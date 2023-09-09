package io.nuevedejun.htmxtest.service;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedReorderedGenerator;
import org.springframework.lang.Nullable;

import java.util.UUID;

public interface UUIDGenerator {
	UUID generate();

	class Version6 implements UUIDGenerator {
		private final TimeBasedReorderedGenerator generator;

		/**
		 * @param address MAC address to use for the UUIDs
		 * @throws IllegalArgumentException if the MAC address is invalid
		 */
		public Version6(@Nullable String address) {
			final EthernetAddress eth = buildEthernetAddress(address);
			this.generator = Generators.timeBasedReorderedGenerator(eth);
		}

		@Nullable
		private EthernetAddress buildEthernetAddress(@Nullable String address) {
			if (address == null) return null;
			try {
				return EthernetAddress.valueOf(address);
			} catch (NumberFormatException nfe) {
				throw new IllegalArgumentException("Provided MAC address is invalid: " + address, nfe);
			}
		}

		@Override
		public UUID generate() {
			return generator.generate();
		}
	}
}
