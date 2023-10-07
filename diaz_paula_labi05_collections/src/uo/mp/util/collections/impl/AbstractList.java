package uo.mp.util.collections.impl;

import uo.mp.util.collections.List;

public abstract class AbstractList<T> implements List<T> {
	private int numberOfElements;

	protected AbstractList() {
		this(0);
	}

	protected AbstractList(int numberOfElements) {
		if (numberOfElements < 0)
			throw new IllegalArgumentException();

		this.numberOfElements = numberOfElements;
	}

	@Override
	public int size() {
		return this.numberOfElements;
	}

	/**
	 * Sets a new value for the numberOfElements field
	 * 
	 * @param size is the new value for the field
	 * @throws IllegalArgumentException if the size is negative
	 */
	public void setSize(int size) {
		if (size < 0)
			throw new IllegalArgumentException();

		this.numberOfElements = size;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@Override
	public boolean add(T element) {
		add(size(), element);
		return true;
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		return remove(index) != null;
	}

}
