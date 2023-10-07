package uo.mp.lab03.dome.model.medialibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;
import uo.mp.lab03.dome.service.MediaLibrary;

public class GetItemTest {
	private MediaLibrary library;
	private String theTitle;
	private String theDirector;
	private int theTime;
	private double theBasePrice;
	private Dvd dvd;
	private Cd cd;

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
	}

	/**
	 * GIVEN: a medialibrary
	 * <p>
	 * WHEN: call getItem(aDvd). aDvd is not in the library
	 * <p>
	 * THEN: Returns null because aDvd is not in the library
	 */
	@Test
	void findingAnItemThatItIsNoThere() {
		final Dvd aDvd = new Dvd("a", Item.MAXIMUM_BASE_PRICE, "b", 1);

		assertEquals(null, library.getItem(aDvd));
	}

	/*
	 * GIVEN: a medialibrary <p> WHEN: call getItem(item). item is in the library
	 * <p> THEN: Returns a reference to the item because it's in the library
	 * 
	 * @Test void findingAnItem() { assertEquals(item, library.getItem(item)); }
	 */

	/**
	 * GIVEN: a medialibrary
	 * <p>
	 * WHEN: call getItem(dvd). dvd is in the library
	 * <p>
	 * THEN: Returns a reference to the dvd because it's in the library
	 */
	@Test
	void findingADvd() {
		assertEquals(dvd, library.getItem(dvd));
	}

	/**
	 * GIVEN: a medialibrary
	 * <p>
	 * WHEN: call getItem(cd). cd is in the library
	 * <p>
	 * THEN: Returns a reference to the cd because it's in the library
	 */
	@Test
	void findingACd() {
		assertEquals(cd, library.getItem(cd));
	}
}
