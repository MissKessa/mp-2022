package uo.mp.lab03.dome.persistence;

import java.util.ArrayList;

import uo.mp.lab03.dome.model.Item;
import uo.mp.util.check.ArgumentChecks;

/**
 * This class represents the central database of our library. It stores
 * internally all the items and allows to perform basic operations such as
 * adding, deleting, filtering and printing them.
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class Database {

	private final ArrayList<Item> items = new ArrayList<Item>();

	// Notice the following constructor. Even though it is the no-args constructor
	// we include it so we
	// can add documentation to it.
	/**
	 * Default constructor without parameters. When a MediaLibrary object is
	 * instantiated, an internal collection called items is created by default where
	 * the items will be stored. This collection is initialized to an ArrayList of
	 * default size.
	 */
	public Database() {
	}

	/**
	 * This method returns a copy of the internal list of items in the library. The
	 * list of items returned is a deep copy and therefore any modification to the
	 * list will have no effect on the internal structure of the database.
	 * 
	 * @return A deep copy of the list of items. If the list is empty then an empty
	 *         list but never null.
	 */
	public ArrayList<Item> getItems() {
		final ArrayList<Item> copy = new ArrayList<Item>(this.items.size());
		for (Item item : this.items) {
			copy.add(item);
		}
		return copy;
	}

	/**
	 * Adds an item to the library. To do so, it modifies the current instance by
	 * adding the item to the collection of items. For the operation to succeed, the
	 * item must not be null. If it is then an IllegalArgumentException is thrown.
	 * It also returns a boolean value that represents the propagation of the return
	 * value of the add operation on the internal collection.
	 * 
	 * @param item is the item to add to the library. It must be not null.
	 * @return propagates the return value of the add operation over the internal
	 *         collection.
	 * @throws IllegalArgumentException if the item to add is null.
	 */
	public boolean add(Item item) {
		ArgumentChecks.isTrue(item != null, "The item cannot be null");
		return this.items.add(item);
	}

	/**
	 * This method removes the given item from the library. If the given item is not
	 * in the library then it does nothing. It returns true or false depending on
	 * whether an item has been removed or not.
	 * 
	 * @param item to remove from the library. It must be not null.
	 * @return true if any item removed. False otherwise.
	 * @throws IllegalArgumentException if the item to add is null.
	 */
	public boolean remove(Item item) {
		ArgumentChecks.isTrue(item != null, "The item cannot be null");
		return this.items.remove(item);
	}

	/**
	 * Returns the String representation of the media library. The format is as
	 * follow "Database [items=%d]" where items value corresponds to the number of
	 * items in the list.
	 */
	@Override
	public String toString() {
		return String.format("Database [items=%d]", this.items.size());
	}

}
