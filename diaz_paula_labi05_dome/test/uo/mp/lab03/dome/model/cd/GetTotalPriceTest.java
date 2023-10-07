package uo.mp.lab03.dome.model.cd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Item;

public class GetTotalPriceTest {
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
	 * GIVEN: a cd with MAXIMUM_BASE_PRICE as basePrice
	 * <p>
	 * WHEN: call getTotalPrice()
	 * <p>
	 * THEN: Returns the total price of the cd
	 */
	@Test
	void getTotalPriceCdWithMaxBasePrice() {
		final double totalPrice = theBasePrice + Cd.TAXES;
		assertEquals(totalPrice, aCD.getTotalPrice());
	}

	/**
	 * GIVEN: a cd with basePrice 0
	 * <p>
	 * WHEN: call getTotalPrice()
	 * <p>
	 * THEN: Returns the total price of the cd
	 */
	@Test
	void getTotalPriceCdWithMinBasePrice() {
		final int minimumPrice = 0;
		aCD.setBasePrice(minimumPrice);
		final double totalPrice = minimumPrice + Cd.TAXES;
		assertEquals(totalPrice, aCD.getTotalPrice());
	}

}
