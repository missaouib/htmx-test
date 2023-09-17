package io.nuevedejun.htmxtest.helper;

import com.github.javafaker.Faker;
import io.nuevedejun.htmxtest.entity.Operation;
import io.nuevedejun.htmxtest.entity.OperationTransaction;
import io.nuevedejun.htmxtest.entity.Transaction;
import io.nuevedejun.htmxtest.entity.User;
import io.nuevedejun.htmxtest.entity.User.Preferences;
import io.nuevedejun.htmxtest.repository.OperationRepository;
import io.nuevedejun.htmxtest.repository.OperationTransactionRepository;
import io.nuevedejun.htmxtest.repository.TransactionRepository;
import io.nuevedejun.htmxtest.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("dev")
class DummyDataIT {
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private OperationTransactionRepository operationTransactionRepository;
	@Autowired
	private UserRepository userRepository;

	private final Faker faker = new Faker();
	private final SecureRandom random = new SecureRandom();

	@Test
	@Disabled("generate dummy data once")
	@Commit
	void generateData() {
		final User user = userRepository.save(new User(null, new Preferences(50)));
		final ZoneOffset offset = Clock.systemDefaultZone().getZone().getRules().getOffset(Instant.now());

		List<Operation> result = IntStream.range(0, 2000).mapToObj(i -> {
			final OffsetDateTime date = faker.date().past(365, TimeUnit.DAYS).toInstant().atOffset(offset);
			return buildGrandOperation(user, date);

		}).map(this::persist).toList();

		assertNotNull(result);
	}

	/**
	 * This method creates an operation. It differs from {@link #buildOperation} in that it adds a transaction to the
	 * composed operation after creating it because it does not assume that the operation already has a transactions.
	 */
	private ComposedOperation buildGrandOperation(User user, OffsetDateTime date) {
		final var initial = BigDecimal.valueOf(faker.number().randomDouble(2, -1000000, 1000000));
		final ComposedOperation co = buildOperation(user, date, initial);
		HashMap<ComposedTransaction, BigDecimal> mut = new HashMap<>(co.txs());
		mut.put(buildTransaction(user, date, initial), initial);
		return new ComposedOperation(co.operation(), Map.copyOf(mut));
	}

	private ComposedOperation buildOperation(User user, OffsetDateTime date, BigDecimal initial) {
		final Map<ComposedTransaction, BigDecimal> children = buildChildren(amount -> buildTransaction(user, date, amount));
		final BigDecimal total = children.values().stream()
				.reduce(BigDecimal::add)
				.map(it -> it.add(initial))
				.orElse(initial);
		final var operation = new Operation(null, user, date,
				faker.commerce().department(),
				faker.harryPotter().quote(),
				total);
		return new ComposedOperation(operation, children);
	}

	private ComposedTransaction buildTransaction(User user, OffsetDateTime date, BigDecimal initial) {
		final Map<ComposedOperation, BigDecimal> children = buildChildren(amount -> buildOperation(user, date, amount));
		final BigDecimal total = children.values().stream()
				.reduce(BigDecimal::add)
				.map(it -> it.add(initial))
				.orElse(initial);
		final var transaction = new Transaction(null, user, date,
				faker.demographic().maritalStatus(),
				faker.backToTheFuture().quote(),
				total);
		return new ComposedTransaction(transaction, children);
	}

	private <T> Map<T, BigDecimal> buildChildren(Function<BigDecimal, T> childProducer) {
		int parts = getSkewedRandom();
		if (parts == 1) {
			return Map.of();
		} else {
			HashMap<T, BigDecimal> map = IntStream.range(0, parts - 1).collect(HashMap::new, (m, i) -> {
				final var amount = BigDecimal.valueOf(faker.number().randomDouble(2, -1000000, 1000000));
				m.put(childProducer.apply(amount), amount);
			}, HashMap::putAll);
			return Map.copyOf(map);
		}
	}

	private int getSkewedRandom() {
		return (int) Math.min(y(random.nextDouble()), 4.0);
	}

	private final static double A = 3;

	/**
	 * Inverse function that always returns 1 for input 1, no matter the value of coefficient A.
	 * <br>
	 * This utility method transforms a uniformly random double between 0 and 1 to value that later gets truncated
	 * to an integer. Using that operation we get random positive integers whose probability of occurring rapidly
	 * decreases as the values get bigger.
	 * <br>
	 * The probability of any integer occurring is expressed by the following formula.
	 * <pre>pn = A / ((A * n - A + 1) * (A * n + 1))</pre>
	 * For example, if we want to have a 70% probability of obtaining 1, then A should be 3.
	 */
	private double y(double x) {
		if (x == 0.0) return Double.MAX_VALUE; // undef
		return ((A - 1.0) * x + 1.0) / (A * x);
	}

	private Operation persist(ComposedOperation compOp) {
		final Operation savedOp = operationRepository.save(compOp.operation());
		final List<Entry<Transaction, BigDecimal>> savedTx = compOp.txs().entrySet().stream()
				.map(entry -> Map.entry(transactionRepository.save(persist(entry.getKey())), entry.getValue()))
				.toList();
		final List<OperationTransaction> ots = savedTx.stream()
				.map(tx -> new OperationTransaction(null, savedOp, tx.getKey(), tx.getValue()))
				.toList();
		operationTransactionRepository.saveAll(ots);
		return savedOp;
	}

	private Transaction persist(ComposedTransaction compTx) {
		final Transaction savedTx = transactionRepository.save(compTx.transaction());
		final List<Entry<Operation, BigDecimal>> savedOp = compTx.ops().entrySet().stream()
				.map(entry -> Map.entry(operationRepository.save(persist(entry.getKey())), entry.getValue()))
				.toList();
		final List<OperationTransaction> ots = savedOp.stream()
				.map(op -> new OperationTransaction(null, op.getKey(), savedTx, op.getValue()))
				.toList();
		operationTransactionRepository.saveAll(ots);
		return savedTx;
	}

	private record ComposedOperation(Operation operation, Map<ComposedTransaction, BigDecimal> txs) {
	}

	private record ComposedTransaction(Transaction transaction, Map<ComposedOperation, BigDecimal> ops) {
	}
}
