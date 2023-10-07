package uo.mp.util.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.Preconditions;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

public class CollectionToStringTests {
	static Stream<Arguments> createLists() {
		return Stream.of(Arguments.of(Named.of("ArrayList", new ArrayList())),
				Arguments.of(Named.of("LinkedList", new LinkedList())));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void toStringAListWithNoElements(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		String representation = "";
		assertEquals(representation, list.toString());
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void toStringAListWithOneElement(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		list.add(element1);
		String representation = "firstElement";
		assertEquals(representation, list.toString());
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void toStringAListWithTwoElements(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		list.add(element1);
		list.add(element2);
		String representation = "firstElement, secondElement";
		assertEquals(representation, list.toString());
	}
}
