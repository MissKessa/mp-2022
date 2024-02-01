package uo.mp.transaction.validator.parser;

import java.util.ArrayList;
import java.util.List;

import uo.mp.transaction.model.CreditCardTransaction;
import uo.mp.transaction.model.CurrentAccountTransaction;
import uo.mp.transaction.model.Transaction;
import uo.mp.transaction.model.TypeOfClient;
import uo.mp.transaction.validator.exception.InvalidLineException;
import uo.mp.util.check.ArgumentChecks;

public class TransactionParser {
	private int lineNumber = 1;

	public List<Transaction> parse(List<String> lines) {
		ArgumentChecks.notNull(lines, "The list of lines cannot be null");
		List<Transaction> trxs = new ArrayList<>(lines.size());

		for (String line : lines) {
			ArgumentChecks.notNull(line, "The list of lines cannot have null lines");
			try {
				Transaction trx = divideLine(line);
				trxs.add(trx);
			} catch (InvalidLineException e) {
				System.out.println(e.getMessage());
			}
			lineNumber++;
		}
		return trxs;
	}

	private Transaction divideLine(String line) throws InvalidLineException {
		isEmptyOrBlankTheLine(line);

		String[] parts = line.split(line);
		String typeOfTransaction = parts[Transaction.POSITION_TYPE_OF_TRANSACTION];

		if (typeOfTransaction.equals(CreditCardTransaction.TYPE_OF_TRANSACTION))
			return parseCreditCardTransaction(parts);
		else if (typeOfTransaction.equals(CurrentAccountTransaction.TYPE_OF_TRANSACTION))
			return parseCurrentAccountTransaction(parts);
		else
			throw new InvalidLineException(this.lineNumber, "PALABRA CLAVE NO VÁLIDA");
	}

	private Transaction parseCurrentAccountTransaction(String[] parts) throws InvalidLineException {
		checkLength(parts, CurrentAccountTransaction.LENGTH_LINE);

		String date = parts[CurrentAccountTransaction.POSITION_DATE];
		checkDateIsCorrect(date);

		String accountNumber = parts[CurrentAccountTransaction.POSITION_ACCOUNT_NUMBER];

		String typeOfClientString = parts[CurrentAccountTransaction.POSITION_TYPE_OF_CLIENT];
		TypeOfClient typeOfClient = parseTypeOfClient(typeOfClientString);

		String amountString = parts[CurrentAccountTransaction.POSITION_AMOUNT];
		int amount = parseInteger(amountString);

		String description = parts[CurrentAccountTransaction.POSITION_DESCRIPTION];

		return new CurrentAccountTransaction(date, accountNumber, amount, description, typeOfClient);
	}

	private Transaction parseCreditCardTransaction(String[] parts) throws InvalidLineException {
		checkLength(parts, CreditCardTransaction.LENGTH_LINE);

		String date = parts[CreditCardTransaction.POSITION_DATE];
		checkDateIsCorrect(date);

		String creditCardNumber = parts[CreditCardTransaction.POSITION_CREDIT_CARD_NUMBER];

		String amountString = parts[CreditCardTransaction.POSITION_AMOUNT];
		int amount = parseInteger(amountString);

		String description = parts[CreditCardTransaction.POSITION_DESCRIPTION];

		String maxQuantityString = parts[CreditCardTransaction.POSITION_MAXIMUM_AMOUNT];
		int maxQuantity = parseInteger(maxQuantityString);

		String expireDate = parts[CreditCardTransaction.POSITION_EXPIRATION_DATE];
		checkDateIsCorrect(expireDate);

		return new CreditCardTransaction(date, creditCardNumber, amount, description, expireDate, maxQuantity);
	}

	private void checkLength(String[] parts, int length) throws InvalidLineException {
		if (parts.length != length)
			throw new InvalidLineException(this.lineNumber, "NÚMERO DE CAMPOS INCORRECTO");
	}

	private void isEmptyOrBlankTheLine(String line) throws InvalidLineException {
		if (line.isBlank() || line.isEmpty()) {
			throw new InvalidLineException(this.lineNumber, "LÍNEA VACÍA");
		}
	}

	public int parseInteger(String number) throws InvalidLineException {
		try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			throw new InvalidLineException(this.lineNumber, "FORMATO DE NÚMERO NO VÁLIDO");
		}
	}

	private TypeOfClient parseTypeOfClient(String client) throws InvalidLineException {
		if (client.equals("N"))
			return TypeOfClient.N;
		else if (client.equals("P"))
			return TypeOfClient.P;
		else
			throw new InvalidLineException(this.lineNumber, "EL TIPO DE CLIENTE NO ES VÁLIDO");
	}

	private void checkDateIsCorrect(String date) throws InvalidLineException {
		try {
			Transaction.checkDate(date);
		} catch (IllegalArgumentException e) {
			throw new InvalidLineException(this.lineNumber, "FORMATO DE FECHA NO VÁLIDO");
		}
	}

}
