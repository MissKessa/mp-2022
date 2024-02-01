package uo.mp.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * A utility class to read/write text lines from/to a compressed text file
 * (.txt.gz)
 */
public class ZipFileUtil extends BaseFileUtil {

	/**
	 * Reads the lines of a given compressed text file
	 * 
	 * @param inFileName is the name of the file
	 * @return a list of strings corresponding to the lines of the file
	 * @throws FileNotFoundException if the given file doesn't exist
	 */
	public List<String> readLines(String inFileName) throws FileNotFoundException {
		return super.readLines(createReaderChain(inFileName));
	}

	/**
	 * Writes the given lines in a given compressed text file
	 * 
	 * @param outFileName is the name of the file
	 * @param lines       is the list of lines to be written
	 */
	public void writeLines(String outZippedFileName, List<String> lines) throws FileNotFoundException {
		super.writeLines(createWriterChain(outZippedFileName), lines);
	}

	@Override
	protected BufferedReader createReaderChain(String inFileName) throws FileNotFoundException {
		super.checkFileName(inFileName);

		try {
			FileInputStream file = new FileInputStream(inFileName);
			final GZIPInputStream gzip = new GZIPInputStream(file);
			final InputStreamReader reader = new InputStreamReader(gzip);
			final BufferedReader buffer = new BufferedReader(reader);
			return buffer;
		} catch (FileNotFoundException exception) {
			throw exception;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	protected BufferedWriter createWriterChain(String outFileName) throws FileNotFoundException {
		super.checkFileName(outFileName);

		try {
			FileOutputStream file = new FileOutputStream(outFileName);
			final GZIPOutputStream gzip = new GZIPOutputStream(file);
			final OutputStreamWriter writer = new OutputStreamWriter(gzip);
			final BufferedWriter buffer = new BufferedWriter(writer);
			return buffer;
		} catch (FileNotFoundException exception) {
			throw exception;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
