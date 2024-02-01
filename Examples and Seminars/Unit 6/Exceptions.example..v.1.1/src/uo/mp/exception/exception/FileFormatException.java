package uo.mp.exception.exception;

/**
 * Indicates the file just processed has some type of error that prevents
 * its normal processing. For example it is empty, it is not a text file, etc.
 * 
 * @author alb
 */
public class FileFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public FileFormatException() {
	}

	public FileFormatException(String message) {
		super(message);
	}

	public FileFormatException(Throwable cause) {
		super(cause);
	}

	public FileFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
