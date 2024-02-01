package uo.mp.ex.retailer.payment;

import paypal.PayPal;

public class PaypalPayment extends Payment {
	private String username;
	private String password;

	public PaypalPayment(String id, double amount, String username, String password) {
		super(id, amount);
		if (username == null)
			throw new IllegalArgumentException("The username cannot be null");
		if (password == null)
			throw new IllegalArgumentException("The password cannot be null");
		this.username = username;
		this.password = password;
	}

	@Override
	public void process() {
		PayPal paypal = new PayPal();
		String token = paypal.logIn(getUsername(), getPassword());
		if (paypal.checkout(token, getId(), getAmount()))
			setValid(true);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
