package uo.mp.lab03.dome.model.cd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Item;

public class SetOwnTest {
	private Cd aCD;
	private String theTitle;
	private String theArtist;
	private double theBasePrice;
	private int theTime;
	private int theTracks;

	@BeforeEach
	public void setUp() {
		theTitle = "Come Together";
		theArtist = "Beatles";
		theTime = 70;
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		theTracks = 4;
		aCD = new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
	}

	/**
	 * Test cases: 1. when isOwned to owned 2- when isNotOwned to owned 3- when is
	 * not owned to owned 4- when is not owned to not owned
	 */

	/**
	 * GIVEN: cd is owned WHEN: call setOwn with true THEN: unchanged
	 * 
	 */
	@Test
	public void trueToTrue() {
		aCD.setInStock(true);
		aCD.setInStock(true);
		assertEquals(true, aCD.inStock());
	}

	/**
	 * GIVEN: cd owned WHEN: call setOwn ( false ) THEN: cd not owned
	 * 
	 */
	@Test
	public void trueToFalse() {
		aCD.setInStock(true);
		aCD.setInStock(false);
		assertEquals(false, aCD.inStock());
	}

	/**
	 * GIVEN: cd not owned WHEN: call setOwn ( false ) THEN: cd not owned
	 * 
	 */
	@Test
	public void falseToFalse() {
		aCD.setInStock(false);
		aCD.setInStock(false);
		assertEquals(false, aCD.inStock());
	}

	/**
	 * GIVEN: cd not owned WHEN: call setOwn ( true ) THEN: cd owned
	 * 
	 */
	@Test
	public void falseToTrue() {
		aCD.setInStock(false);
		aCD.setInStock(true);
		assertEquals(true, aCD.inStock());
	}
}
