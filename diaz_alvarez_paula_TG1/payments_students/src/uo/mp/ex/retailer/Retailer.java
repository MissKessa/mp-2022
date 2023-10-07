package uo.mp.ex.retailer;

import java.util.ArrayList;
import java.util.List;

import uo.mp.ex.retailer.payment.Payment;

public class Retailer {
	private List<Payment> payments;

	public Retailer() {
		this.payments = new ArrayList<Payment>();
	}

	public void addPayment(Payment payment) {
		if (payment == null) {
			throw new IllegalArgumentException("The payment must be not null");
		}
		payments.add(payment);
	}

	public void processAllPayments() {
		for (Payment payment : this.payments) {
			payment.process();
		}
	}

	public double getTotalSales() {
		double sales = 0;
		for (Payment payment : this.payments) {
			sales += payment.getAmount();
		}
		return sales;
	}

	public Payment getPayment(String id, double amount) {
		if (id == null || amount < 0)
			throw new IllegalArgumentException();
		for (Payment payment : this.payments) {
			if (payment.getId().equals(id) && payment.getAmount() == amount)
				return payment;
		}
		return null;
	}
}
