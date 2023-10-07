package uo.mp.lab03.dome.model.cd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Item;

public class SetCommentTest {
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
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		theTracks = 4;
		aCD = new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
	}

	/**
	 * Test cases: 1- right comment 2- invalid comment
	 */
	/**
	 * GIVEN: cd with "No comment" comment WHEN: call setComment with other comment
	 * THEN: comment is changed
	 */

	@Test
	public void rightComment() {
		aCD.setComment("Excellent");

		assertEquals("Excellent", aCD.getComment());
	}

	/**
	 * GIVEN: dvd with non-default comment WHEN: call setComment ( null ) THEN:
	 * comment not changed
	 */
	@Test
	public void invalidComment() {
		aCD.setComment("Excellent");

		try {
			aCD.setComment(null);
			fail("An exception should be thrown here.");
		} catch (Exception exception) {
			assertEquals("Excellent", aCD.getComment());
		}
	}
}
