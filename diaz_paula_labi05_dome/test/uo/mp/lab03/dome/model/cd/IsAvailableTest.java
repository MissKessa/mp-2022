package uo.mp.lab03.dome.model.cd;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Item;

public class IsAvailableTest {
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
	 * GIVEN: a not loaned cd and not instock
	 * <p>
	 * WHEN: call isAvailable()
	 * <p>
	 * THEN: returns false because it's not instock
	 */
	@Test
	void isAvailableNotLoanedNotInStockCD() {
		aCD.giveBack();
		assertFalse(aCD.isLoaned());
		aCD.setInStock(false);
		assertFalse(aCD.inStock());
		assertFalse(aCD.isAvailable());
	}

	/**
	 * GIVEN: a loaned cd and not instock
	 * <p>
	 * WHEN: call isAvailable()
	 * <p>
	 * THEN: returns false because it's not instock and loaned
	 */
	@Test
	void isAvailableLoanedNotInStockCD() {
		aCD.borrow();
		assertTrue(aCD.isLoaned());
		aCD.setInStock(false);
		assertFalse(aCD.inStock());
		assertFalse(aCD.isAvailable());
	}

	/**
	 * GIVEN: a not loaned cd and instock
	 * <p>
	 * WHEN: call isAvailable()
	 * <p>
	 * THEN: returns true
	 */
	@Test
	void isAvailableNotLoanedInStockCD() {
		aCD.giveBack();
		assertFalse(aCD.isLoaned());
		aCD.setInStock(true);
		assertTrue(aCD.inStock());
		assertTrue(aCD.isAvailable());
	}

	/**
	 * GIVEN: a loaned cd and instock
	 * <p>
	 * WHEN: call isAvailable()
	 * <p>
	 * THEN: returns false because it's loaned
	 */
	@Test
	void isAvailableLoanedInStockCD() {
		aCD.borrow();
		assertTrue(aCD.isLoaned());
		aCD.setInStock(true);
		assertTrue(aCD.inStock());
		assertFalse(aCD.isAvailable());
	}

}
