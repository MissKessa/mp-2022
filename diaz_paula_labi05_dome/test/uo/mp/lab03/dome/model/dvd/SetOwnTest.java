package uo.mp.lab03.dome.model.dvd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;

public class SetOwnTest {
	private Dvd aDVD;
	private String theTitle;
	private String theDirector;
	private double theBasePrice;
	private int theTime;

	@BeforeEach
	public void setUp() {
		theTitle = "La guerra de las Galaxias";
		theDirector = "George Lucas";
		theTime = 125;
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		aDVD = new Dvd(theTitle, theBasePrice, theDirector, theTime);
	}

	/**
	 * Test cases: being own to own being own to not own being not own to not own
	 * being not own to own
	 */

	/**
	 * GIVEN: dvd owned WHEN: call setOwn ( true ) THEN: dvd not changed
	 * 
	 */
	@Test
	public void trueToTrue() {
		aDVD.setInStock(true);
		aDVD.setInStock(true);
		assertEquals(true, aDVD.inStock());
	}

	/**
	 * GIVEN: dvd owned WHEN: call setOwn ( false ) THEN: dvd not owned
	 * 
	 */
	@Test
	public void trueToFalse() {
		aDVD.setInStock(true);
		aDVD.setInStock(false);
		assertEquals(false, aDVD.inStock());
	}

	/**
	 * GIVEN: dvd not owned WHEN: call setOwn ( false ) THEN: dvd not changed
	 * 
	 */
	@Test
	public void falseToFalse() {
		aDVD.setInStock(false);
		aDVD.setInStock(false);
		assertEquals(false, aDVD.inStock());
	}

	/**
	 * GIVEN: dvd not owned WHEN: call setOwn ( true ) THEN: dvd owned
	 * 
	 */
	@Test
	public void falseToTrue() {
		aDVD.setInStock(false);
		aDVD.setInStock(true);
		assertEquals(true, aDVD.inStock());
	}

}
