package model;

/**
 * In the context of the social network, a Image is a post that also has a
 * filename and a caption. The Image has no default value except for the
 * comments and the number of likes which inherits the default value from the
 * Post. In addition a Image is a mostly immutable object. Once created it is
 * not allowed to change any of its values except for the number of likes and
 * the comments.
 *
 * @author Paula Díaz Álvarez
 * @version 2023
 */
public class Image extends Post {
	private final String filename;
	private final String caption;

	/**
	 * Main constructor. This builder takes all the necessary data such as username,
	 * filename and caption to create a Image.
	 * 
	 * @param username is the string value for the username of the Image. It must be
	 *                 not-null and non-empty. Otherwise an RuntimeException is
	 *                 thrown.
	 * 
	 * @param filename is the string representation of the filename of the Image. It
	 *                 must be not-null and non-empty. Otherwise an RuntimeException
	 *                 is thrown.
	 * 
	 * @param caption  is the string representation of the caption of the Image. It
	 *                 must be not-null and non-empty. Otherwise an RuntimeException
	 *                 is thrown.
	 * 
	 * @throws RuntimeException if any of the previous parameters value is not
	 *                          valid.
	 */
	public Image(String username, String filename, String caption) {
		super(username);

		checkParam(filename == null, "The filename cannot be null");
		checkParam(filename == "", "The filename cannot be empty");
		this.filename = filename;

		checkParam(caption == null, "The caption cannot be null");
		checkParam(caption == "", "The caption cannot be empty");
		this.caption = caption;
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
	 * @return the value of the filename as a non-zero, non-empty and non-blank
	 *         string.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @return the value of the caption as a non-zero, non-empty and non-blank
	 *         string.
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * Returns the String representation of a Image. The format is as follow "Image:
	 * [username=%s, numberOfLikes=%s, comments=%s, filename=%s, caption=%s]".
	 */
	@Override
	public String toString() {
		return String.format("Image: [username=%s, numberOfLikes=%s, comments=%s, filename=%s, caption=%s]",
				getUsername(), getNumberOfLikes(), getComments(), this.filename, this.caption);
	}

	/**
	 * This method returns the representation of the image in HTML format:
	 * "<img src=file_name>caption</img>"
	 * 
	 * @return String representation of the image in HTML format
	 */
	@Override
	public String toHTML() {
		return String.format("<img src=%s>%s</img>", this.filename, this.caption);
	}
}
