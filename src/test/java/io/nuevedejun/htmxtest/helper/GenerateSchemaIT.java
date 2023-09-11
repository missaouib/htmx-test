package io.nuevedejun.htmxtest.helper;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles({"dev", "ddl"})
class GenerateSchemaIT {
	@Test
	@Disabled("not required to generate ddl")
	void generateDDL() {
		assertTrue(true);
	}
}
