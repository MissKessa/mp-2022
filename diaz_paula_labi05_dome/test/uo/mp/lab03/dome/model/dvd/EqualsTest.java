package uo.mp.lab03.dome.model.dvd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;

public class EqualsTest {
	private Dvd aDvd;
	private String theTitle;
	private String theDirector;
	private double theBasePrice;
	private String theDifferentDirector;
	private int theTime;

	@BeforeEach
	public void setUp() {
		theTitle = "Star Wars";
		theDirector = "George Lucas";
		theDifferentDirector = "Paula";
		theTime = 125;
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		aDvd = new Dvd(theTitle, theBasePrice, theDirector, theTime);
	}

	/**
	 * GIVEN: a dvd
	 * <p>
	 * WHEN: call equals(a dvd). So they are the same object
	 * <p>
	 * THEN: Returns true because they have the same content
	 * 
	 */
	@Test
	void equalsToSameDVD() {
		assertEquals(true, aDvd.equals(aDvd));
	}

	/**
	 * GIVEN: a dvd
	 * <p>
	 * WHEN: call equals(dvd2). dvd2 has the same content
	 * <p>
	 * THEN: Returns true because they have the same content
	 * 
	 */
	@Test
	void equalsToIdenticalDVD() {
		final Dvd dvd2 = new Dvd(theTitle, theBasePrice, theDirector, theTime);
		assertEquals(true, aDvd.equals(dvd2));
	}

	/**
	 * GIVEN: a dvd
	 * <p>
	 * WHEN: call equals(dvd2). dvd2 has the same content
	 * <p>
	 * THEN: Returns true
	 * 
	 */
	@Test
	void equalsToDVDWithSameDirector() {
		final Dvd dvd2 = new Dvd(theTitle, theBasePrice, theDirector, theTime + 1);
		assertEquals(true, aDvd.equals(dvd2));
	}

	/**
	 * GIVEN: a dvd
	 * <p>
	 * WHEN: call equals(dvd2). dvd2 has different content
	 * <p>
	 * THEN: Returns false because they don't have the same content
	 * 
	 */
	@Test
	void equalsToDifferentDVD() {
		final Dvd dvd2 = new Dvd(theTitle, theBasePrice, theDifferentDirector, theTime + 100);
		assertEquals(false, aDvd.equals(dvd2));
	}

	/**
	 * GIVEN: a dvd
	 * <p>
	 * WHEN: call equals(cd)
	 * <p>
	 * THEN: Returns false because they don't have even the same type
	 * 
	 */
	@Test
	void equalsToOtherType() {
		final Cd cd = new Cd(theTitle, theBasePrice, theDirector, theTime, theTime);
		assertEquals(false, aDvd.equals(cd));
	}

}
