package uo.mp.ex.retailer.payment;

import ruralvia.PaymentGateway;
import ruralvia.Transaction;

public class CreditCardPayment extends Payment implements Transaction {
	private String creditCardNumber;
	private int month;
	private int year;
	private String merchantId;
	private PaymentGateway paymentGateway;

	public CreditCardPayment(String id, double amount, String creditCardNumber, int month, int year,
			String retailerId) {
		super(id, amount);

		if (creditCardNumber == null)
			throw new IllegalArgumentException("The credit card number cannot be null");
		if (month <= 0 || month > 12)
			throw new IllegalArgumentException("The month must be in the range [1,12]");
		if (year <= 0)
			throw new IllegalArgumentException("The year must be greater than 0");
		if (retailerId == null)
			throw new IllegalArgumentException("The merchant id cannot be null");

		this.creditCardNumber = creditCardNumber;
		this.month = month;
		this.year = year;
		this.merchantId = retailerId;
		paymentGateway = new PaymentGateway();
	}

	@Override
	public void process() {
		if (!isValid()) {
			if (this.paymentGateway.isValid(this))
				setValid(true);
		}

	}

	@Override
	public String getMerchantId() {
		return this.merchantId;
	}

	@Override
	public String getCreditCardNumber() {
		return this.creditCardNumber;
	}

	@Override
	public int getMonth() {
		return this.month;
	}

	@Override
	public int getYear() {
		return this.year;
	}

	@Override
	public String getTransactionId() {
		return getId();
	}

}
