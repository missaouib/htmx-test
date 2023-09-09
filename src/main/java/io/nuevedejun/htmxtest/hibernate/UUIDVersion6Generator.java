package io.nuevedejun.htmxtest.hibernate;

import io.nuevedejun.htmxtest.service.UUIDGenerator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.EnumSet;
import java.util.UUID;

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;

/**
 * @see UUIDVersion6
 */
public class UUIDVersion6Generator implements BeforeExecutionGenerator {
	public static final String V6_UUID_NODE_ADDR = "V6_UUID_NODE_ADDR";

	private final transient UUIDGenerator generator;

	/**
	 * This constructor signature is recognized in {@link org.hibernate.boot.model.internal.GeneratorBinder} to
	 * instantiate the generator.
	 *
	 * @param ignoredAnnotation an instance of the strategy's annotation type. Typically, implementations will retrieve the
	 *                          annotation's attribute values and store them in fields.
	 * @param member            the Java member annotated with the generator annotation.
	 * @param ignoredContext    a {@link CustomIdGeneratorCreationContext}
	 */
	public UUIDVersion6Generator(UUIDVersion6 ignoredAnnotation, Member member, CustomIdGeneratorCreationContext ignoredContext) {
		// only allow fields of type UUID
		if (member instanceof Field field && !UUID.class.isAssignableFrom(field.getType())) {
			throw new IllegalStateException("Unexpected annotated field type [" +
					field.getType().getName() + "] should be [" + UUID.class.getName() + "]");
		}

		final String node = System.getenv(V6_UUID_NODE_ADDR);
		this.generator = new UUIDGenerator.Version6(node);
	}

	@Override
	public EnumSet<EventType> getEventTypes() {
		return INSERT_ONLY;
	}

	@Override
	public UUID generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
		return generator.generate();
	}
}
