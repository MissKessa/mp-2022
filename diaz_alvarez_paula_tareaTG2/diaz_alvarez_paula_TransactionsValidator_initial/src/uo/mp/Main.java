package uo.mp;

import java.util.List;

import uo.mp.transaction.TransactionProcessor;
import uo.mp.transaction.model.Transaction;
import uo.mp.transaction.validator.exception.TransactionException;
import uo.mp.util.console.Console;
import uo.mp.util.log.Logger;

public class Main {

	private static final String TRX_FILE_NAME = "input_transactions.trx";
//	private static final String TRX_FILE_NAME = "input_transactions_with_parsing_errors.trx";
//	private static final String TRX_FILE_NAME = "input_transactions_with_repeated_abort.trx";

	public static void main(String[] args) {
		new Main().run();
	}

	private void run() {
		try {
			process();
		} catch (RuntimeException e) {
			handleSystemError(e);
		} catch (TransactionException e) {
			handleUserError(e);
		}
	}

	private void handleSystemError(RuntimeException e) {
		Console.print("PROGRAMMING ERROR la aplicación finaliza debido a un error interno");
		Logger.log(e);

	}

	private void handleUserError(TransactionException e) {
		Console.print("APPLICATION ERROR revise este error: " + e.getMessage());

	}

	private void process() throws TransactionException {
		TransactionProcessor tp = new TransactionProcessor();
		tp.process(TRX_FILE_NAME);
		showInvalidTransactions(tp.getInvalidTransactions());
	}

	private void showInvalidTransactions(List<Transaction> invalidTrxs) {
		for (Transaction t : invalidTrxs) {
			System.out.println(t);
			for (String error : t.getFaults()) {
				System.out.println(error);
			}
		}
	}

}
