package uo.mp.util.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.Preconditions;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

public class ListAddAtPositionTests {
	static Stream<Arguments> createLists() {
		return Stream.of(Arguments.of(Named.of("ArrayList", new ArrayList())),
				Arguments.of(Named.of("LinkedList", new LinkedList())));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithOneElementAddAnotherElementToTheBeggining(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		list.add(element1);
		list.add(0, element2);

		assertTrue(list.size() == 2);
		assertEquals(0, list.indexOf(element2));
		assertEquals(1, list.indexOf(element1));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithTwoElementsAddAnotherElementToTheBeggining(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		Object element3 = "thirdElement";
		list.add(element1);
		list.add(element2);
		list.add(0, element3);

		assertTrue(list.size() == 3);
		assertEquals(0, list.indexOf(element3));
		assertEquals(1, list.indexOf(element1));
		assertEquals(2, list.indexOf(element2));
	}

	@ParameterizedTest
	@MethodSource("createLists")
	void aListWithTwoElementsAddAnotherElementToTheMiddle(List list) {
		Preconditions.notNull(list, "The initial list cannot be null.");

		Object element1 = "firstElement";
		Object element2 = "secondElement";
		Object element3 = "thirdElement";
		list.add(element1);
		list.add(element2);
		list.add(1, element3);

		assertTrue(list.size() == 3);
		assertEquals(1, list.indexOf(element3));
		assertEquals(0, list.indexOf(element1));
		assertEquals(2, list.indexOf(element2));
	}
}
