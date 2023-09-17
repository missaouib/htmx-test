package io.nuevedejun.htmxtest.hibernate;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

/**
 * Annotate a {@link java.util.UUID} entity column to populate it with an auto-generated UUID version 6. The node part
 * of the UUID can be configured using the env variable {@link UUIDVersion6Generator#V6_UUID_NODE_ADDR}.
 *
 * @see UUIDVersion6Generator
 * @see IdGeneratorType
 */
@IdGeneratorType(UUIDVersion6Generator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface UUIDVersion6 {
	String BLANK = "";

	/**
	 * MAC address used in the UUID.
	 */
	String address() default BLANK;
}
