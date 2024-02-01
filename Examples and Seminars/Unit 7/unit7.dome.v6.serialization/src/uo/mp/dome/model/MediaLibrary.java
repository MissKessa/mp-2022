package uo.mp.dome.model;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uo.mp.dome.model.items.Item;

@SuppressWarnings("serial")
public class MediaLibrary implements Serializable {

	private List<Item> items = new ArrayList<>();
	private List<Borrowable> borrowableItems = new ArrayList<>();

	public void add(Item item) {
		if (item == null) throw new RuntimeException("Item cannot be null");
		if (items.contains(item)) return;
		
		items.add(item);
		if (item instanceof Borrowable) {
			borrowableItems.add((Borrowable) item);
		}
	}

	public int getnumberOfItems() {
		return items.size();
	}

	public int getnumberOfItemsOwned() {
		int count = 0;
		for (Item i : items) {
			if (i.getOwn()) {
				count++;
			}
		}
		return count;
	}

	public void list(PrintStream out) {
		for (Item i : items) {
			i.print(out);
		}
	}

	public void listBorrowableItems(PrintStream out) {
		for (Borrowable item : this.borrowableItems) {
			out.println(item);
		}
	}

	public void listAvailableItems(PrintStream out) {
		for (Borrowable item : this.borrowableItems) {
			if (item.isAvailable()) {
				out.println(item);
			}
		}
	}

	public Item search(Item item) {
		for (Item i : items) {
			if (i.equals(item))
				return i;
		}
		return null;
	}

	public Borrowable borrow(Borrowable item) {
		Borrowable theItem = this.search(item);
		if (theItem == null) return null; // not found
		if (!theItem.isAvailable()) return null; // not available

		theItem.borrow();
		return theItem;
	}

	private Borrowable search(Borrowable item) {
		for (Borrowable i : this.borrowableItems) {
			if (i.equals(item)) {
				return i;
			}
		}
		return null;
	}

	public boolean giveBack(Borrowable item) {
		Borrowable theItem = this.search(item);
		if (theItem == null) return false; // not found
		if ( theItem.isAvailable() ) return false; // already available
		
		theItem.giveBack();
		return true; // correct!
	}

}
