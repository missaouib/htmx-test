package io.nuevedejun.htmxtest.entity;

import io.nuevedejun.htmxtest.hibernate.UUIDVersion6;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(indexes = @Index(columnList = "date"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	@Id
	@UUIDVersion6
	private UUID id;

	private OffsetDateTime date;

	private String account;

	private String category;

	private String note;

	private BigDecimal amount;
}
