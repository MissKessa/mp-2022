package uo.mp.transaction.validator;

import java.util.ArrayList;
import java.util.List;

import uo.mp.transaction.model.Transaction;
import uo.mp.transaction.validator.exception.TransactionException;
import uo.mp.util.check.ArgumentChecks;

public class TransactionValidator {
	List<Transaction> transactions = new ArrayList<>();
	List<Transaction> invalidTransactions = new ArrayList<>();
	List<Transaction> validTransactions = new ArrayList<>();

	public void add(List<Transaction> trxs) throws TransactionException {
		ArgumentChecks.notNull(trxs, "The list cannot be null");
		for (Transaction trx : trxs) {
			ArgumentChecks.notNull(trx, "The list cannot have null elements");
			if (this.transactions.contains(trx)) {
				throw new TransactionException("operación repetida");
			}
			this.transactions.add(trx);
		}
	}

	public void validate() {
		for (Transaction transaction : this.transactions) {
			transaction.validate();
			if (!transaction.hasFaults()) {
				validTransactions.add(transaction);
			} else
				invalidTransactions.add(transaction);
		}
	}

	public List<Transaction> getInvalidTransactions() {
		List<Transaction> copy = new ArrayList<>();
		for (Transaction transaction : this.invalidTransactions) {
			copy.add(transaction);
		}
		return copy;
	}

	public List<Transaction> getValidTransactions() {
		List<Transaction> copy = new ArrayList<>();
		for (Transaction transaction : this.validTransactions) {
			copy.add(transaction);
		}
		return copy;
	}

}
