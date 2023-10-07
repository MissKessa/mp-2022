package uo.mp.lab03.dome.model.cd;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Item;

public class BorrowTests {
	private Cd aCD;
	private String theTitle;
	private String theArtist;
	private int theTime;
	private double theBasePrice;
	private int theTracks;

	@BeforeEach
	public void setUp() {
		theTitle = "Come Together";
		theArtist = "Beatles";
		theTime = 70;
		theTracks = 4;
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		aCD = new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
	}

	/**
	 * GIVEN: a not loaned cd
	 * <p>
	 * WHEN: call borrow()
	 * <p>
	 * THEN: the cd is loaned
	 */
	@Test
	void borrowNotLoanedCD() {
		assertFalse(aCD.isLoaned());
		aCD.borrow();
		assertTrue(aCD.isLoaned());
	}

	/**
	 * GIVEN: an already loaned cd
	 * <p>
	 * WHEN: call borrow()
	 * <p>
	 * THEN: the cd is loaned
	 */
	@Test
	void borrowLoanedCD() {
		assertFalse(aCD.isLoaned());
		aCD.borrow();
		assertTrue(aCD.isLoaned());

		aCD.borrow();
		assertTrue(aCD.isLoaned());
	}

}
