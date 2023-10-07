package uo.mp.lab03.dome.model.medialibrary;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;
import uo.mp.lab03.dome.service.MediaLibrary;

public class BorrowTests {

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
	 * GIVEN: a medialibrary with cd and dvd
	 * <p>
	 * WHEN: call borrow(dvd).
	 * <p>
	 * THEN: Returns an IllegalArgumentException because dvd is not a borrowable
	 * item
	 */
	@Test
	void borrowNotBorrowable() {
		try {
			library.borrow(dvd);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "The item is not a borrowable item");
		}
	}

	/**
	 * GIVEN: a medialibrary with cd and dvd
	 * <p>
	 * WHEN: call borrow(aCD). aCD is not there
	 * <p>
	 * THEN: Returns null because it's not in the medialibrary
	 */
	@Test
	void giveBackCDNotInLibrary() {
		final Cd aCD = new Cd("rosas", theBasePrice, theDirector, theTime, theTime);
		assertEquals(null, library.borrow(aCD));
	}

	/**
	 * GIVEN: a medialibrary with cd and dvd
	 * <p>
	 * WHEN: call borrow(cd)
	 * <p>
	 * THEN: Returns cd and it's borrow
	 */
	@Test
	void giveBackCd() {
		assertEquals(cd, library.borrow(cd));
		assertTrue(cd.isLoaned());
	}

}
