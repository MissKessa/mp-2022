package uo.mp.lab03.dome.model.cd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Item;

public class GetResponsiblePersonTest {
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
	 * GIVEN: a cd
	 * <p>
	 * WHEN: call getResponsiblePerson()
	 * <p>
	 * THEN: Returns the artist of the cd
	 * 
	 */
	@Test
	void getResponsiblePerson() {
		assertEquals(theArtist, aCD.getResponsiblePerson());
	}

}
