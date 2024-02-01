package uo.mp2122.util.collections;

import java.util.List;

public class Collections<T extends Comparable<T>> {

	public List<T> sort(List<T> collection) {
//		List<T> sorted = new ArrayList<T>();
//		while (collection.size() != 0) {
//			T min = collection.get(0);
//
//			for (int i = 1; i < collection.size(); i++) {
//				T comparable = collection.get(i);
//				if (min.compareTo(comparable) > 0) {
//					min = comparable;
//				}
//			}
//			sorted.add(min);
//			collection.remove(min);
//		}
//		return sorted;
		java.util.Collections.sort(collection); // sort that list itself in ascending order(modifies)
		// java.util.Collections.reverse(collection); //first, la linea 22 y luego
		// llamar a esta
		// java.util.list(collection);
		return collection;
	}

}
