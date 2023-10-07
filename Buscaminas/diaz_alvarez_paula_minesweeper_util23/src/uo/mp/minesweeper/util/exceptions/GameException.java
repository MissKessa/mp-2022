package uo.mp.minesweeper.util.exceptions;

/**
 * It's a recoverable error: The user is informed by displaying a message on the
 * screen and the menu will be redisplayed so that the user can correct the
 * error and run the game again.
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class GameException extends Exception {

	/**
	 * It's the format for the error message
	 */
	public static final String MESSAGE_FORMAT = "Recoverable error: %s";
	private static final long serialVersionUID = -2562541870850788940L;

	/**
	 * Main constructor for this exception by passing the error message that
	 * represents it
	 * 
	 * @param message is the error message of the exception
	 */
	public GameException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return String.format(MESSAGE_FORMAT, super.getMessage());
	}
}
