package io.nuevedejun.htmxtest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Getter
public class ModelData {
	public static final String MODEL_DATA_ATTR = "model";
	public static final NavigableSet<Integer> PAGE_SIZES =
			Collections.unmodifiableNavigableSet(IntStream.of(20, 50, 100, 250, 500)
					.collect(TreeSet::new, TreeSet::add, TreeSet::addAll));

	private final UserData userData;

	public Set<Integer> getPageSizes() {
		return PAGE_SIZES;
	}
}
