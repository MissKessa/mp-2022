package uo.mp.ex.retailer.payment;

public abstract class Payment {
	private String id;
	private double amount;
	private boolean valid;

	public Payment(String id, double amount) {
		if (id == null)
			throw new IllegalArgumentException("The id cannot be null");
		if (amount < 0)
			throw new IllegalArgumentException("The amount cannot be negative");
		this.id = id;
		this.amount = amount;
		this.valid = false;
	}

	public abstract void process();

	public boolean isValid() {
		return this.valid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (id == null)
			throw new IllegalArgumentException("The id cannot be null");
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		if (amount < 0)
			throw new IllegalArgumentException("The amount cannot be negative");
		this.amount = amount;
	}

	protected void setValid(boolean valid) {
		this.valid = valid;
	}

}
