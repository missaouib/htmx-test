package io.nuevedejun.htmxtest.config;

import io.nuevedejun.htmxtest.transaction.TransactionModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalModel {
	public static class UserData {
		public List<TransactionModel> getTransactions() {
			final ZoneOffset offset = Clock.systemDefaultZone().getZone().getRules().getOffset(Instant.now());
			return List.of(
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Healthcare", "Payment at X", BigDecimal.valueOf(-1965000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2)),
					new TransactionModel(
							OffsetDateTime.of(2023, 7, 31, 12, 54, 0, 0, offset),
							"Savings", "Groceries", "Payment at X", BigDecimal.valueOf(-5465000, 2))
			);
		}
	}

	private static final String ATTR_NAME = "userData";

	@ModelAttribute(ATTR_NAME)
	public UserData userData() {
		log.info("Binding user data to model attribute: {}", ATTR_NAME);
		return new UserData();
	}
}
