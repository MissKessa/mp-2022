package uo.mp.util.collections;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import uo.mp.util.collections.impl.ArrayList;
import uo.mp.util.collections.impl.LinkedList;

public class ListEqualsTests {

	@Test
	void EmptyArrayListEqualsToSameArrayList() {
		ArrayList a = new ArrayList();
		assertTrue(a.equals(a));
	}

	@Test
	void EmptyArrayListEqualsToSameContentArrayList() {
		ArrayList a = new ArrayList();
		ArrayList b = new ArrayList();
		assertTrue(a.equals(b));
	}

	@Test
	void lengthOneArrayListEqualsToSameArrayList() {
		ArrayList a = new ArrayList();
		Object element = "firstElement";
		a.add(element);
		assertTrue(a.equals(a));
	}

	@Test
	void lengthOneArrayListEqualsToSameContentArrayList() {
		ArrayList a = new ArrayList();
		ArrayList b = new ArrayList();
		Object element = "firstElement";
		a.add(element);
		b.add(element);
		assertTrue(a.equals(b));
	}

	@Test
	void lengthTwoArrayListEqualsToSameArrayList() {
		ArrayList a = new ArrayList();
		Object element = "firstElement";
		Object element2 = "secondElement";
		a.add(element);
		a.add(element2);
		assertTrue(a.equals(a));
	}

	@Test
	void lengthTwoArrayListEqualsToSameContentArrayList() {
		ArrayList a = new ArrayList();
		ArrayList b = new ArrayList();
		Object element = "firstElement";
		Object element2 = "secondElement";
		a.add(element);
		b.add(element);
		a.add(element2);
		b.add(element2);
		assertTrue(a.equals(b));
	}

	@Test
	void EqualsArrayListWithDifferentSize() {
		ArrayList a = new ArrayList();
		ArrayList b = new ArrayList();
		Object element = "firstElement";
		Object element2 = "secondElement";
		a.add(element);
		b.add(element);
		a.add(element2);
		assertFalse(a.equals(b));
	}

	@Test
	void EqualsArrayListWithDifferentOrderOfElements() {
		ArrayList a = new ArrayList();
		ArrayList b = new ArrayList();
		Object element = "firstElement";
		Object element2 = "secondElement";
		a.add(element);
		b.add(element2);
		a.add(element2);
		b.add(element);
		assertFalse(a.equals(b));
	}

	@Test
	void EqualsArrayListWithDifferentElements() {
		ArrayList a = new ArrayList();
		ArrayList b = new ArrayList();
		Object element = "firstElement";
		Object element2 = "secondElement";
		a.add(element);
		b.add(element2);
		assertFalse(a.equals(b));
	}

	@Test
	void EmptyLinkedListEqualsToSameLinkedList() {
		LinkedList a = new LinkedList();
		assertTrue(a.equals(a));
	}

	@Test
	void EmptyLinkedListEqualsToSameContentLinkedList() {
		LinkedList a = new LinkedList();
		LinkedList b = new LinkedList();
		assertTrue(a.equals(b));
	}

	@Test
	void lengthOneLinkedListEqualsToSameLinkedList() {
		LinkedList a = new LinkedList();
		Object element = "firstElement";
		a.add(element);
		assertTrue(a.equals(a));
	}

	@Test
	void lengthOneLinkedListEqualsToSameContentLinkedList() {
		LinkedList a = new LinkedList();
		LinkedList b = new LinkedList();
		Object element = "firstElement";
		a.add(element);
		b.add(element);
		assertTrue(a.equals(b));
	}

	@Test
	void lengthTwoLinkedListEqualsToSameLinkedList() {
		LinkedList a = new LinkedList();
		Object element = "firstElement";
		Object element2 = "secondElement";
		a.add(element);
		a.add(element2);
		assertTrue(a.equals(a));
	}

	@Test
	void lengthTwoLinkedListEqualsToSameContentLinkedList() {
		LinkedList a = new LinkedList();
		LinkedList b = new LinkedList();
		Object element = "firstElement";
		Object element2 = "secondElement";
		a.add(element);
		b.add(element);
		a.add(element2);
		b.add(element2);
		assertTrue(a.equals(b));
	}

	@Test
	void EqualsLinkedListWithDifferentSize() {
		LinkedList a = new LinkedList();
		LinkedList b = new LinkedList();
		Object element = "firstElement";
		Object element2 = "secondElement";
		a.add(element);
		b.add(element);
		a.add(element2);
		assertFalse(a.equals(b));
	}

	@Test
	void EqualsLinkedListWithDifferentOrderOfElements() {
		LinkedList a = new LinkedList();
		LinkedList b = new LinkedList();
		Object element = "firstElement";
		Object element2 = "secondElement";
		a.add(element);
		b.add(element2);
		a.add(element2);
		b.add(element);
		assertFalse(a.equals(b));
	}

	@Test
	void EqualsLinkedListWithDifferentElements() {
		LinkedList a = new LinkedList();
		LinkedList b = new LinkedList();
		Object element = "firstElement";
		Object element2 = "secondElement";
		a.add(element);
		b.add(element2);
		assertFalse(a.equals(b));
	}
}
