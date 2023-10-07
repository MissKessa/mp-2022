package uo.mp.lab03.dome.model.dvd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;

public class DvdTest {

	private Dvd aDvd;
	private String theTitle;
	private String theDirector;
	private int theTime;
	private double theBasePrice;

	@BeforeEach
	public void setUp() {
		theTitle = "Star Wars";
		theDirector = "George Lucas";
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		theTime = 125;
	}

	/**
	 * Cases 1- Valid params 2- Title null 3- Blank title 4- null director 5- blank
	 * director 6- negative playing time
	 */

	/**
	 * GIVEN: WHEN: A Dvd is created with valid parameters THEN: is created and
	 * values ​​are assigned to attributes
	 */
	@Test
	public void validParams() {
		aDvd = new Dvd(theTitle, theBasePrice, theDirector, theTime);

		assertEquals(theTitle, aDvd.getTitle());
		assertEquals(theDirector, aDvd.getDirector());
		assertEquals(theTime, aDvd.getPlayingTime());
		assertEquals(theBasePrice, aDvd.getBasePrice());
		assertEquals(false, aDvd.inStock());
		assertEquals("No comment", aDvd.getComment());
	}

	/**
	 * GIVEN WHEN A DVD is created by passing null instead of title THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void nullTitle() {
		theTitle = null;
		try {
			new Dvd(theTitle, theBasePrice, theDirector, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A DVD is created passing blanks for title THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void blankTitle() {
		theTitle = "    ";
		try {
			new Dvd(theTitle, theBasePrice, theDirector, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A DVD is created by passing null instead of director THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void nullDirector() {
		theDirector = null;
		try {
			new Dvd(theTitle, theBasePrice, theDirector, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A blancks chain DVD is created instead of director THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void blankDirector() {
		theDirector = "   ";
		try {
			new Dvd(theTitle, theBasePrice, theDirector, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A DVD with negative time is created THEN IllegalArgumentException
	 * throws
	 */
	@Test
	public void negativeTime() {
		theTime = -1;
		try {
			new Dvd(theTitle, theBasePrice, theDirector, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A DVD with negative basePrice is created THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void negativePrice() {
		theBasePrice = -1;
		try {
			new Dvd(theTitle, theBasePrice, theDirector, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A DVD is created with a basePrice greater than MAXIMUM_BASE_PRICE
	 * THEN IllegalArgumentException throws
	 */
	@Test
	public void greaterPrice() {
		theBasePrice = Item.MAXIMUM_BASE_PRICE + 1;
		try {
			new Dvd(theTitle, theBasePrice, theDirector, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

}
