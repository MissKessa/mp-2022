package uo.mp.lab03.dome.model.medialibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;
import uo.mp.lab03.dome.service.MediaLibrary;

public class GetResponsiblePeople {
	private MediaLibrary library;
	private String theTitle;
	private String theDirector;
	private int theTime;
	private double theBasePrice;
	private Dvd dvd;
	private Cd cd;

	@BeforeEach
	public void setUp() {
		library = new MediaLibrary();
		theTitle = "Star Wars";
		theDirector = "George Lucas";
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		theTime = 125;

		dvd = new Dvd(theTitle, theBasePrice, theDirector, theTime);
		cd = new Cd(theTitle, theBasePrice, theDirector, theTime, theTime);
		library.add(dvd);
		library.add(cd);
	}

	/**
	 * GIVEN: a empty medialibrary
	 * <p>
	 * WHEN: call getResponsiblePeople()
	 * <p>
	 * THEN: Returns an empty string
	 */
	@Test
	void getResponsiblePeopleEmptylibrary() {
		library = new MediaLibrary();
		final StringBuilder responsiblePeople = new StringBuilder();
		assertEquals(responsiblePeople.toString(), library.getResponsiblePeople());
	}

	/**
	 * GIVEN: a empty medialibrary
	 * <p>
	 * WHEN: call getResponsiblePeople()
	 * <p>
	 * THEN: Returns an empty string
	 */
	@Test
	void getResponsiblePeopleLibrary() {
		final String responsiblePeople = dvd.getResponsiblePerson() + ", " + cd.getResponsiblePerson();
		assertEquals(responsiblePeople, library.getResponsiblePeople());
	}

}
