package model;

import java.util.ArrayList;

/**
 * In the context of the social network a post refers to the abstraction that
 * encompasses any post that exists in it. As such it has a username, number of
 * likes and a list of comments. In addition, once the post is created, the
 * username cannot be changed. The rest of the fields can change their value.
 *
 * @author Paula Díaz Álvarez
 * @version 2023
 */
public abstract class Post {
	private final String username;
	private int numberOfLikes;
	private final ArrayList<String> comments;

	/**
	 * Main constructor. This builder takes all the necessary data (username) to
	 * create a Post; and initializes the numberOfLikes as 0, and the comments as an
	 * ArrayList without comments yet.
	 * 
	 * @param username is the string value for the username of the Post. It must be
	 *                 not-null and non-empty. Otherwise an RuntimeException is
	 *                 thrown.
	 * 
	 * @throws RuntimeException if any of the previous parameters value is not
	 *                          valid.
	 */
	public Post(String username) {
		checkParam(username == null, "The username cannot be null");
		checkParam(username == "", "The username cannot be empty");
		this.username = username;
		numberOfLikes = 0;
		comments = new ArrayList<String>(0);
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
	 * @return the value of the numberOfLikes as a positive integer
	 */
	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	/**
	 * Method that sets the numberOfLikes as a given positive integer
	 * 
	 * @param numberOfLikes is the new value for the numberOfLikes field. It must be
	 *                      a positive integer
	 * 
	 * @throws RuntimeException if the parameter value is not valid.
	 */
	public void setNumberOfLikes(int numberOfLikes) {
		checkParam(numberOfLikes < 0, "The numberOfLikes must be positive");
		this.numberOfLikes = numberOfLikes;
	}

	/**
	 * @return the value of the username as a non-zero, non-empty and non-blank
	 *         string.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return a copy of all the comments of the post
	 */
	public ArrayList<String> getComments() {
		ArrayList<String> copy = new ArrayList<String>(comments.size());
		for (String comment : comments) {
			copy.add(comment);
		}
		return copy;
	}

	/**
	 * Method that adds a non-null and non-empty comment to the comments field
	 * 
	 * @param comment is the comment going to be added
	 * 
	 * @throws RuntimeException if the parameter value is not valid.
	 */
	public void addComment(String comment) {
		checkParam(comment == null, "The comment cannot be null");
		checkParam(comment == "", "The comment cannot be empty");
		comments.add(comment);
	}

	/**
	 * Returns the String representation of a Post. The format is as follow "Post:
	 * [username=%s, numberOfLikes=%s, comments=%s]"
	 */
	@Override
	public String toString() {
		return String.format("Post: [username=%s, numberOfLikes=%s, comments=%s]", this.username, this.numberOfLikes,
				this.comments);
	}

	public abstract String toHTML();
}
