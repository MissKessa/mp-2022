package uo.mp.exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import uo.mp.exception.exception.FileFormatException;
import uo.mp.exception.exception.LineFormatException;

public class Accounter {

	private List<String> lines;

	public Accounter(String fileName)
			throws FileNotFoundException, FileFormatException {
		lines = readLinesOnFile(fileName);
		assertNotEmptyFile(fileName, lines);
	}

	public double processFile() throws FileFormatException {
		double res = 0.0;
		int ln = 1;
		for (String l : lines) {
			res += processLine(l, ln);
			ln++;
		}
		return res;
	}

	/**
	 * We want to handle FileNotFoundtException as a user's fault, but the rest
	 * of generic IO issues (read/write errors, etc.) to be treated as system
	 * errors (not user's fault) that prevent the execution to continue. For
	 * that we leave the FileNotFoundException to pass, but convert the rest of
	 * IOExceptions into RuntimeExceptions
	 * 
	 * @param fName
	 * @return
	 * @throws IOException
	 */
	private List<String> readLinesOnFile(String fName)
			throws FileNotFoundException {
		FileReader f = new FileReader(fName); // May throw FileNotFoundException
		try {
			try {
				return f.readLines(); // <-- May throw IOException
			} finally {
				f.close(); // <-- May throw IOException
			}
		} catch (IOException ioe) {
			throw new RuntimeException(ioe); // <-- conversion of the type
		}
	}

	private double processLine(String line, int ln) throws LineFormatException {
		assertNotBlankLine(line, ln);

		String[] parts = line.split(";");
		assertThreeFields(line, ln, parts);

		double amount = 0.0;
		try {
			amount = Double.parseDouble(parts[2]);
		} catch (NumberFormatException nfe) {
			throw new LineFormatException("The field is not a number", line, ln,
					nfe);
		}

		return amount;
	}

	private void assertThreeFields(String line, int ln, String[] parts)
			throws LineFormatException {

		if (parts.length == 3)
			return;

		throw new LineFormatException(
				"The line " + ln + " has " + parts.length + " fields", line,
				ln);
	}

	private void assertNotBlankLine(String line, int ln)
			throws LineFormatException {

		if (isBlankLine(line)) {
			throw new LineFormatException("The line " + ln + " is blank", line,
					ln);
		}
	}

	private boolean isBlankLine(String line) {
		return line == null || "".equals(line) || " ".equals(line);
	}

	private void assertNotEmptyFile(String fName, List<String> lines)
			throws FileFormatException {

		if (!lines.isEmpty())
			return;

		throw new FileFormatException("The file " + fName + " is empty");
	}

}
