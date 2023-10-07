package uo.mp.lab02.example01.person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uo.mp.lab02.example01.DNI;
import uo.mp.lab02.example01.Person;

class SetDNITest {

	@Test
	void testThatAPersonWithoutDNICanBeSetWithOne() {
		final String validName="willy";
		final Person person= new Person(validName);
		
		final String validDNINumber="123456789";
		final DNI validDNI= new DNI(validDNINumber);
		
		try {
			person.setDNI(validDNI);
		} catch (Exception exception) {
			fail("NO exception should be trown");
		}
	}
	
	@Test
	void testThatAPersonWithDNICannotBeSetWithOne() {
		final String validName="willy";
		final Person person= new Person(validName);
		
		final String validDNINumber="123456789";
		final DNI validDNI= new DNI(validDNINumber);
		person.setDNI(validDNI);
		
		try {
			person.setDNI(validDNI);
			fail("A exception should be trown");
		} catch (Exception exception) {
			
		}
	}
}
