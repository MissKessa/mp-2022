package uo.mp.lab03.dome.service;

import java.util.ArrayList;

import uo.mp.lab03.dome.model.Borrowable;
import uo.mp.lab03.dome.model.Item;
import uo.mp.lab03.dome.persistence.Database;
import uo.mp.util.check.ArgumentChecks;

/**
 * This class simulates the library service. That is, the functionality offered
 * to the different clients, whether they are a web API, a desktop GUI or a CLI.
 * It allows operations to be performed against the implemented persistence
 * service.
 * 
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class MediaLibrary {

	private final Database database = new Database();

	public MediaLibrary() {
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
		ArgumentChecks.isTrue(item != null, "The item cannot be null.");
		return this.database.add(item);
	}

	/**
	 * @implNote Currently the method is implemented as an iterative scheme using
	 *           java for-each. However it is possible to use a single expression as
	 *           follows: return this.items.stream().filter(item ->
	 *           item.inStock()).count();
	 * @return the number of items in the bookstore that are marked as in stock.
	 */
	public int getNumberOfItemInStock() {
		int itemsInStock = 0;
		for (Item item : this.database.getItems()) {
			if (item.inStock()) {
				itemsInStock++;
			}
		}
		return itemsInStock;
	}

	/**
	 * Auxiliary method that allows to obtain the list of all the elements in the
	 * list of items in string format where each line of the string corresponds to
	 * an element.
	 * 
	 * @returns the list of items registered in the library as a list of strings.
	 *          Each line is an item.
	 */
	public String getItemsAsString() {
		final StringBuilder returnString = new StringBuilder();
		for (Item item : this.database.getItems()) {
			returnString.append(item);
			returnString.append("\n");
		}
		return returnString.toString();
	}

	/**
	 * Returns the String representation of the media library. The format is as
	 * follow "MediaLibrary [items=%d]" where items value corresponds to the number
	 * of items in the list.
	 */
	@Override
	public String toString() {
		return String.format("MediaLibrary [items=%d]", this.database.getItems().size());
	}

	/**
	 * Method that searches for the item in the media library. If the item is in the
	 * library, then a reference to the item is returned. If not, it returns null
	 * 
	 * @param item is the item that we are looking for in the media library
	 * @return a reference to the item if it's in the collection. If not null is
	 *         returned
	 */
	public Item getItem(Item item) {
		ArgumentChecks.isTrue(item != null, "The item cannot be null");
		if (!database.getItems().contains(item)) {
			return null;
		}
		for (Item element : database.getItems()) {
			if (element.equals(item)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * Method that returns a string with all the responsible people of each item of
	 * the medialibrary
	 * 
	 * @return a string with all the responsible people separated by commas
	 */
	public String getResponsiblePeople() {
		final StringBuilder responsiblePeople = new StringBuilder();
		for (int i = 0; i < database.getItems().size(); i++) {

			final Item element = database.getItems().get(i);
			responsiblePeople.append(element.getResponsiblePerson());
			if (i < database.getItems().size() - 1) {
				responsiblePeople.append(", ");
			}
		}

		return responsiblePeople.toString();
	}

	/**
	 * This method will return the sum of the total price of each item.
	 * 
	 * @return The sum of the total price of each item
	 */
	public double getTotalPrice() {
		double sumPrices = 0;
		for (Item item : this.database.getItems()) {
			sumPrices += item.getTotalPrice();
		}
		return sumPrices;
	}

	/**
	 * 
	 * @return a list of borrowable items
	 */
	public ArrayList<Item> getBorrowableItems() {
		ArrayList<Item> borrowables = new ArrayList<Item>();
		for (Item element : database.getItems()) {
			if (element instanceof Borrowable) {
				borrowables.add(element);
			}
		}
		return borrowables;
	}

	/**
	 * 
	 * @return a list of available items
	 */
	public ArrayList<Item> getAvailableItems() {
		ArrayList<Item> availables = new ArrayList<Item>();
		for (Item element : database.getItems()) {
			if (element.isAvailable()) {
				availables.add(element);
			}
		}
		return availables;
	}

	/**
	 * Lend the given item. It receives an item that can be checked out as a
	 * parameter, looks for it in the list and if it finds it, marks it as checked
	 * out and returns it. If it doesn't find it then it returns null.
	 * 
	 * @param item is the one we are searching for
	 * @return a borrowable item if the item is found
	 */
	public Borrowable borrow(Item item) {
		ArgumentChecks.isTrue(item instanceof Borrowable, "The item is not a borrowable item");
		Borrowable borrowable = (Borrowable) this.getItem(item);
		if (borrowable != null) {
			borrowable.borrow();
		}
		return borrowable;
	}

	/**
	 * Returns the given item. It receives an item that can be checked out as a
	 * parameter, looks for it in the list and if it finds it, marks it as returned
	 * and returns it. If it doesn't find it then it returns null
	 * 
	 * @param item is the one we are searching for
	 * @return a borrowable item if the item is found
	 */
	public Borrowable giveBack(Item item) {
		ArgumentChecks.isTrue(item instanceof Borrowable, "The item is not a borrowable item");
		Borrowable borrowable = (Borrowable) this.getItem(item);
		if (borrowable != null) {
			borrowable.giveBack();
		}
		return borrowable;
	}
}
