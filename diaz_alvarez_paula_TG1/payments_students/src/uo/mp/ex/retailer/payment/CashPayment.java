package uo.mp.ex.retailer.payment;

public class CashPayment extends Payment {

	public CashPayment(String id, double amount) {
		super(id, amount);
	}

	@Override
	public void process() {
		if (!isValid())
			setValid(true);
	}

}
