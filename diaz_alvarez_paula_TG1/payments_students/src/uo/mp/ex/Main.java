package uo.mp.ex;

import uo.mp.ex.retailer.Retailer;
import uo.mp.ex.retailer.payment.CashPayment;
import uo.mp.ex.retailer.payment.CreditCardPayment;
import uo.mp.ex.retailer.payment.Payment;
import uo.mp.ex.retailer.payment.PaypalPayment;

public class Main {
	private static final String RETAILER_ID = "1111-22";

	public static void main(String[] args) {
		Retailer r = new Retailer();

		Payment p1 = new CreditCardPayment("100", 100.0, "1111-1111-1111-1111-1111", 12, 2018, RETAILER_ID);
		Payment p2 = new PaypalPayment("101", 100.0, "john@gmail.com", "@34abX!");
		Payment p3 = new CashPayment("102", 100.0);

		r.addPayment(p1);
		r.addPayment(p2);
		r.addPayment(p3);

		double total = r.getTotalSales();
		System.out.println("Total sales " + total);

		r.processAllPayments();

		total = r.getTotalSales();

		System.out.println("After payment processing total sales " + total);
	}

}
