package uo.mp.lab03.dome.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Book;
import uo.mp.lab03.dome.model.Item;

public class BorrowTests {
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
	 * GIVEN: a not loaned book
	 * <p>
	 * WHEN: call borrow()
	 * <p>
	 * THEN: the book is loaned
	 */
	@Test
	void borrowNotLoanedCD() {
		assertFalse(aBook.isLoaned());
		aBook.borrow();
		assertTrue(aBook.isLoaned());
	}

	/**
	 * GIVEN: an already loaned book
	 * <p>
	 * WHEN: call borrow()
	 * <p>
	 * THEN: the book is loaned
	 */
	@Test
	void borrowLoanedCD() {
		assertFalse(aBook.isLoaned());
		aBook.borrow();
		assertTrue(aBook.isLoaned());

		aBook.borrow();
		assertTrue(aBook.isLoaned());
	}

}
