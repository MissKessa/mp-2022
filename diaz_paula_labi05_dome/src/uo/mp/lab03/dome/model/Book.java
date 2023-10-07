package uo.mp.lab03.dome.model;

import java.util.Objects;

import uo.mp.util.check.ArgumentChecks;

public class Book extends Item implements Borrowable {
	private final String author;
	private final String publisher;
	private final String ISBN;
	private boolean loaned;

	/**
	 * Main constructor. This builder takes all the necessary data such as title,
	 * basePrice author, ISBN to create a Book. The item comment is initialized with
	 * the default value of Item.COMMENT_DEFAULT_VALUE.
	 * 
	 * @param title     is the string value for the title of the Book. It must be
	 *                  not-null, non-empty and non-blank. Otherwise an
	 *                  IllegalArgumentException is thrown.
	 * 
	 * @param basePrice is the base price that will be assigned to the created item.
	 *                  It cannot be negative or greater than
	 *                  {@value #MAXIMUM_BASE_PRICE}
	 * 
	 * @param author    is the string representation of the author of the Book. It
	 *                  must be not-null, non-empty and non-blank. Otherwise an
	 *                  IllegalArgumentException is thrown.
	 * 
	 * @param ISBN      is the string representation of the ISBN of the Book. It
	 *                  must be not-null, non-empty and non-blank. Otherwise an
	 *                  IllegalArgumentException is thrown.
	 * 
	 * @throws IllegalArgumentException if any of the previous parameters value is
	 *                                  not valid.
	 */
	public Book(String title, double basePrice, String author, String publisher, String ISBN) {
		super(title, basePrice);

		ArgumentChecks.isTrue(author != null, "The author cannot be null.");
		ArgumentChecks.isTrue(!author.trim().isEmpty(), "The author cannot be empty nor blank.");
		this.author = author;

		ArgumentChecks.isTrue(publisher != null, "The publisher cannot be null.");
		ArgumentChecks.isTrue(!publisher.trim().isEmpty(), "The publisher cannot be empty nor blank.");
		this.publisher = publisher;

		ArgumentChecks.isTrue(ISBN != null, "The ISBN cannot be null.");
		ArgumentChecks.isTrue(!ISBN.trim().isEmpty(), "The ISBN cannot be empty nor blank.");
		this.ISBN = ISBN;

		this.loaned = false;
	}

	/**
	 * @return if the book is loaned of not
	 */
	@Override
	public boolean isLoaned() {
		return loaned;
	}

	/**
	 * Sets the book to not loaned
	 */
	@Override
	public void giveBack() {
		this.loaned = false;
	}

	/**
	 * Sets the cd to loaned
	 */
	@Override
	public void borrow() {
		this.loaned = true;
	}

	/**
	 * @return if a book is available or not (inStock and not loaned)
	 */
	@Override
	public boolean isAvailable() {
		return super.isAvailable() && !this.loaned;
	}

	/**
	 * @return the value of the author as a non-zero, non-empty and non-blank
	 *         string.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the value of the publisher as a non-zero, non-empty and non-blank
	 *         string.
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @return the value of the ISBN as a non-zero, non-empty and non-blank string.
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * Returns the String representation of a Book. The format is as follow "Book
	 * [code=%s, inStock=%s, comment=%s, publisher=%s, ISBN=%s]".
	 */
	@Override
	public String toString() {
		return String.format("Book [code=%s, inStock=%s, comment=%s, publisher=%s, ISBN=%s]", this.getCode(),
				this.inStock(), this.getComment(), this.publisher, this.ISBN);
	}

	/**
	 * Compares if an item and a Book are equal or not
	 * 
	 * @param item is the item we want to compare with this Book
	 * @return True if the item is equal (has the same title and ISBN)
	 */
	@Override
	public boolean equals(Item item) {
		if (!(item instanceof Book)) {
			return false;
		}
		if (!super.equals(item)) {
			return false;
		}
		Book book = (Book) item;
		return Objects.equals(this.ISBN, book.ISBN);
	}

	/**
	 * @return the value of the responsible person of the Book (author) as a
	 *         non-zero, non-empty and non-blank string.
	 */
	@Override
	public String getResponsiblePerson() {
		return author;
	}

	/**
	 * @return the total price of the Book (base price+ 0.04*base price)
	 */
	@Override
	public double getTotalPrice() {
		final double TAXES = this.getBasePrice() * 0.04;
		return this.getBasePrice() + TAXES;
	}

}
