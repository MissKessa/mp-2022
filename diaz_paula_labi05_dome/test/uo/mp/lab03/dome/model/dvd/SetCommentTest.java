package uo.mp.lab03.dome.model.dvd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;

public class SetCommentTest {
	private Dvd aDVD;
	private String theTitle;
	private String theDirector;
	private double theBasePrice;
	private int theTime;

	@BeforeEach
	public void setUp() {
		theTitle = "Star Wars";
		theDirector = "George Lucas";
		theTime = 125;
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		aDVD = new Dvd(theTitle, theBasePrice, theDirector, theTime);
	}

	/**
	 * Test cases: 1- valid comment 2- invalid comment
	 */

	/**
	 * GIVEN: dvd with default comment WHEN: call setComment with a non-empty string
	 * THEN: comment updates
	 */
	@Test
	public void validComment() {
		aDVD.setComment("Excellent");

		assertEquals("Excellent", aDVD.getComment());
	}

	/**
	 * GIVEN: dvd with non-default comment WHEN: call setComment ( null ) THEN:
	 * comment not changed
	 */
	@Test
	public void invalidComment() {
		aDVD.setComment("Excellent");

		try {
			aDVD.setComment(null);
			fail("An exception should be thrown here.");
		} catch (Exception exception) {
			assertEquals("Excellent", aDVD.getComment());
		}
	}

}
