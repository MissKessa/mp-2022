package uo.mp2122.newsstand.domain;

/**
 * The Newspaper class represents a publication that is issued daily, and
 * extends the Publication class.
 * 
 * @author Programming Methodology Teaching Staff
 * @version 2023
 * @see Publication
 */
public class Newspaper extends Publication {
	public static int MIN_STOCK = 5;
	public static int MIN_ORDER = 20;

	/**
	 * Creates a Newspaper object with the specified name, stock, and sales.
	 *
	 * @param name  a string representing the name of the newspaper
	 * @param stock an integer representing the current stock of the newspaper
	 * @param sales an integer representing the number of sales of the newspaper
	 */
	public Newspaper(String name, int stock, int sales) {
		super(name, stock, sales);
	}

	/**
	 * Generates an Order object for the Newspaper based on its current stock and
	 * sales. A new Order object will be generated with a quantity equal to its
	 * sales plus twice its current stock.
	 *
	 * @return an Order object representing the order to be placed for the Newspaper
	 */
	@Override
	public Order generateOrders() {
		if (getStock() < MIN_STOCK)
			return new Order(getName(), MIN_ORDER);
		return null;
	}

	/**
	 * Returns a serialized string representation of the Newspaper object, including
	 * its name, stock, and sales.
	 *
	 * @return a serialized string representing the Newspaper object
	 */
	@Override
	public String serialize() {
		return "newspaper" + "\t" + getName() + "\t" + getStock() + "\t" + getSales();
	}
}
