package uo.mp.util.collections;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.Preconditions;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

public class ListIndexOfTests {
	static Stream<Arguments> createLists() {
		return Stream.of(Arguments.of(Named.of("ArrayList", new ArrayList())),
				Arguments.of(Named.of("LinkedList", new LinkedList())));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void indexOfOneElementInAListWithNoElements(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";

		assertEquals(-1, list.indexOf(element1));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void indexOfTheElementOfAListWithOneElement(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		list.add(element1);

		assertEquals(0, list.indexOf(element1));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void indexOfAnotherElementOfAListWithOneElemen(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		list.add(element1);

		assertEquals(-1, list.indexOf(element2));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void indexOfTheElementsOfAListWithTwoElements(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		list.add(element1);
		list.add(element2);

		assertEquals(0, list.indexOf(element1));
		assertEquals(1, list.indexOf(element2));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void indexOfAnotherElementOfAListWithTwoElements(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		Object element3 = "thirdElement";
		list.add(element1);
		list.add(element2);

		assertEquals(0, list.indexOf(element1));
		assertEquals(1, list.indexOf(element2));
		assertEquals(-1, list.indexOf(element3));
	}
}
