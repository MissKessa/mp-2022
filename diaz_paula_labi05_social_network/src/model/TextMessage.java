package model;

/**
 * In the context of the social network, a TextMessage is a post that also has a
 * message. The TextMessage has no default value except for the comments and the
 * number of likes which inherits the default value from the Post. In addition a
 * TextMessage is a mostly immutable object. Once created it is not allowed to
 * change any of its values except for the number of likes and the comments.
 *
 * @author Paula Díaz Álvarez
 * @version 2023
 */
public class TextMessage extends Post {
	private final String message;

	/**
	 * Main constructor. This builder takes all the necessary data such as username,
	 * and message to create a TextMessage.
	 * 
	 * @param username is the string value for the username of the TextMessage. It
	 *                 must be not-null and non-empty. Otherwise an RuntimeException
	 *                 is thrown.
	 * 
	 * @param message  is the string representation of the message of the
	 *                 TextMessage. It must be not-null and non-empty. Otherwise an
	 *                 RuntimeException is thrown.
	 * 
	 * @throws RuntimeException if any of the previous parameters value is not
	 *                          valid.
	 */
	public TextMessage(String username, String message) {
		super(username);

		checkParam(message == null, "The message cannot be null");
		checkParam(message == "", "The message cannot be empty");
		this.message = message;
	}

	/**
	 * Method that throws a RuntimeException with the given message if the given
	 * condition is True
	 * 
	 * @param condition    is the condition to be check. If it's True, an exception
	 *                     is thrown
	 * @param errorMessage is the message shown as an exception if the given
	 *                     condition is True
	 */
	private void checkParam(boolean condition, String errorMessage) {
		if (condition) {
			throw new RuntimeException(errorMessage);
		}
	}

	/**
	 * @return the message of the TextMessage
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Returns the String representation of a Post. The format is as follow
	 * "TextMessage: [username=%s, numberOfLikes=%s, comments=%s, message=%s]"
	 */
	@Override
	public String toString() {
		return String.format("TextMessage: [username=%s, numberOfLikes=%s, comments=%s, message=%s]", getUsername(),
				getNumberOfLikes(), getComments(), this.message);
	}

	/**
	 * This method returns the representation of the textMessage in HTML format: "
	 * <p>
	 * post_message
	 * </p>
	 * "
	 * 
	 * @return String representation of the text in HTML format
	 */
	@Override
	public String toHTML() {
		return String.format("<p>%s</p>", this.message);
	}
}
