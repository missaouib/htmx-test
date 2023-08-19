package io.nuevedejun.htmxtest.transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionModel(OffsetDateTime date, String account, String category, String note, BigDecimal amount) {
}
