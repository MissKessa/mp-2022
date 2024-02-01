package uo.mp.lab11.marker.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import uo.mp.util.check.ArgumentChecks;

/**
 * A utility class to read/write text lines from/to a text file
 */
public class FileUtil {

	public List<String> readLines(String inFileName) throws FileNotFoundException {
		List<String> lines = new ArrayList<>();
		String line;

		try (final BufferedReader buffer = createReaderChain(inFileName)) {
			while ((line = buffer.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		return lines;
	}

	protected BufferedReader createReaderChain(String inFileName) throws FileNotFoundException {
		ArgumentChecks.notNull(inFileName, "The filename cannot be null");
		ArgumentChecks.notBlank(inFileName, "The filename cannot be made of blanks");

		try (FileReader file = new FileReader(inFileName)) {
			final BufferedReader buffer = new BufferedReader(file);
			return buffer;
		} catch (FileNotFoundException exception) {
			throw exception;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	public void writeLines(String outFileName, List<String> lines) throws FileNotFoundException {

		try (final BufferedWriter buffer = createWriterChain(outFileName)) {
			for (String line : lines) {
				buffer.write(line);
				buffer.newLine();
			}
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

	protected BufferedWriter createWriterChain(String outFileName) throws FileNotFoundException {
		ArgumentChecks.notNull(outFileName, "The filename cannot be null");
		ArgumentChecks.notBlank(outFileName, "The filename cannot be made of blanks");

		try (Writer file = new FileWriter(outFileName)) {
			final BufferedWriter buffer = new BufferedWriter(file);
			return buffer;
		} catch (FileNotFoundException exception) {
			throw exception;
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}

}
