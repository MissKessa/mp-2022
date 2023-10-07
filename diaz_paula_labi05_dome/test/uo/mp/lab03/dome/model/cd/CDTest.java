package uo.mp.lab03.dome.model.cd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Item;

public class CDTest {

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
	}

	/**
	 * Constructor with Params Test cases: 1- Valid Params 2- Invalid Title. null 3-
	 * Invalid Title. blancks 4- Invalid Artist. null 5- Invalid Artis. Blancks 6-
	 * Invalid playingTime. < 0 7- Invalid tracks. < 0
	 */

	/**
	 * 1 GIVEN: WHEN: new Cd with valid arguments THEN: a new Cd is created with
	 * that attributes
	 */
	@Test
	public void validParams() {
		Cd cd = new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);

		assertEquals("Come Together", cd.getTitle());
		assertEquals("Beatles", cd.getArtist());
		assertEquals(theTime, cd.getPlayingTime());
		assertEquals(theTracks, cd.getNumberOfTracks());
		assertEquals(theBasePrice, cd.getBasePrice());
		assertEquals(false, cd.inStock());
		assertEquals("No comment", cd.getComment());
	}

	/**
	 * GIVEN WHEN Create a cd by passing null instead of title THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void nullTitle() {
		theTitle = null;
		try {
			new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A CD is created by passing a string of blanks for title THEN
	 * IllegalArgumentException throws
	 */
	@Test

	public void blankTitle() {
		theTitle = "    ";
		try {
			new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN Create a CD by passing null instead of artist THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void nullArtist() {
		theArtist = null;
		try {
			new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A Cd is created by passing empty string to artist THEN
	 * IllegalArgumentException throws
	 */
	@Test

	public void blankArtist() {
		theArtist = " ";
		try {
			new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A Cd is created by passing negative value for playingTime THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void negativeTime() {
		theTime = -1;
		try {
			new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A Cd is created by passing negative value for tracks THEN
	 * IllegalArgumentException throws
	 */
	@Test

	public void negativeTracks() {
		theTracks = -1;
		try {
			new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A Cd is created by passing negative value for theBasePrice THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void negativePrice() {
		theBasePrice = -1;
		try {
			new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A Cd is created by passing a value for theBasePrice greater than
	 * MAXIMUM_BASE_PRICE THEN IllegalArgumentException throws
	 */
	@Test
	public void greaterPrice() {
		theBasePrice = Item.MAXIMUM_BASE_PRICE + 1;
		try {
			new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}
}
