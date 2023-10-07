package uo.mp.transaction.validator.file;

import java.io.FileNotFoundException;
import java.util.List;

import uo.mp.transaction.model.Transaction;
import uo.mp.transaction.validator.exception.TransactionException;
import uo.mp.transaction.validator.serialize.TransactionSerializer;
import uo.mp.util.fileutil.ZipFileUtil;

public class ValidTransactionWriter {

	private String fileName;

	public ValidTransactionWriter(String fileName) {
		this.fileName = fileName;
	}

	public void save(List<Transaction> validTrx) throws TransactionException {
		List<String> lines = new TransactionSerializer().serialize(validTrx);
		try {
			new ZipFileUtil().writeLines(fileName + ".trx.gz", lines);
		} catch (FileNotFoundException e) {
			throw new TransactionException("El fichero de entrada no existe");
		}
	}

}
