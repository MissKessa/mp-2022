package uo.mp2122.newsstand.domain;

import java.util.Objects;

import uo.mp.util.check.ArgumentChecks;

/**
 * The Order class represents an order for a publication, which includes the
 * name of the publication and the quantity to be ordered.
 * 
 * @author Programming Methodology Teaching Staff
 * @version 2023
 */
public class Order {
	private String name;
	private int quantity;

	/**
	 * Creates an Order object with the specified publication name and quantity to
	 * order. Throws an IllegalArgumentException if the name is null or empty, or if
	 * the quantity is negative.
	 *
	 * @param name     a string representing the name of the publication to order
	 * @param quantity an integer representing the quantity to order
	 * @throws IllegalArgumentException if the name is null or empty, or if the
	 *                                  quantity is negative
	 */
	public Order(String name, int quantity) {
		ArgumentChecks.isTrue(name != null);
		ArgumentChecks.isTrue(name.trim().isEmpty() == false);
		ArgumentChecks.isTrue(quantity > 0);

		this.name = name;
		this.quantity = quantity;
	}

	/**
	 * Returns the name of the publication for the order.
	 *
	 * @return a string representing the name of the publication for the order
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the quantity to order.
	 *
	 * @return an integer representing the quantity to order
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Returns a string representation of the Order object.
	 *
	 * @return a string representing the Order object
	 */
	@Override
	public String toString() {
		return "Order: " + getName() + "\t" + getQuantity();
	}

	/**
	 * Returns a serialized string representation of the Order object, including its
	 * name and quantity to order.
	 *
	 * @return a serialized string representing the Order object
	 */
	public String serialize() {
		return getName() + "\t" + getQuantity();
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(name, other.name) && quantity == other.quantity;
	}

}
