package uo.mp.util.fileutil;

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

import uo.mp.util.check.ArgumentChecks;

/**
 * A utility class to read/write text lines from/to a compressed text file
 * (.txt.gz)
 */
public class ZipFileUtil extends BaseFileUtil {

	public List<String> readLines(String inFileName) throws FileNotFoundException {
//		List<String> lines = new ArrayList<>();
//		try (FileInputStream file = new FileInputStream(inFileName)) { // this try-catch can be used with resources
//																		// (things
//																		// that
//																		// can be closed), and closed it when it
//																		// finishes
//			final GZIPInputStream gzip = new GZIPInputStream(file);
//			final InputStreamReader reader = new InputStreamReader(gzip);
//			final BufferedReader buffer = new BufferedReader(reader);
//
//			// 2. for each line of the file
//			String line;
//			while ((line = buffer.readLine()) != null) { // IO Exception: file corrupted, remove the USB where it's
//															// while
//															// reading, the conextion is lose....
//				// 2.1 Load the line into an auxiliary list
//				lines.add(line);
//			}
//		} catch (FileNotFoundException exception) {
//			throw exception;
//		} catch (IOException exception) {
//			throw new RuntimeException(exception);
//
//		}
//		// 4.Return the list
//		return lines;
		return super.readLines(createReaderChain(inFileName));
	}

	public void writeLines(String outZippedFileName, List<String> lines) throws FileNotFoundException {

//		try (FileOutputStream file = new FileOutputStream(outZippedFileName)) { // this try-catch can be used with
//																				// resources
//			// (things that
//			// can be closed), and closed it when it finishes
//			final GZIPOutputStream gzip = new GZIPOutputStream(file);
//			final OutputStreamWriter writer = new OutputStreamWriter(gzip);
//			final BufferedWriter buffer = new BufferedWriter(writer);
//
//			// 2. for each line of the file
//			for (String line : lines) {
//				buffer.write(line);
//				buffer.newLine();
//			}
//		} catch (IOException exception) {
//			throw new RuntimeException(exception);
//		}
		super.writeLines(createWriterChain(outZippedFileName), lines);
	}

	@Override
	protected BufferedReader createReaderChain(String inFileName) throws FileNotFoundException {
		ArgumentChecks.notNull(inFileName, "The filename cannot be null");
		ArgumentChecks.notBlank(inFileName, "The filename cannot be made of blanks");

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
		ArgumentChecks.notNull(outFileName, "The filename cannot be null");
		ArgumentChecks.notBlank(outFileName, "The filename cannot be made of blanks");

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
