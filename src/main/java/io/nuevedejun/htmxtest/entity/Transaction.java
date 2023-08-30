package io.nuevedejun.htmxtest.entity;

import jakarta.persistence.*;
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
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private OffsetDateTime date;

	private String account;

	private String category;

	private String note;

	private BigDecimal amount;
}
