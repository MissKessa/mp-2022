package uo.mp.lab03.dome.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.lab03.dome.model.Book;
import uo.mp.lab03.dome.model.Item;

public class GetTotalPriceTest {
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
	 * GIVEN: a book with MAXIMUM_BASE_PRICE as basePrice
	 * <p>
	 * WHEN: call getTotalPrice()
	 * <p>
	 * THEN: Returns the total price of the book
	 */
	@Test
	void getTotalPriceCdWithMaxBasePrice() {
		final double totalPrice = theBasePrice + theBasePrice * 0.04;
		assertEquals(totalPrice, aBook.getTotalPrice());
	}

	/**
	 * GIVEN: a book with basePrice 0
	 * <p>
	 * WHEN: call getTotalPrice()
	 * <p>
	 * THEN: Returns the total price of the book
	 */
	@Test
	void getTotalPriceCdWithMinBasePrice() {
		final int minimumPrice = 0;
		aBook.setBasePrice(minimumPrice);
		final double totalPrice = minimumPrice + minimumPrice * 0.04;
		assertEquals(totalPrice, aBook.getTotalPrice());
	}

}
