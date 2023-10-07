package uo.mp.transaction.validator.serialize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uo.mp.transaction.model.Transaction;
import uo.mp.transaction.validator.sort.TransactionComparator;
import uo.mp.util.check.ArgumentChecks;

public class TransactionSerializer {

	public List<String> serialize(List<Transaction> validTrx) {
		ArgumentChecks.notNull(validTrx, "The list of valid transactions cannot be null");
		Collections.sort(validTrx, new TransactionComparator());

		List<String> serialized = new ArrayList<>(validTrx.size());
		for (Transaction trx : validTrx) {
			ArgumentChecks.notNull(trx, "The list of valid transactions cannot have null elements");
			serialized.add(trx.serialize());
		}
		return serialized;
	}

}
