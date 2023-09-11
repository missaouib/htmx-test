package io.nuevedejun.htmxtest.entity;

import io.nuevedejun.htmxtest.hibernate.UUIDVersion6;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(indexes = {
		@Index(columnList = "date"),
		@Index(columnList = "user_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
	@Id
	@UUIDVersion6
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	private OffsetDateTime date;

	private String category;

	private String note;

	private BigDecimal amount;
}
