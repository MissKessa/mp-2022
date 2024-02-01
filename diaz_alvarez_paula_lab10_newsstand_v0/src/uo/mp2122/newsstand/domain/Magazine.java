package uo.mp2122.newsstand.domain;

import uo.mp.util.check.ArgumentChecks;

/**
 * 
 * The Magazine class represents a publication that is issued periodically, and
 * contains a frequency attribute indicating how often it is issued. It extends
 * the Publication class.
 * 
 * @author Programming Methodology Teaching Staff
 * @version 2023
 * @see Publication
 */
public class Magazine extends Publication {
	public static int WEEKLY = 7;
	public static int MONTHLY = 30;
	public static int MIN_STOCK = 5;
	public static int MIN_ORDER = 20;

	private int frequency;

	/**
	 * Creates a Magazine object with the specified name, stock, sales and
	 * frequency. The frequency attribute indicates how often the magazine is
	 * issued.
	 *
	 * @param name  a string representing the name of the magazine
	 * @param stock an integer representing the current stock of the magazine
	 * @param sales an integer representing the number of sales of the magazine
	 * @param freq  an integer representing the frequency at which the magazine is
	 *              issued
	 * @throws IllegalArgumentException if freq is less than or equal to 0
	 */
	public Magazine(String name, int stock, int sales, int freq) {
		super(name, stock, sales);
		ArgumentChecks.isTrue(freq > 0);
		this.frequency = freq;
	}

	/**
	 * Returns the frequency at which the magazine is issued.
	 *
	 * @return an integer representing the frequency of the magazine
	 */
	public int getFrequency() {
		return frequency;
	}

	/**
	 * Returns a string representation of the Magazine object, including its name,
	 * stock, sales, and frequency.
	 *
	 * @return a string representing the Magazine object
	 */
	@Override
	public String toString() {
		return super.toString() + "\t" + getFrequency();
	}

	/**
	 * Returns a serialized string representation of the Magazine object, including
	 * its name, stock, sales, and frequency.
	 *
	 * @return a serialized string representing the Magazine object
	 */
	@Override
	public String serialize() {
		return "magazine" + "\t" + getName() + "\t" + getStock() + "\t" + getSales() + "\t" + getFrequency();
	}

	/**
	 * Generates an Order object for the Magazine based on its current stock and
	 * sales. If the stock is less than MIN_STOCK, a new Order object will be
	 * generated with a minimum order quantity of MIN_ORDER. If the frequency of the
	 * Magazine is WEEKLY, a new Order object will be generated with a quantity
	 * equal to its sales. Otherwise, a new Order object will be generated with a
	 * quantity equal to the sum of its sales and stock.
	 *
	 * @return an Order object representing the order to be placed for the Magazine
	 */
	@Override
	public Order generateOrders() {
		if (getStock() < MIN_STOCK) {
			return new Order(this.getName(), MIN_ORDER);
		} else {
			if (getFrequency() == WEEKLY && getStock() == MIN_STOCK)
				return new Order(this.getName(), getSales());
			else if (getFrequency() == MONTHLY && getStock() == MIN_STOCK) {
				return new Order(this.getName(), getSales() + getStock());
			} else {
				return null;
			}
		}
	}

}
