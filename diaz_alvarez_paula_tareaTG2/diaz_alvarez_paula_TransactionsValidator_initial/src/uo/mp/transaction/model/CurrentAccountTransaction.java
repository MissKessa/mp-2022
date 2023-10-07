package uo.mp.transaction.model;

import java.util.Objects;

import uo.mp.transaction.model.util.IBAN;

public class CurrentAccountTransaction extends Transaction {
	public final static String TYPE_OF_TRANSACTION = "ca";
	public final static int POSITION_ACCOUNT_NUMBER = 2;
	public final static int POSITION_TYPE_OF_CLIENT = 3;
	public final static int POSITION_AMOUNT = 4;
	public final static int POSITION_DESCRIPTION = 5;
	public final static int LENGTH_LINE = 6;

	private TypeOfClient client;

	public CurrentAccountTransaction(String date, String number, double amount, String description,
			TypeOfClient client) {
		super(date, date, amount, date);
		this.client = client;
	}

	@Override
	public void validate() {
		if (client.equals(TypeOfClient.N)) {
			if (getAmount() > 1000) {
				addFault("The quantity must be lower or equal to 1000");
			}
		}
		if (!IBAN.isValid(getNumber())) {
			addFault("The IBAN is invalid");
		}

	}

	@Override
	public String serialize() {
		return String.format("%s;%s;%s;%s;%s;%s", TYPE_OF_TRANSACTION, getDate(), getNumber(), this.client, getAmount(),
				getDescription());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(client);
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
		CurrentAccountTransaction other = (CurrentAccountTransaction) obj;
		return super.equals(obj) && client == other.client;
	}

}
