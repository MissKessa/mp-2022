package uo.mp.minesweeper.util.console;

import java.io.IOException;
import java.io.InputStream;

import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * This class extends the java InputStream by creating an InputStream that
 * cannot be closed. This behavior is achieved by delegating all methods to
 * their default behavior with the exception of the close method. In the latter,
 * nothing is done.
 * <p>
 * The purpose of this class is to encapsulate those input streams that are used
 * when reading from the console but must not be closed. Like for example the
 * {@code System.in}.
 *
 * @author Paula
 * @version 2023
 */
public final class UnclosableInputStream extends InputStream {

	private final InputStream inputStream;

	public UnclosableInputStream(final InputStream inputStream) {
		ArgumentChecks.notNull(inputStream, "The inputStream cannot be null.");
		this.inputStream = inputStream;
	}

	@Override
	public int read() throws IOException {
		return inputStream.read();
	}

	@Override
	public int read(final byte[] b) throws IOException {
		return inputStream.read(b);
	}

	@Override
	public int read(final byte[] b, int off, int len) throws IOException {
		return inputStream.read(b, off, len);
	}

	@Override
	public long skip(final long n) throws IOException {
		return inputStream.skip(n);
	}

	@Override
	public int available() throws IOException {
		return inputStream.available();
	}

	@Override
	public synchronized void mark(final int readlimit) {
		inputStream.mark(readlimit);
	}

	@Override
	public synchronized void reset() throws IOException {
		inputStream.reset();
	}

	@Override
	public boolean markSupported() {
		return inputStream.markSupported();
	}

	/**
	 * This method has been modified such that it does not propagate nor executes
	 * the close action.
	 */
	@Override
	public void close() throws IOException {
		// do nothing
	}
}
