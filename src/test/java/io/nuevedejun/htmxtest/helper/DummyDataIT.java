package io.nuevedejun.htmxtest.helper;

import com.github.javafaker.Faker;
import io.nuevedejun.htmxtest.entity.Transaction;
import io.nuevedejun.htmxtest.entity.User;
import io.nuevedejun.htmxtest.entity.User.Preferences;
import io.nuevedejun.htmxtest.repository.TransactionRepository;
import io.nuevedejun.htmxtest.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("dev")
class DummyDataIT {
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private UserRepository userRepository;

	private final Faker faker = new Faker();

	@Test
	@Disabled("generate dummy data once")
	@Commit
	void generateTransactions() {
		final User user = userRepository.save(new User(null, new Preferences(50)));

		ZoneOffset offset = Clock.systemDefaultZone().getZone().getRules().getOffset(Instant.now());
		List<Transaction> transactions = transactionRepository.saveAll(buildTransactionList(user, offset, 2000));

		assertNotNull(transactions);
	}

	private List<Transaction> buildTransactionList(User user, ZoneOffset offset, int size) {
		return IntStream.range(0, size).mapToObj(n -> new Transaction(
						null,
						user,
						faker.date().past(365, TimeUnit.DAYS).toInstant().atOffset(offset),
						faker.demographic().maritalStatus(),
						faker.backToTheFuture().quote(),
						BigDecimal.valueOf(faker.number().randomDouble(2, -1000000, 1000000))))
				.toList();
	}
}
