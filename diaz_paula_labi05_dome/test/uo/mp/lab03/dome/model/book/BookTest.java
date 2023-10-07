package uo.mp.lab03.dome.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Book;
import uo.mp.lab03.dome.model.Item;

public class BookTest {
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
	}

	/**
	 * Constructor with Params Test cases: 1- Valid Params 2- Invalid Title. null 3-
	 * Invalid Title. blancks 4- Invalid Artist. null 5- Invalid Artis. Blancks 6-
	 * Invalid playingTime. < 0 7- Invalid tracks. < 0
	 */

	/**
	 * 1 GIVEN: WHEN: new book with valid arguments THEN: a new book is created with
	 * that attributes
	 */
	@Test
	public void validParams() {
		Book book = new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);

		assertEquals(theTitle, book.getTitle());
		assertEquals(theAuthor, book.getAuthor());
		assertEquals(thePublisher, book.getPublisher());
		assertEquals(theISBN, book.getISBN());
		assertEquals(theBasePrice, book.getBasePrice());
		assertEquals(false, book.inStock());
		assertEquals("No comment", book.getComment());
	}

	/**
	 * GIVEN WHEN Create a book by passing null instead of title THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void nullTitle() {
		theTitle = null;
		try {
			new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A book is created by passing a string of blanks for title THEN
	 * IllegalArgumentException throws
	 */
	@Test

	public void blankTitle() {
		theTitle = "    ";
		try {
			new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN Create a book by passing null instead of author THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void nullAuthor() {
		theAuthor = null;
		try {
			new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A book is created by passing a string of blanks for author THEN
	 * IllegalArgumentException throws
	 */
	@Test

	public void blankAuthor() {
		theAuthor = "    ";
		try {
			new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN Create a book by passing null instead of publisher THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void nullPublisher() {
		thePublisher = null;
		try {
			new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A book is created by passing a string of blanks for publisher THEN
	 * IllegalArgumentException throws
	 */
	@Test

	public void blankPublisher() {
		thePublisher = "    ";
		try {
			new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN Create a book by passing null instead of ISBN THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void nullISBN() {
		theISBN = null;
		try {
			new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A book is created by passing a string of blanks for ISBN THEN
	 * IllegalArgumentException throws
	 */
	@Test

	public void blankISBN() {
		theISBN = "    ";
		try {
			new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A book is created by passing negative value for theBasePrice THEN
	 * IllegalArgumentException throws
	 */
	@Test
	public void negativePrice() {
		theBasePrice = -1;
		try {
			new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}

	/**
	 * GIVEN WHEN A book is created by passing a value for theBasePrice greater than
	 * MAXIMUM_BASE_PRICE THEN IllegalArgumentException throws
	 */
	@Test
	public void greaterPrice() {
		theBasePrice = Item.MAXIMUM_BASE_PRICE + 1;
		try {
			new Book(theTitle, theBasePrice, theAuthor, thePublisher, theISBN);
			fail("Exception expected");
		} catch (IllegalArgumentException e) {
		}
	}
}
