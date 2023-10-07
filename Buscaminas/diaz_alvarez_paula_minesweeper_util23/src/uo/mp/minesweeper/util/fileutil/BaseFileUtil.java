package uo.mp.minesweeper.util.fileutil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * This abstract class has the functionality to read lines and write lines. It
 * has 2 abstract methods to create a buffer according to the file read.
 * 
 * @author Paula Díaz Álvarez
 *
 */
public abstract class BaseFileUtil {

	/**
	 * It's the error message thrown if the list of lines have some null lines
	 */
	private static final String ERROR_MESSAGE_THE_LINES_CANNOT_HAVE_ONE_NULL_LINE = "The lines cannot have one null line";
	/**
	 * It's the error message thrown if the list of lines is null
	 */
	private static final String ERROR_MESSAGE_THE_LINES_CANNOT_BE_NULL = "The lines cannot be null";
	/**
	 * It's the error message thrown if the buffer is null
	 */
	public static final String ERROR_MESSAGE_THE_BUFFER_CANNOT_BE_NULL = "The buffer cannot be null";
	/**
	 * It's the error message thrown if the fileName is null
	 */
	public static final String ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_NULL = "The filename cannot be null";
	/**
	 * It's the error message thrown if the fileName is blank
	 */
	public static final String ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_BLANK = "The filename cannot be made of blanks";
	/**
	 * It's the error message thrown if the fileName is empty
	 */
	public static final String ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_EMPTY = "The filename cannot be empty";

	/**
	 * It creates a buffer reader chain according to the type of file read
	 * 
	 * @param inFileName is the name of the file read
	 * @return the bufferReader according to the type of file read
	 * @throws FileNotFoundException    if the file doesn't exist
	 * @throws IllegalArgumentException if the name of the file is null, blank or
	 *                                  empty
	 */
	protected abstract BufferedReader createReaderChain(String inFileName) throws FileNotFoundException;

	/**
	 * It creates a buffer writer chain according to the type of file written
	 * 
	 * @param outFileName is the name of the file written
	 * @return the BufferedWriter according to the type of file written
	 * @throws FileNotFoundException    if the file doesn't exist
	 * @throws IllegalArgumentException if the name of the file is null, blank or
	 *                                  empty
	 */
	protected abstract BufferedWriter createWriterChain(String outFileName) throws FileNotFoundException;

	/**
	 * Reads the lines of a file given its buffered reader
	 * 
	 * @param givenBuffer is the BufferedReader of the file
	 * @return a list of strings corresponding to the lines of the file
	 * @throws IllegalArgumentException if buffer is null
	 */
	protected List<String> readLines(BufferedReader givenBuffer) {
		ArgumentChecks.notNull(givenBuffer, ERROR_MESSAGE_THE_BUFFER_CANNOT_BE_NULL);

		List<String> lines = new ArrayList<>();

		String line;
		try (BufferedReader buffer = givenBuffer) {
			while ((line = buffer.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		} finally {
			try {
				givenBuffer.close();
			} catch (IOException exception) {
				throw new RuntimeException(exception);
			}
		}
		return lines;

	}

	/**
	 * Checks if the fileName is null, blank or empty
	 * 
	 * @param fileName is the fileName to be checked
	 * @throws IllegalArgumentException if the name of the file is null, blank or
	 *                                  empty
	 */
	protected void checkFileName(String fileName) {
		ArgumentChecks.notNull(fileName, ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_NULL);
		ArgumentChecks.notBlank(fileName, ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_BLANK);
		ArgumentChecks.notEmpty(fileName, ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_EMPTY);
	}

	/**
	 * Writes the lines in a file given its buffered writer and the lines to be
	 * written
	 * 
	 * @param givenBuffer is the BufferedWriter of the file
	 * @param lines       is the list of lines to be written
	 * @throws IllegalArgumentException if buffer is null, the list of lines is
	 *                                  null, or has null lines
	 */
	protected void writeLines(BufferedWriter givenBuffer, List<String> lines) {
		ArgumentChecks.notNull(givenBuffer, ERROR_MESSAGE_THE_BUFFER_CANNOT_BE_NULL);
		ArgumentChecks.notNull(lines, ERROR_MESSAGE_THE_LINES_CANNOT_BE_NULL);
		ArgumentChecks.noNullElements(lines.toArray(), ERROR_MESSAGE_THE_LINES_CANNOT_HAVE_ONE_NULL_LINE);

		try (final BufferedWriter buffer = givenBuffer) {
			for (String line : lines) {
				buffer.write(line);
				buffer.newLine();
			}
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		} finally {
			try {
				givenBuffer.close();
			} catch (IOException exception) {
				throw new RuntimeException(exception);
			}
		}

	}
}
