package io.nuevedejun.htmxtest.faker;

import com.github.javafaker.Faker;
import io.nuevedejun.htmxtest.entity.Transaction;
import io.nuevedejun.htmxtest.repository.TransactionRepository;
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
	private final Faker faker = new Faker();

	@Test
	@Disabled("generate dummy data once")
	@Commit
	void generateTransactions() {
		ZoneOffset offset = Clock.systemDefaultZone().getZone().getRules().getOffset(Instant.now());

		List<Transaction> transactions = IntStream.range(0, 2000).mapToObj(n -> new Transaction(
						null,
						faker.date().past(365, TimeUnit.DAYS).toInstant().atOffset(offset),
						faker.demographic().maritalStatus(),
						faker.commerce().department(),
						faker.backToTheFuture().quote(),
						BigDecimal.valueOf(faker.number().randomDouble(2, -1000000, 1000000))))
				.toList();

		List<Transaction> saved = transactionRepository.saveAll(transactions);
		assertNotNull(saved);
	}
}
