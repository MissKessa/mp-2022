package uo.mp.transaction.model;

import java.util.Objects;

import uo.mp.util.check.ArgumentChecks;

public class CreditCardTransaction extends Transaction {
	public final static String TYPE_OF_TRANSACTION = "cc";
	public final static int POSITION_CREDIT_CARD_NUMBER = 2;
	public final static int POSITION_EXPIRATION_DATE = 3;
	public final static int POSITION_MAXIMUM_AMOUNT = 4;
	public final static int POSITION_AMOUNT = 5;
	public final static int POSITION_DESCRIPTION = 6;
	public final static int LENGTH_LINE = 7;

	private String expireDate;
	private double maximumQuantity;

	public CreditCardTransaction(String date, String number, double amount, String description, String expireDate,
			double maximumQuantity) {
		super(date, date, amount, date);
		ArgumentChecks.notBlank(expireDate, "The expireDate cannot be blank");
		ArgumentChecks.notNull(expireDate, "The expireDate cannot be null");
		ArgumentChecks.isTrue(maximumQuantity > 0, "The maximumQuantity must be greater than 0");
		this.maximumQuantity = maximumQuantity;
		this.expireDate = expireDate;

	}

	@Override
	public void validate() {
		if (getDate().compareTo(this.expireDate) > 1) {
			addFault("The date cannot be greater than the expiration date");
		}
		if (getAmount() > this.maximumQuantity) {
			addFault("The date cannot be greater than the expiration date");
		}

	}

	@Override
	public String serialize() {
		return String.format("%s;%s;%s;%s;%s;%s;%s", TYPE_OF_TRANSACTION, getDate(), getNumber(), this.expireDate,
				this.maximumQuantity, getAmount(), getDescription());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(expireDate, maximumQuantity);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCardTransaction other = (CreditCardTransaction) obj;
		return super.equals(obj) && Objects.equals(expireDate, other.expireDate)
				&& Double.doubleToLongBits(maximumQuantity) == Double.doubleToLongBits(other.maximumQuantity);
	}

}
