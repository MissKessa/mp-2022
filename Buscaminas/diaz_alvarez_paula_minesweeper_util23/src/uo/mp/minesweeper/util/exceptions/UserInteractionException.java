package uo.mp.minesweeper.util.exceptions;

/**
 * It's a recoverable error: If the user types an option different from any of
 * the characters allowed in the different menu options (upper or lower case),
 * it will raise a UserInteractionException
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class UserInteractionException extends Exception {

	/**
	 * It's the format for the error message
	 */
	private static final String MESSAGE_FORMAT = "Recoverable error: %s";
	private static final long serialVersionUID = -2562541870850788940L;

	/**
	 * Main constructor for this exception by passing the error message that
	 * represents it
	 * 
	 * @param message is the error message of the exception
	 */
	public UserInteractionException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return String.format(MESSAGE_FORMAT, super.getMessage());
	}
}
