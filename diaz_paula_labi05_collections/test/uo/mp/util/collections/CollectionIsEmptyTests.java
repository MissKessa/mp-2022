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

public class CollectionIsEmptyTests {

	static Stream<Arguments> createLists() {
		return Stream.of(Arguments.of(Named.of("ArrayList", new ArrayList())),
				Arguments.of(Named.of("LinkedList", new LinkedList())));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithNoElementsIsEmpty(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		assertTrue(list.isEmpty());
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithOneElementIsNotEmpty(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		list.add("firstElement");
		assertFalse(list.isEmpty());
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithMultipleElementIsNotEmpty(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		list.add("firstElement");
		list.add("secondElement");
		assertFalse(list.isEmpty());
	}
}
