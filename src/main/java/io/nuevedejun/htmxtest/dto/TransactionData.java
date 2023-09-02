package io.nuevedejun.htmxtest.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionData(OffsetDateTime date, String account, String category, String note, BigDecimal amount) {
}
