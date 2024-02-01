package uo.mp2122.newsstand.service.serializers;

import java.util.LinkedList;
import java.util.List;

import uo.mp2122.newsstand.domain.Order;

public class OrderSerializer {

	/**
	 * Returns a list of String out of a list of Orders
	 * 
	 * @param orders, the list of orders to convert
	 * @return a list of String-serialized orders
	 */
	public List<String> serialize(List<Order> orders) {
		List<String> res = new LinkedList<>();
		for (Order o : orders) {
			res.add(o.serialize());
		}
		return res;
	}

}
