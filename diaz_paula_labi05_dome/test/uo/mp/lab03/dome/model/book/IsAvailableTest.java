package uo.mp.lab03.dome.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Book;
import uo.mp.lab03.dome.model.Item;

public class IsAvailableTest {
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
	 * GIVEN: a not loaned book and not instock
	 * <p>
	 * WHEN: call isAvailable()
	 * <p>
	 * THEN: returns false because it's not instock
	 */
	@Test
	void isAvailableNotLoanedNotInStockCD() {
		aBook.giveBack();
		assertFalse(aBook.isLoaned());
		aBook.setInStock(false);
		assertFalse(aBook.inStock());
		assertFalse(aBook.isAvailable());
	}

	/**
	 * GIVEN: a loaned book and not instock
	 * <p>
	 * WHEN: call isAvailable()
	 * <p>
	 * THEN: returns false because it's not instock and loaned
	 */
	@Test
	void isAvailableLoanedNotInStockCD() {
		aBook.borrow();
		assertTrue(aBook.isLoaned());
		aBook.setInStock(false);
		assertFalse(aBook.inStock());
		assertFalse(aBook.isAvailable());
	}

	/**
	 * GIVEN: a not loaned book and instock
	 * <p>
	 * WHEN: call isAvailable()
	 * <p>
	 * THEN: returns true
	 */
	@Test
	void isAvailableNotLoanedInStockCD() {
		aBook.giveBack();
		assertFalse(aBook.isLoaned());
		aBook.setInStock(true);
		assertTrue(aBook.inStock());
		assertTrue(aBook.isAvailable());
	}

	/**
	 * GIVEN: a loaned book and instock
	 * <p>
	 * WHEN: call isAvailable()
	 * <p>
	 * THEN: returns false because it's loaned
	 */
	@Test
	void isAvailableLoanedInStockCD() {
		aBook.borrow();
		assertTrue(aBook.isLoaned());
		aBook.setInStock(true);
		assertTrue(aBook.inStock());
		assertFalse(aBook.isAvailable());
	}

}
