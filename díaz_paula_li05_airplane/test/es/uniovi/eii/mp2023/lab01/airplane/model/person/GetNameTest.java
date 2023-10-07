package es.uniovi.eii.mp2023.lab01.airplane.model.person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import es.uniovi.eii.mp2023.lab01.airplane.model.Person;

class GetNameTest {

	@Test
	void getNameFirWillyTest() {
		Person willy = new Person ("willy",18);
		assertEquals ("willy",willy.getName());
	}

}
