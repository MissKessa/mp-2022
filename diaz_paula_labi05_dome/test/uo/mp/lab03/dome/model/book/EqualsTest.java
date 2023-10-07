package uo.mp.lab03.dome.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Book;
import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.model.Item;

public class EqualsTest {

	private Book aBook;
	private String theTitle;
	private String theAuthor;
	private String theDifferentAuthor;
	private String thePublisher;
	private String theDifferentPublisher;
	private String theISBN;
	private String theDifferentISBN;
	private double theBasePrice;

	@BeforeEach
	public void setUp() {
		theTitle = "Charlie";
		theAuthor = "Roald Dahl";
		theDifferentAuthor = "Paco";
		thePublisher = "Coco";
		theDifferentPublisher = "Naranja";
		theISBN = "AAAA1111";
		theDifferentISBN = "BBB111";
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		aBook = new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
	}

	/**
	 * GIVEN: a book
	 * <p>
	 * WHEN: call equals(a book). So they are the same object
	 * <p>
	 * THEN: Returns true because they have the same content
	 * 
	 */
	@Test
	void equalsToSamebook() {
		assertEquals(true, aBook.equals(aBook));
	}

	/**
	 * GIVEN: a book
	 * <p>
	 * WHEN: call equals(book2). book2 has the same content
	 * <p>
	 * THEN: Returns true because they have the same content
	 * 
	 */
	@Test
	void equalsToIdenticalBook() {
		final Book book2 = new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
		assertEquals(true, aBook.equals(book2));
	}

	/**
	 * GIVEN: a book
	 * <p>
	 * WHEN: call equals(book2). book2 has the same ISBN and title
	 * <p>
	 * THEN: Returns true
	 * 
	 */
	@Test
	void equalsToBookWithSameISBN() {
		final Book book2 = new Book(theTitle, theBasePrice, theDifferentAuthor, theDifferentPublisher, theISBN);
		assertEquals(true, aBook.equals(book2));
	}

	/**
	 * GIVEN: a book
	 * <p>
	 * WHEN: call equals(book2). book2 has different content
	 * <p>
	 * THEN: Returns false because they don't have the same content
	 * 
	 */
	@Test
	void equalsToDifferentBook() {
		final Book book2 = new Book(theTitle, theBasePrice, theDifferentAuthor, theDifferentPublisher,
				theDifferentISBN);
		assertEquals(false, aBook.equals(book2));
	}

	/**
	 * GIVEN: a book
	 * <p>
	 * WHEN: call equals(dvd)
	 * <p>
	 * THEN: Returns false because they don't have even the same type
	 * 
	 */
	@Test
	void equalsToOtherType() {
		final Dvd aDvd = new Dvd(theTitle, theBasePrice, theAuthor, 1);
		assertEquals(false, aBook.equals(aDvd));
	}

}
