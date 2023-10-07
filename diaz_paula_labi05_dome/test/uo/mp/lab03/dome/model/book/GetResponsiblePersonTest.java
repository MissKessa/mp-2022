package uo.mp.lab03.dome.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Book;
import uo.mp.lab03.dome.model.Item;

public class GetResponsiblePersonTest {
	private Book aBook;
	private String theTitle;
	private String theAuthor;
	private String thePublisher;
	private String theISBN;
	private double theBasePrice;

	@BeforeEach
	public void setUp() {
		theTitle = "Charlie";
		theAuthor = "Roald Dahl";
		thePublisher = "Coco";
		theISBN = "AAAA111";
		theBasePrice = Item.MAXIMUM_BASE_PRICE;
		aBook = new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
	}

	/**
	 * GIVEN: a book
	 * <p>
	 * WHEN: call getResponsiblePerson()
	 * <p>
	 * THEN: Returns the artist of the book
	 * 
	 */
	@Test
	void getResponsiblePerson() {
		assertEquals(theAuthor, aBook.getResponsiblePerson());
	}

}
