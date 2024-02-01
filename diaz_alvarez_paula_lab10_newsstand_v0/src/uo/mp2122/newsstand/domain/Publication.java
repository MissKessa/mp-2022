package uo.mp2122.newsstand.domain;

import uo.mp.util.check.ArgumentChecks;

/**
 * The Publication class represents a publication that can be sold at a
 * newsstand. This is an abstract class that provides common functionality for
 * magazines and newspapers. The class has the following attributes: name: the
 * name of the publication instock: the quantity of the publication in stock
 * sales: the quantity of the publication sold The class provides the following
 * methods: generateOrders(): an abstract method that generates orders for the
 * publication serialize(): an abstract method that serializes the publication
 * to a string getName(): a method that returns the name of the publication
 * getStock(): a method that returns the quantity of the publication in stock
 * getSales(): a method that returns the quantity of the publication sold
 * toString(): a method that returns a string representation of the publication
 * hashCode(): a method that returns the hash code of the publication
 * equals(Object obj): a method that checks if the publication is equal to
 * another object.
 *
 * @author Programming Methodology Teaching Staff
 * @version 2023
 */
public abstract class Publication implements Comparable<Publication> {
	private String name;
	private int instock;
	private int sales;

	/**
	 * Constructs a new publication with a name, a stock quantity, and a sales
	 * quantity.
	 *
	 * @param name  the name of the publication
	 * @param stock the quantity of the publication in stock
	 * @param sales the quantity of the publication sold
	 * @throws IllegalArgumentException if name is null or empty, or if stock or
	 *                                  sales is not positive
	 */
	public Publication(String name, int stock, int sales) {
		ArgumentChecks.isTrue(name != null);
		ArgumentChecks.isTrue(name.trim().isEmpty() == false);
		ArgumentChecks.isTrue(stock > 0);
		ArgumentChecks.isTrue(sales > 0);
		this.name = name;
		this.instock = stock;
		this.sales = sales;
	}

	/**
	 * Generates an order for the publication.
	 *
	 * @return an order for the publication
	 */
	public abstract Order generateOrders();

	/**
	 * Serializes the publication to a string.
	 *
	 * @return a string that represents the publication
	 */
	public abstract String serialize();

	/**
	 * Returns the name of the publication.
	 *
	 * @return the name of the publication
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the quantity of the publication in stock.
	 *
	 * @return the quantity of the publication in stock
	 */
	public int getStock() {
		return instock;
	}

	/**
	 * Returns the quantity of the publication sold.
	 *
	 * @return the quantity of the publication sold
	 */
	public int getSales() {
		return sales;
	}

	/**
	 * Returns a string representation of the publication.
	 *
	 * @return a string representation of the publication
	 */
	@Override
	public String toString() {
		return getName() + "\t" + getStock() + "\t" + getSales();
	}

	/**
	 * Returns a hash code value for the object, based on the name field.
	 * 
	 * @return the hash code value for the object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31; // The constant 31 is used as a multiplier in the hash function
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode()); // Computes hash code value
																			// for the name field
		return result;
	}

	/**
	 * Indicates whether some other object is "equal to" this one based on the name
	 * field.
	 * 
	 * @param obj the reference object with which to compare.
	 * @return true if this object is the same as the obj argument; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publication other = (Publication) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Publication other) {
		return this.getName().compareTo(other.getName());
		// return getSales()-other.getSales();
	}

}
