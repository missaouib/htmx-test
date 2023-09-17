package io.nuevedejun.htmxtest.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record OperationData(OffsetDateTime date, String account, String category, String note, BigDecimal amount) {
}
