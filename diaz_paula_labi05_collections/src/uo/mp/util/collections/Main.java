package uo.mp.util.collections;

import uo.mp.util.collections.impl.LinkedList;

public class Main {

	public static void main(String[] args) {
		List<String> myList = new LinkedList<>();
		myList.add("hola");
		myList.add("caracola");
		for (String string : myList)
			System.out.println(string);

	}

}
