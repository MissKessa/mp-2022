package uo.mp.lab03.dome.model.cd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;

public class EqualsTest {
	private Cd aCD;
	private String theTitle;
	private String theArtist;
	private String theDifferentArtist;
	private double theBasePrice;
	private int theTime;
	private int theTracks;

	@BeforeEach
	public void setUp() {
		theTitle = "Come Together";
		theArtist = "Beatles";
		theDifferentArtist = "Rolling Stones";
		theTime = 70;
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		theTracks = 4;
		aCD = new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
	}

	/**
	 * GIVEN: a cd
	 * <p>
	 * WHEN: call equals(a cd). So they are the same object
	 * <p>
	 * THEN: Returns true because they have the same content
	 * 
	 */
	@Test
	void equalsToSameCD() {
		assertEquals(true, aCD.equals(aCD));
	}

	/**
	 * GIVEN: a cd
	 * <p>
	 * WHEN: call equals(cd2). Cd2 has the same content
	 * <p>
	 * THEN: Returns true because they have the same content
	 * 
	 */
	@Test
	void equalsToIdenticalDVD() {
		final Cd cd2 = new Cd(theTitle, theBasePrice, theArtist, theTracks, theTime);
		assertEquals(true, aCD.equals(cd2));
	}

	/**
	 * GIVEN: a cd
	 * <p>
	 * WHEN: call equals(cd2). cd2 has the same Artist and title
	 * <p>
	 * THEN: Returns true
	 * 
	 */
	@Test
	void equalsToCDWithSameArtist() {
		final Cd cd2 = new Cd(theTitle, theBasePrice, theArtist, theTracks + 1, theTime);
		assertEquals(true, aCD.equals(cd2));
	}

	/**
	 * GIVEN: a cd
	 * <p>
	 * WHEN: call equals(cd2). Cd2 has different content
	 * <p>
	 * THEN: Returns false because they don't have the same content
	 * 
	 */
	@Test
	void equalsToDifferentDVD() {
		final Cd cd2 = new Cd(theTitle, theBasePrice, theDifferentArtist, theTracks, theTime);
		assertEquals(false, aCD.equals(cd2));
	}

	/**
	 * GIVEN: a cd
	 * <p>
	 * WHEN: call equals(dvd)
	 * <p>
	 * THEN: Returns false because they don't have even the same type
	 * 
	 */
	@Test
	void equalsToOtherType() {
		final Dvd aDvd = new Dvd(theTitle, theBasePrice, theArtist, theTime);
		assertEquals(false, aCD.equals(aDvd));
	}

}
