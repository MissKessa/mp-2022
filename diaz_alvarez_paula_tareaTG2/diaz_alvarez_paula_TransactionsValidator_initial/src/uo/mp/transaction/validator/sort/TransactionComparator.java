package uo.mp.transaction.validator.sort;

import java.util.Comparator;

import uo.mp.transaction.model.Transaction;

public class TransactionComparator implements Comparator<Transaction> {

	@Override
	public int compare(Transaction t1, Transaction t2) {
		final int compareToDates = t1.getDate().compareTo(t2.getDate());
		final int compareToNumber = t1.getNumber().compareTo(t2.getNumber());

		if (compareToDates == 0) {
			return compareToNumber;
		}
		return compareToDates;
	}

}
