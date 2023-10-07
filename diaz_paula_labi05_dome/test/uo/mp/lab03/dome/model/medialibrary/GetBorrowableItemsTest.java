package uo.mp.lab03.dome.model.medialibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;
import uo.mp.lab03.dome.service.MediaLibrary;

public class GetBorrowableItemsTest {
	private MediaLibrary library;
	private String theTitle;
	private String theDirector;
	private int theTime;
	private double theBasePrice;
	private Dvd dvd;
	private Cd cd;
	private ArrayList<Item> borrowables;

	@BeforeEach
	public void setUp() {
		library = new MediaLibrary();
		theTitle = "Star Wars";
		theDirector = "George Lucas";
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		theTime = 125;

		dvd = new Dvd(theTitle, theBasePrice, theDirector, theTime);
		cd = new Cd(theTitle, theBasePrice, theDirector, theTime, theTime);
		library.add(dvd);
		library.add(cd);
		borrowables = new ArrayList<Item>();
		borrowables.add(cd);
	}

	/**
	 * GIVEN: an empty medialibrary
	 * <p>
	 * WHEN: call getBorrowableItems()
	 * <p>
	 * THEN: Returns an empty Arraylist
	 */
	@Test
	void getBorrowableItemsEmptyLibrary() {
		library = new MediaLibrary();
		borrowables = new ArrayList<Item>();
		assertEquals(borrowables, library.getBorrowableItems());
	}

	/**
	 * GIVEN: a medialibrary with a cd and dvd
	 * <p>
	 * WHEN: call getBorrowableItems()
	 * <p>
	 * THEN: Returns an arraylist with the cd
	 */
	@Test
	void getBorrowableItemsLibrary() {
		assertEquals(borrowables, library.getBorrowableItems());
	}

}
