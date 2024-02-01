package uo.mp.util.fileutil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import uo.mp.util.check.ArgumentChecks;

/**
 * A utility class to read/write text lines from/to a text file
 */
public class FileUtil extends BaseFileUtil {

	public List<String> readLines(String inFileName) throws FileNotFoundException {
//		List<String> lines = new ArrayList<>();
//		// 2. for each line of the file
//		String line;
//
//		try (final BufferedReader buffer = createReaderChain(inFileName)) {
//			while ((line = buffer.readLine()) != null) { // IO Exception: file corrupted, remove the USB where it's
//															// while
//															// reading, the conextion is lose....
//				// 2.1 Load the line into an auxiliary list
//				lines.add(line);
//			}
//		} catch (IOException exception) {
//			throw new RuntimeException(exception);
//		}
//
//		// 4.Return the list
//		return lines;
		return super.readLines(createReaderChain(inFileName));
	}

	public void writeLines(String outFileName, List<String> lines) throws FileNotFoundException {

//		try (final BufferedWriter buffer = createWriterChain(outFileName)) { // this try-catch can be used with
//			// resources (things that can be closed), and closed it when it finishes
//
//			// 2. for each line of the file
//			for (String line : lines) {
//				buffer.write(line);
//				buffer.newLine();
//			}
//		} catch (IOException exception) {
//			throw new RuntimeException(exception);
//		}
		super.writeLines(createWriterChain(outFileName), lines);
	}

	@Override
	protected BufferedReader createReaderChain(String inFileName) throws FileNotFoundException {
		ArgumentChecks.notNull(inFileName, "The filename cannot be null");
		ArgumentChecks.notBlank(inFileName, "The filename cannot be made of blanks");

		try {
			FileReader file = new FileReader(inFileName);
			final BufferedReader buffer = new BufferedReader(file);
			return buffer;
		} catch (FileNotFoundException exception) {
			throw exception;
		}
	}

	@Override
	protected BufferedWriter createWriterChain(String outFileName) throws FileNotFoundException {
		ArgumentChecks.notNull(outFileName, "The filename cannot be null");
		ArgumentChecks.notBlank(outFileName, "The filename cannot be made of blanks");

		try {
			Writer file = new FileWriter(outFileName);
			final BufferedWriter buffer = new BufferedWriter(file);
			return buffer;
		} catch (FileNotFoundException exception) {
			throw exception;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
