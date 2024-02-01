package uo.mp.newsstand.service.newsstand;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import uo.mp2122.newsstand.exception.NewsstandException;
import uo.mp2122.newsstand.service.Newsstand;

public class NewsStandLoadFileTest {
	private Newsstand ns;

	/**
	 * GIVEN: a valid file
	 * <p>
	 * WHEN: loading it
	 * <p>
	 * THEN: should be load all its contents
	 */
	@Test
	void loadValidFile() {
		ns = new Newsstand();
		try {
			ns.loadFile("publications.txt");
		} catch (FileNotFoundException | NewsstandException e) {
			fail("No exception should be thrown");
		}
	}

	/**
	 * GIVEN: a non existing file
	 * <p>
	 * WHEN: loading it
	 * <p>
	 * THEN: a FileNotFoundException should be thrown
	 */
	@Test
	void loadNonExistingFile() {
		ns = new Newsstand();
		try {
			ns.loadFile("aaaaa.txt");
		} catch (FileNotFoundException e) {
		} catch (NewsstandException e) {
			fail("No exception should be thrown");
		}
	}

	/**
	 * GIVEN: a invalid file with unknown type
	 * <p>
	 * WHEN: loading it
	 * <p>
	 * THEN: those lines are omitted
	 */
	@Test
	void loadInvalidFileUnknownType() {
		ns = new Newsstand();
		try {
			ns.loadFile("publicationsUnknownType.txt");
		} catch (FileNotFoundException e) {
			fail("No exception should be thrown");
		} catch (NewsstandException e) {
		}
	}

	/**
	 * GIVEN: a invalid file with empty lines
	 * <p>
	 * WHEN: loading it
	 * <p>
	 * THEN: those lines are omitted
	 */
	@Test
	void loadInvalidWithEmptyLines() {
		ns = new Newsstand();
		try {
			ns.loadFile("publicationsWithEmptyLines.txt");
		} catch (FileNotFoundException e) {
			fail("No exception should be thrown");
		} catch (NewsstandException e) {
		}
	}

	/**
	 * GIVEN: a invalid file with wrong numbers
	 * <p>
	 * WHEN: loading it
	 * <p>
	 * THEN: those lines are omitted
	 */
	@Test
	void loadInvalidWithWrongNumbers() {
		ns = new Newsstand();
		try {
			ns.loadFile("publicationsWithWrongNumbers.txt");
		} catch (FileNotFoundException e) {
			fail("No exception should be thrown");
		} catch (NewsstandException e) {
		}
	}

	/**
	 * GIVEN: a invalid file with wrong number of fields
	 * <p>
	 * WHEN: loading it
	 * <p>
	 * THEN: those lines are omitted
	 */
	@Test
	void loadInvalidWithWrongNumberOfFields() {
		ns = new Newsstand();
		try {
			ns.loadFile("publicationWrongNumberFields.txt");
		} catch (FileNotFoundException e) {
			fail("No exception should be thrown");
		} catch (NewsstandException e) {
		}
	}

}
