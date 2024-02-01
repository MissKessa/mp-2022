package api;

import java.util.ArrayList;

import model.Post;

/**
 * This class simulates the social network service. That is, the functionality
 * offered to the different users, whether they are a web API, a desktop GUI or
 * a CLI.
 * 
 * @author Paula Díaz Álvarez
 * @version 2023
 */
public class Service {
	private final ArrayList<Post> posts = new ArrayList<Post>(0);

	public Service() {
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
	 * Adds a post to the social network. To do so, it modifies the current instance
	 * by adding the post to the collection of posts. For the operation to succeed,
	 * the post must not be null. If it is then an IllegalArgumentException is
	 * thrown. It also returns a boolean value that represents the propagation of
	 * the return value of the add operation on the internal collection.
	 * 
	 * @param post is the post to add to the social network. It must be not null.
	 * @return propagates the return value of the add operation over the internal
	 *         collection.
	 * @throws RuntimeException if the post to add is null.
	 */
	public boolean addPost(Post post) {
		checkParam(post == null, "The post cannot be null");

		if (posts.contains(post)) {
			return false;
		}
		posts.add(post);
		return true;
	}

	/**
	 * Returns all the post of a given user as an ArrayList
	 * 
	 * @param username is the username of the person who we want to find the posts
	 * @return an Arraylist with all the post of the given user
	 * @throws RuntimeException if the given username is null or empty.
	 */
	public ArrayList<Post> findPostsByUser(String username) {
		checkParam(username == null, "The username cannot be null");
		checkParam(username == "", "The username cannot be empty");

		ArrayList<Post> userPosts = new ArrayList<Post>();
		for (Post post : posts) {
			if (post.getUsername() == username) {
				userPosts.add(post);
			}
		}
		return userPosts;
	}

	/**
	 * Returns a string with all the posts. The content of each one in one line
	 * 
	 * @return the string with the content of all the posts
	 */
	public String getAllPostsAsString() {
		final StringBuilder allPosts = new StringBuilder();
		for (Post post : posts) {
			allPosts.append(post.toString() + "\n");
		}
		return allPosts.toString();
	}

	/**
	 * This method returns a String that will contain the HTML representation of
	 * each post
	 * 
	 * @return a String that will contain the HTML representation of each post
	 */
	public String generateHTML() {
		final StringBuilder htmlFormat = new StringBuilder();
		for (Post post : this.posts) {
			htmlFormat.append(post.toHTML() + "\n");
		}
		return htmlFormat.toString();
	}
}
