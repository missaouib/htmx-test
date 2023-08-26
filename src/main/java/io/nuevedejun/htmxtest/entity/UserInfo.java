package io.nuevedejun.htmxtest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	@JdbcTypeCode(SqlTypes.JSON)
	private Preferences preferences;

	@Data
	public static class Preferences {
		private Integer pageSize;
	}
}
