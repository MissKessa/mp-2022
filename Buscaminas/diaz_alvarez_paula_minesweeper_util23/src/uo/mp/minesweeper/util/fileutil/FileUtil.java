package uo.mp.minesweeper.util.fileutil;

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

	/**
	 * Writes the given lines in a given text file in append mode
	 * 
	 * @param outFileName is the name of the file
	 * @param lines       is the list of lines to be written
	 */
	public void appendLines(String outFileName, List<String> lines) {
		super.writeLines(createWriterChainInAppendMode(outFileName), lines);
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

	/**
	 * It creates a buffer writer chain in append mode according to the type of file
	 * written
	 * 
	 * @param outFileName is the name of the file written
	 * @return the BufferedWriter according to the type of file written
	 * @throws FileNotFoundException    if the file doesn't exist
	 * @throws IllegalArgumentException if the name of the file is null, blank or
	 *                                  empty
	 */
	protected BufferedWriter createWriterChainInAppendMode(String outFileName) {
		super.checkFileName(outFileName);

		try {
			Writer file = new FileWriter(outFileName, true);
			final BufferedWriter buffer = new BufferedWriter(file);
			return buffer;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
