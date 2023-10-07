package uo.mp.lab03.dome.model.dvd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;

public class GetResponsiblePersonTest {
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
	 * GIVEN: a dvd
	 * <p>
	 * WHEN: call getResponsiblePerson()
	 * <p>
	 * THEN: Returns the artist of the dvd
	 * 
	 */
	@Test
	void getResponsiblePerson() {
		assertEquals(theDirector, aDvd.getResponsiblePerson());
	}

}
