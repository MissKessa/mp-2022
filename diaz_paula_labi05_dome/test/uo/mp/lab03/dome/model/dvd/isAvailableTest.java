package uo.mp.lab03.dome.model.dvd;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;

public class isAvailableTest {
	private Dvd aDvd;
	private String theTitle;
	private String theDirector;
	private double theBasePrice;
	private int theTime;

	@BeforeEach
	public void setUp() {
		theTitle = "Star Wars";
		theDirector = "George Lucas";
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		theTime = 125;
		aDvd = new Dvd(theTitle, theBasePrice, theDirector, theTime);
	}

	/**
	 * GIVEN: a dvd that is not instock
	 * <p>
	 * WHEN: call isAvailable()
	 * <p>
	 * THEN: returns false because it's not instock
	 */
	@Test
	void isAvailableNotInStockDVD() {
		aDvd.setInStock(false);
		assertFalse(aDvd.inStock());
		assertFalse(aDvd.isAvailable());
	}

	/**
	 * GIVEN: a dvd that is instock
	 * <p>
	 * WHEN: call isAvailable()
	 * <p>
	 * THEN: returns true
	 */
	@Test
	void isAvailableInStockDVD() {
		aDvd.setInStock(true);
		assertTrue(aDvd.inStock());
		assertTrue(aDvd.isAvailable());
	}

}
