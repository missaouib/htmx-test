package io.nuevedejun.htmxtest.hibernate;

import io.nuevedejun.htmxtest.service.UUIDGenerator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.AnnotationBasedGenerator;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;
import org.hibernate.generator.GeneratorCreationContext;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.EnumSet;
import java.util.UUID;

import static io.nuevedejun.htmxtest.hibernate.UUIDVersion6.BLANK;
import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;

/**
 * @see UUIDVersion6
 * @see org.hibernate.boot.model.internal.GeneratorBinder
 */
public class UUIDVersion6Generator implements BeforeExecutionGenerator, AnnotationBasedGenerator<UUIDVersion6> {
	public static final String V6_UUID_NODE_ADDR = "V6_UUID_NODE_ADDR";

	private transient UUIDGenerator generator;

	@Override
	public void initialize(UUIDVersion6 annotation, Member member, GeneratorCreationContext context) {
		// only allow fields of type java.util.UUID
		if (member instanceof Field field && !UUID.class.isAssignableFrom(field.getType())) {
			throw new IllegalStateException("Unexpected annotated field type [" +
					field.getType().getName() + "] should be [" + UUID.class.getName() + "]");
		}

		final String address = BLANK.equals(annotation.address()) ? System.getenv(V6_UUID_NODE_ADDR) : annotation.address();
		this.generator = new UUIDGenerator.Version6(address);
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
