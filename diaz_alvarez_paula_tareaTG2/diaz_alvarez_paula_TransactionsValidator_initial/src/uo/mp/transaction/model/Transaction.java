package uo.mp.transaction.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import uo.mp.util.check.ArgumentChecks;

public abstract class Transaction {
	public final static int POSITION_TYPE_OF_TRANSACTION = 0;
	public final static int POSITION_DATE = 1;

	public final static int MIN_DAY = 1;
	public final static int MAX_DAY = 31;
	public final static int POSITION_DAY = 2;

	public final static int MIN_MONTH = 1;
	public final static int MAX_MONTH = 12;
	public final static int POSITION_MONTH = 1;

	public final static int MAX_YEAR = 2021;
	public final static int POSITION_YEAR = 0;

	public final static int LENGTH_DATE = 3;

	private String date;
	private String number;
	private double amount;
	private String description;

	private List<String> validationFaults = new ArrayList<>();

	public Transaction(String date, String number, double amount, String description) {
		ArgumentChecks.notBlank(date, "The date cannot be blank");
		ArgumentChecks.notNull(date, "The date cannot be null");
		checkDate(date);
		ArgumentChecks.notBlank(number, "The number cannot be blank");
		ArgumentChecks.notNull(number, "The number cannot be null");
		ArgumentChecks.notBlank(description, "The date cannot be blank");
		ArgumentChecks.notNull(description, "The date cannot be null");
		ArgumentChecks.isTrue(amount > 0, "The amount must be greater than 0");
		this.date = date;
		this.number = number;
		this.amount = amount;
		this.description = description;
	}

	public abstract void validate();

	public abstract String serialize();

	public void addFault(String error) {
		validationFaults.add(error);
	}

	public String getNumber() {
		return number;
	}

	public double getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getFaults() {
		return new ArrayList<>(validationFaults);
	}

	public String getDate() {
		return date;
	}

	public boolean hasFaults() {
		return validationFaults.size() > 0;
	}

	public static void checkDate(String date) {
		String[] parts = date.split("/");
		if (parts.length != LENGTH_DATE)
			throw new IllegalArgumentException("The date must have 3 sections");

		int day;
		int month;
		int year;
		try {
			day = Integer.parseInt(parts[POSITION_DAY]);
			month = Integer.parseInt(parts[POSITION_DAY]);
			year = Integer.parseInt(parts[POSITION_DAY]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("The day, the month and the year must be numbers");
		}

		if (day < MIN_DAY || day > MAX_DAY)
			throw new IllegalArgumentException(String.format("The day must be in [%s,%s]", MIN_DAY, MAX_DAY));
		if (month < MIN_MONTH || month > MAX_MONTH)
			throw new IllegalArgumentException(String.format("The month must be in [%s,%s]", MIN_MONTH, MAX_MONTH));
		if (year > MAX_YEAR)
			throw new IllegalArgumentException(String.format("The day must be in [%s,%s]", MIN_DAY, MAX_DAY));

	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, date, description, number, validationFaults);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(date, other.date) && Objects.equals(description, other.description)
				&& Objects.equals(number, other.number) && Objects.equals(validationFaults, other.validationFaults);
	}

}
