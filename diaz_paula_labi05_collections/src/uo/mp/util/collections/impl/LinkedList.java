package uo.mp.util.collections.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractList<T> {
	private Node first;

	private class Node {
		T element;
		Node next;

		Node(T element, Node next) {
			this.element = element;
			this.next = next;
		}

		@Override
		public int hashCode() {
			int hashCode = 1;
			hashCode = 31 * hashCode + (element == null ? 0 : element.hashCode());
			return hashCode;
		}

		@Override
		public boolean equals(Object o) {
			// if (o == null || !(o instanceof Node))
			// return false;
			@SuppressWarnings("unchecked")
			Node a = (Node) o;
			return this.element.equals(a.element) && (next == null && a.next == null || this.next.equals(a.next));
		}

	}

	public LinkedList() {
		super();
		this.first = null;
	}

	@Override
	public void clear() {
		for (Node x = this.first; x != null;) {
			Node next = x.next;
			x.element = null;
			x.next = null;
			x = next;
		}
		setSize(0);
		this.first = null;
	}

	@Override
	public T get(int index) {
		return getNode(index).element;
	}

	private Node getNode(int index) {
		if (index < 0 || index > size())
			throw new IllegalArgumentException();

		Node node = this.first;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T set(int index, T element) {
		final Node prevNode = getNode(index);
		final Object prevElement = prevNode.element;
		prevNode.element = element;
		return (T) prevElement;
	}

	@Override
	public void add(int index, T element) {
		Node node = new Node(element, getNode(index));
		if (index == 0)
			first = node;
		else
			getNode(index - 1).next = node;

		setSize(size() + 1);
	}

	@Override
	public T remove(int index) {
		if (index < 0 || index >= size())
			return null;
		Node nodeToRemove = getNode(index);
		if (index == 0)
			first = nodeToRemove.next;
		else
			getNode(index - 1).next = nodeToRemove.next;

		nodeToRemove.next = null;
		setSize(size() - 1);
		return nodeToRemove.element;
	}

	@Override
	public int indexOf(Object o) {
		int index = 0;
		for (Node node = this.first; node != null; node = node.next) {
			if (node.element.equals(o)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder representation = new StringBuilder();
		int counter = 0;
		for (Node node = this.first; node != null; node = node.next) {
			representation.append(node.element.toString());
			if (counter < size() - 1) {
				representation.append(", ");
				counter++;
			}
		}
		return representation.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof LinkedList))
			return false;
		LinkedList a = (LinkedList) o;
		if (a.size() != size())
			return false;

		int counter = 0;
		Node node1 = this.first;
		Node node2 = a.first;
		while (counter < size()) {
			if (!node1.equals(node2)) {
				return false;
			}
			node1 = node1.next;
			node2 = node2.next;
			counter++;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hashCode = 1;
		for (Node node = this.first; node != null; node = node.next)
			hashCode = 31 * hashCode + (node == null ? 0 : node.hashCode());
		return hashCode;
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<T> {
		private Node currentNode;

		public LinkedListIterator() {
			currentNode = getNode(0);
		}

		@Override
		public boolean hasNext() {
			return currentNode != null;
		}

		@Override
		public T next() {
			if (hasNext()) {
				currentNode = currentNode.next;
				return currentNode.element;
			} else
				throw new NoSuchElementException("No more elements...");
		}

	}

}
