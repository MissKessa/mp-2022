package uo.mp.util.collections;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.Preconditions;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

public class CollectionContainsTests {
	static Stream<Arguments> createLists() {
		return Stream.of(Arguments.of(Named.of("ArrayList", new ArrayList())),
				Arguments.of(Named.of("LinkedList", new LinkedList())));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void containsAListWithNoElementOneElement(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";

		assertFalse(list.contains(element1));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void containsAListWithOneElementThatElement(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		list.add(element1);

		assertTrue(list.contains(element1));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void containsAListWithOneElementAnotherElement(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		list.add(element1);

		assertFalse(list.contains(element2));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void containsAListWithTwoElementsThoseElements(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		list.add(element1);
		list.add(element2);

		assertTrue(list.contains(element1));
		assertTrue(list.contains(element2));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void containsAListWithTwoElementsOtherElement(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		Object element3 = "thirdElement";
		list.add(element1);
		list.add(element2);

		assertTrue(list.contains(element1));
		assertTrue(list.contains(element2));
		assertFalse(list.contains(element3));
	}
}
