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

public class ListRemoveAtPositionTests {

	static Stream<Arguments> createLists() {
		return Stream.of(Arguments.of(Named.of("ArrayList", new ArrayList())),
				Arguments.of(Named.of("LinkedList", new LinkedList())));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithOneElementRemoveThatPosition(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		list.add(element1);

		assertEquals(element1, list.remove(0));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithTwoElementsRemoveFirstPosition(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		list.add(element1);
		list.add(element2);

		assertEquals(element1, list.remove(0));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithTwoElementsRemoveSecondPosition(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		list.add(element1);
		list.add(element2);

		assertEquals(element2, list.remove(1));
	}

}
