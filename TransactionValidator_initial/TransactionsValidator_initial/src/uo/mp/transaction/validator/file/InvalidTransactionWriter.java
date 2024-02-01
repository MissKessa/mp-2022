package uo.mp.transaction.validator.file;

import java.io.FileNotFoundException;
import java.util.List;

import uo.mp.transaction.model.Transaction;
import uo.mp.transaction.validator.exception.TransactionException;
import uo.mp.transaction.validator.serialize.TransactionSerializer;
import uo.mp.util.fileutil.FileUtil;

public class InvalidTransactionWriter {

	private String fileName;

	public InvalidTransactionWriter(String fileName) {
		this.fileName = fileName;
	}

	public void save(List<Transaction> invalidTrx) throws TransactionException {
		List<String> lines = new TransactionSerializer().serialize(invalidTrx);
		try {
			new FileUtil().writeLines(fileName + ".invalid.trx", lines);
		} catch (FileNotFoundException e) {
			throw new TransactionException("El fichero de entrada no existe");
		}
	}

}
