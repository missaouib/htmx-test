package io.nuevedejun.htmxtest.entity;

import io.nuevedejun.htmxtest.hibernate.UUIDVersion6;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(indexes = {
		@Index(columnList = "operation_id, transaction_id", unique = true),
		@Index(columnList = "transaction_id"),
		@Index(columnList = "user_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationTransaction {
	@Id
	@UUIDVersion6
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@ManyToOne
	private Operation operation;

	@ManyToOne
	private Transaction transaction;

	private BigDecimal amount;
}
