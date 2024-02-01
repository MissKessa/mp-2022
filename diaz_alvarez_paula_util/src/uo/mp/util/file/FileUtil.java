package uo.mp.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * A utility class to read/write text lines from/to a text file
 */
public class FileUtil extends BaseFileUtil {

	/**
	 * Reads the lines of a given text file
	 * 
	 * @param inFileName is the name of the file
	 * @return a list of strings corresponding to the lines of the file
	 * @throws FileNotFoundException if the given file doesn't exist
	 */
	public List<String> readLines(String inFileName) throws FileNotFoundException {
		return super.readLines(createReaderChain(inFileName));
	}

	/**
	 * Writes the given lines in a given text file
	 * 
	 * @param outFileName is the name of the file
	 * @param lines       is the list of lines to be written
	 */
	public void writeLines(String outFileName, List<String> lines) {
		super.writeLines(createWriterChain(outFileName), lines);
	}

	@Override
	protected BufferedReader createReaderChain(String inFileName) throws FileNotFoundException {
		super.checkFileName(inFileName);

		try {
			FileReader file = new FileReader(inFileName);
			final BufferedReader buffer = new BufferedReader(file);
			return buffer;
		} catch (FileNotFoundException exception) {
			throw exception;
		}
	}

	@Override
	protected BufferedWriter createWriterChain(String outFileName) {
		super.checkFileName(outFileName);

		try {
			Writer file = new FileWriter(outFileName);
			final BufferedWriter buffer = new BufferedWriter(file);
			return buffer;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
