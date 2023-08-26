package io.nuevedejun.htmxtest;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class ViewOptions {
	public static final String VIEW_OPTS_ATTR = "viewOptions";
	public static final NavigableSet<Integer> PAGE_SIZES =
			Collections.unmodifiableNavigableSet(IntStream.of(20, 50, 100, 250, 500)
					.collect(TreeSet::new, TreeSet::add, TreeSet::addAll));

	public Set<Integer> getPageSizes() {
		return PAGE_SIZES;
	}
}
