package uo.mp.lab03.dome.model.dvd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;

public class GetTotalPriceTest {
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
	 * GIVEN: a dvd with MAXIMUM_BASE_PRICE as basePrice
	 * <p>
	 * WHEN: call getTotalPrice()
	 * <p>
	 * THEN: Returns the total price of the dvd
	 */
	@Test
	void getTotalPriceDvdWithMaxBasePrice() {
		final double totalPrice = theBasePrice + Dvd.TAXES;
		assertEquals(totalPrice, aDvd.getTotalPrice());
	}

	/**
	 * GIVEN: a dvd with basePrice 0
	 * <p>
	 * WHEN: call getTotalPrice()
	 * <p>
	 * THEN: Returns the total price of the dvd
	 */
	@Test
	void getTotalPriceDvdWithMinBasePrice() {
		final int minimumPrice = 0;
		aDvd.setBasePrice(minimumPrice);
		final double totalPrice = minimumPrice + Dvd.TAXES;
		assertEquals(totalPrice, aDvd.getTotalPrice());
	}

}
