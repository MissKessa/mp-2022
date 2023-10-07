package uo.mp.util.collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.Preconditions;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

public class CollectionRemoveTests {
	static Stream<Arguments> createLists() {
		return Stream.of(Arguments.of(Named.of("ArrayList", new ArrayList())),
				Arguments.of(Named.of("LinkedList", new LinkedList())));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithNoElementsRemoveAnObject(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		assertFalse(list.remove(element1));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithOneElementRemoveThatElement(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		list.add(element1);
		assertTrue(list.remove(element1));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithOneElementRemoveOtherElement(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		list.add(element1);
		assertFalse(list.remove(element2));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithTwoElementsRemoveTheFirstElement(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		list.add(element1);
		list.add(element2);
		assertTrue(list.remove(element1));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithTwoElementsRemoveTheSecondElement(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		list.add(element1);
		list.add(element2);
		assertTrue(list.remove(element2));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithTwoElementsRemoveOtherElement(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		Object element3 = "thirdElement";

		list.add(element1);
		list.add(element2);
		assertFalse(list.remove(element3));
	}
}
