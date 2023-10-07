package uo.mp.transaction.validator.file;

import java.io.FileNotFoundException;
import java.util.List;

import uo.mp.transaction.model.Transaction;
import uo.mp.transaction.validator.exception.TransactionException;
import uo.mp.transaction.validator.parser.TransactionParser;
import uo.mp.util.fileutil.FileUtil;

public class TransactionLoader {

	private String fileName;

	public TransactionLoader(String fileName) {
		this.fileName = fileName;
	}

	public List<Transaction> load() throws TransactionException {
		List<String> lines;
		try {
			lines = new FileUtil().readLines(fileName);
			return new TransactionParser().parse(lines);
		} catch (FileNotFoundException e) {
			throw new TransactionException("El fichero de entrada no existe");
		}
	}

}
