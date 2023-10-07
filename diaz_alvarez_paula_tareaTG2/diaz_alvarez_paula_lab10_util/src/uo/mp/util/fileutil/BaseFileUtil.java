package uo.mp.util.fileutil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uo.mp.util.check.ArgumentChecks;

public abstract class BaseFileUtil {
	protected abstract BufferedReader createReaderChain(String inFileName) throws FileNotFoundException;

	protected abstract BufferedWriter createWriterChain(String outFileName) throws FileNotFoundException;

	protected List<String> readLines(BufferedReader givenBuffer) {
		ArgumentChecks.notNull(givenBuffer, "The buffer cannot be null");
		List<String> lines = new ArrayList<>();
		// 2. for each line of the file
		String line;
		try (BufferedReader buffer = givenBuffer) {
			while ((line = buffer.readLine()) != null) { // IO Exception: file corrupted, remove the USB where it's
				// while reading, the conextion is lose....
				// 2.1 Load the line into an auxiliary list
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

	protected void writeLines(BufferedWriter givenBuffer, List<String> lines) throws FileNotFoundException {
		ArgumentChecks.notNull(givenBuffer, "The buffer cannot be null");
		ArgumentChecks.notNull(lines, "The lines cannot be null");
		ArgumentChecks.noNullElements(lines.toArray(), "The lines cannot have one null line");

		try (final BufferedWriter buffer = givenBuffer) { // this try-catch can be used with
			// resources (things that can be closed), and closed it when it finishes

			// 2. for each line of the file
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
