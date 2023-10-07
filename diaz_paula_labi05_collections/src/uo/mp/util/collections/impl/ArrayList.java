package uo.mp.util.collections.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> extends AbstractList<T> {
	private Object[] list;

	public ArrayList() {
		super();
		list = new Object[10];
	}

	@Override
	public void clear() {
		list = new Object[10];
		setSize(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		if (index < 0 || index >= size())
			throw new IllegalArgumentException();

		return (T) list[index];
	}

	@Override
	public T set(int index, T element) {
		if (index < 0 || index >= size())
			throw new IllegalArgumentException();

		T previousElement = get(index);
		list[index] = element;
		return previousElement;
	}

	@Override
	public void add(int index, T element) {
		if (index < 0 || index > size())
			throw new IllegalArgumentException();

		if (size() == list.length)
			enlarge();

		Object now = null;
		for (int i = index; i < size() + 1 /* this.list.length *//* && (list[i] != null || i == index) */; i++) {
			if (i == index) {
				now = list[i];
				list[i] = element;
			} else {
				Object next = list[i];
				list[i] = now;
				now = next;
			}
		}
		setSize(size() + 1);
	}

	/**
	 * It duplicates the length of the arraylist. It still has the same elements
	 */
	private void enlarge() {
		Object[] list2 = new Object[size() * 2];
		for (int i = 0; i < size(); i++) {
			list2[i] = get(i);
		}
		this.list = list2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T remove(int index) {
		if (index < 0 || index >= size())
			return null;
		Object removed = get(index);
		for (int i = index + 1; i < size(); i++) {
			this.list[i - 1] = get(i);
		}
		setSize(size() - 1);
		return (T) removed;
	}

	@Override
	public int indexOf(Object o) {
		int index = 0;
		if (size() == 0) {
			return -1;
		}
		for (Object element = list[0]; element != null; element = list[index]) {
			if (element.equals(o))
				return index;
			index++;
			if (index == size())
				break;
		}
		return -1;
	}

	@Override
	public String toString() {
		StringBuilder representation = new StringBuilder();
		for (int i = 0; i < size(); i++) {
			representation.append(list[i].toString());
			if (i < size() - 1)
				representation.append(", ");
		}
		return representation.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof ArrayList))
			return false;
		@SuppressWarnings("unchecked")
		ArrayList<T> a = (ArrayList<T>) o;
		if (a.size() != size())
			return false;
		for (int i = 0; i < size(); i++) {
			if (!get(i).equals(a.get(i)))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hashCode = 1;
		for (Object e : this.list)
			hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
		return hashCode;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator<T> {
		private int currentPosition;

		@Override
		public boolean hasNext() {
			return currentPosition < size();
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (hasNext())
				return (T) list[currentPosition++];
			else
				throw new NoSuchElementException("No more elements...");
		}

	}
}
