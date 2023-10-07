package uo.mp.lab03.dome.model;

import java.util.Objects;

import uo.mp.util.check.ArgumentChecks;

/**
 * In the context of the multimedia content library an item refers to the
 * abstraction that encompasses any item that exists in it. As such it has a
 * title, a comment and a flag that indicates whether we have the item
 * physically in the library or not. In addition, once the item is created, the
 * title cannot be changed. The rest of the fields can change their value. By
 * default the items are created with the DEFAULT_COMMENT_VALUE comment.
 *
 * @author Paula Díaz Álvarez
 * @version 2023
 */
public abstract class Item {

	private static final String COMMENT_DEFAULT_VALUE = "No comment";
	private static final boolean IN_STOCK_DEFAULT_VALUE = false;
	/**
	 * It's the maximum value (included) that the basePrice field can take
	 */
	public static final double MAXIMUM_BASE_PRICE = 1000;

	private final String title;
	private double basePrice;
	private boolean inStock = IN_STOCK_DEFAULT_VALUE;
	private String comment = COMMENT_DEFAULT_VALUE;

	/**
	 * Main constructor for the item object. This constructor receives the title to
	 * be given to the instantiated item. All other fields take the default value.
	 * The title cannot be null or consist only of spaces or be empty. If any of
	 * these circumstances occurs, an IllegalArgumentException is thrown.
	 * 
	 * @param title     is the title that will be assigned to the created item. It
	 *                  cannot be null or empty or consist only of spaces. If any of
	 *                  these situations occurs, an IllegalArgumentException is
	 *                  thrown.
	 * @param basePrice is the base price that will be assigned to the created item.
	 *                  It cannot be negative or greater than
	 *                  {@value #MAXIMUM_BASE_PRICE}
	 * @throws IllegalArgumentException if the title is null, empty or blank.
	 */
	public Item(String title, double basePrice) {
		ArgumentChecks.isTrue(title != null, "The title cannot be null.");
		ArgumentChecks.isTrue(!title.isBlank(), "The title cannot be empty nor blank.");
		this.title = title;
		setBasePrice(basePrice);
	}

	/**
	 * Modifies the value of the basePrice field of the current instance.
	 * 
	 * @param basePrice is the value to assign to the basePrice field of the
	 *                  instance.
	 * @throws IllegalArgumentException if the basePrice is negative or greater than
	 *                                  {@value #MAXIMUM_BASE_PRICE}
	 * 
	 */
	public void setBasePrice(double basePrice) {
		ArgumentChecks.isTrue(basePrice >= 0 && basePrice <= MAXIMUM_BASE_PRICE,
				"The basePrice must be [0," + MAXIMUM_BASE_PRICE + "]");
		this.basePrice = basePrice;
	}

	/**
	 * Modifies the value of the inStock field of the current instance.
	 * 
	 * @param inStock is the value to assign to the inStock field of the instance.
	 *                No param nor state validation is done.
	 */
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	/**
	 * Modifies the value of the comment of the current instance. To do so, it
	 * receives the new value of the comment as a String that must not be null,
	 * empty or blank.
	 * 
	 * @param comment is the new string value to set to the comment field of the
	 *                instance. Cannot be null, blank nor empty.
	 * @throws IllegalArgumentException if the comment is either null, empty or
	 *                                  blank.
	 */
	public void setComment(String comment) {
		ArgumentChecks.isTrue(comment != null, "The comment cannot be null.");
		ArgumentChecks.isTrue(!comment.isBlank(), "The comment cannot be empty nor blank.");
		this.comment = comment;
	}

	/**
	 * @return the value of the comment as a non-null, non-empty, non-blank string.
	 */
	public String getComment() {
		return this.comment;
	}

	/**
	 * @return the value that indicates whether an item is physically in the library
	 *         or not. If the value is true, the item is physically in the library.
	 *         If false then it is not.
	 */
	public boolean inStock() {
		return this.inStock;
	}

	/**
	 * @return the value of the item title as a non-null, non-empty, non-blank
	 *         string.
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Returns the String representation of an Item. The format is as follow "Item
	 * [title=%s, inStock=%s, comment=%s, playingTime=%d]".
	 */
	@Override
	public String toString() {
		return String.format("[title=%s, inStock=%s, comment=%s]", this.title, this.inStock, this.comment);
	}

	/**
	 * Returns a string made up of the first 2 letters of the title, a hyphen, the
	 * first 2 letters of the person in charge. Everything must be in lower case.
	 */
	public String getCode() {
		return String.format("%s-%s", this.getTitle().substring(0, 2).toLowerCase(),
				this.getResponsiblePerson().substring(0, 2).toLowerCase());
	}

	/**
	 * Compares if 2 items are equal or not in contents
	 * 
	 * @param item is the item we want to compare with this object
	 * @return True if the item is equal (has the same attributes with same content)
	 *         to this item
	 */
	public boolean equals(Item item) {
		if (item == null) {
			return false;
		}
		return Objects.equals(this.title, item.title);
	}

	public abstract String getResponsiblePerson();

	/**
	 * @return the value of the item basePrice as a non-negative double less than
	 *         {@value #MAXIMUM_BASE_PRICE}
	 */
	public double getBasePrice() {
		return basePrice;
	}

	public abstract double getTotalPrice();

	/**
	 * @return if an item is available or not
	 */
	public boolean isAvailable() {
		return inStock;
	}

}
