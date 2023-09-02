package io.nuevedejun.htmxtest.model;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

public record ModelData(UserData userData) {
	public static final String MODEL_DATA_ATTR = "model";
	public static final NavigableSet<Integer> PAGE_SIZES =
			Collections.unmodifiableNavigableSet(IntStream.of(20, 50, 100, 250, 500)
					.collect(TreeSet::new, TreeSet::add, TreeSet::addAll));

	public Set<Integer> getPageSizes() {
		return PAGE_SIZES;
	}
}
