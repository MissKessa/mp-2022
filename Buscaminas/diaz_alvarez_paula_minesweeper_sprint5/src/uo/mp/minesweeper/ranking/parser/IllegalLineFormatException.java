package uo.mp.minesweeper.ranking.parser;

import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * It's an error produce during the parsing. When a line produces this type of
 * error, is omitted and the error is logged.
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class IllegalLineFormatException extends Exception {

	/**
	 * It's the format for the error message
	 */
	private static final String MESSAGE_FORMAT = "INVALID LINE [%s] : %s";
	private static final long serialVersionUID = -2562541870850788940L;
	private int lineNumber;

	/**
	 * Main constructor for this exception by passing the error message that
	 * represents it
	 * 
	 * @param lineNumber is the number of the line in the file where the error has
	 *                   occur
	 * @param message    is the error message of the exception
	 */
	public IllegalLineFormatException(int lineNumber, String message) {
		super(message);
		ArgumentChecks.isTrue(lineNumber > 0);
		this.lineNumber = lineNumber;
	}

	@Override
	public String getMessage() {
		return String.format(MESSAGE_FORMAT, this.lineNumber, super.getMessage());
	}
}
