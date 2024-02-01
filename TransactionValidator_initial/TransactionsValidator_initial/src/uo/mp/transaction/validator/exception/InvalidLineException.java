package uo.mp.transaction.validator.exception;

public class InvalidLineException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidLineException() {
		// TODO Auto-generated constructor stub
	}

	public InvalidLineException(int lineNumber, String description) {
		super(String.format("PARSING ERROR en línea %s: %s", lineNumber, description));
	}

	public InvalidLineException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidLineException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidLineException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
