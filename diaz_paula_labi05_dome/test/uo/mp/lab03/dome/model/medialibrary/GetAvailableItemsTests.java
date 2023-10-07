package uo.mp.lab03.dome.model.medialibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;
import uo.mp.lab03.dome.service.MediaLibrary;

public class GetAvailableItemsTests {
	private MediaLibrary library;
	private String theTitle;
	private String theDirector;
	private int theTime;
	private double theBasePrice;
	private Dvd dvd;
	private Cd cd;
	private ArrayList<Item> availables;
	private Cd cd2;
	private Dvd dvd2;
	private Cd cd3;

	@BeforeEach
	public void setUp() {
		library = new MediaLibrary();
		theTitle = "Star Wars";
		theDirector = "George Lucas";
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		theTime = 125;

		// not in stock
		dvd = new Dvd(theTitle, theBasePrice, theDirector, theTime);
		dvd.setInStock(false);
		cd = new Cd(theTitle, theBasePrice, theDirector, theTime, theTime);
		cd.setInStock(false);

		// in stock and not loaned
		dvd2 = new Dvd(theTitle, theBasePrice, theDirector, theTime);
		dvd2.setInStock(true);
		cd2 = new Cd(theTitle, theBasePrice, theDirector, theTime, theTime);
		cd2.setInStock(true);
		cd2.giveBack();

		// in stock and loaned
		cd3 = new Cd(theTitle, theBasePrice, theDirector, theTime, theTime);
		cd3.setInStock(true);
		cd3.borrow();

		library.add(dvd);
		library.add(cd);
		library.add(dvd2);
		library.add(cd2);
		library.add(cd3);

		availables = new ArrayList<Item>();
		availables.add(dvd2);
		availables.add(cd2);
	}

	/**
	 * GIVEN: an empty medialibrary
	 * <p>
	 * WHEN: call getAvailableItems()
	 * <p>
	 * THEN: Returns an empty Arraylist
	 */
	@Test
	void getBorrowableItemsEmptyLibrary() {
		library = new MediaLibrary();
		availables = new ArrayList<Item>();
		assertEquals(availables, library.getAvailableItems());
	}

	/**
	 * GIVEN: a medialibrary with a cd, dvd, cd2, dvd2 and cd3
	 * <p>
	 * WHEN: call getBorrowableItems()
	 * <p>
	 * THEN: Returns an arraylist with the cd2 and dvd2
	 */
	@Test
	void getBorrowableItemsLibrary() {
		assertEquals(availables, library.getAvailableItems());
	}

}
