package io.nuevedejun.htmxtest.entity;

import io.nuevedejun.htmxtest.hibernate.UUIDVersion6;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.lang.Nullable;

import java.util.UUID;

@Entity
@Table(name = "`user`")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@UUIDVersion6
	private UUID id;

	@JdbcTypeCode(SqlTypes.JSON)
	private Preferences preferences;

	public record Preferences(@Nullable Integer pageSize) {
	}
}
