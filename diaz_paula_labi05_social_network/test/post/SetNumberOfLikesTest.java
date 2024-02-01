package post;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Post;
import model.TextMessage;

public class SetNumberOfLikesTest {
	private String theUsername;
	private ArrayList<String> theComments;
	private String message;
	private Post post;

	@BeforeEach
	public void setUp() {
		theUsername = "hola123";
		theComments = new ArrayList<String>(0);
		message = "hello world";
		post = new TextMessage(theUsername, message);
	}

	/**
	 * GIVEN A post with theUsername as username
	 * <p>
	 * WHEN The number of likes is set to 0
	 * <p>
	 * THEN The number of likes of the post is updated to 0
	 */
	@Test
	void numberOfLikesTo0() {
		final int theNumberOfLikes = 0;
		post.setNumberOfLikes(theNumberOfLikes);

		assertEquals(theUsername, post.getUsername());
		assertEquals(theNumberOfLikes, post.getNumberOfLikes());
		assertEquals(theComments, post.getComments());

	}

	/**
	 * GIVEN A post with theUsername as username
	 * <p>
	 * WHEN The number of likes is set to 1000
	 * <p>
	 * THEN The number of likes of the post is updated to 1000
	 */
	@Test
	void numberOfLikesTo1000() {
		final int theNumberOfLikes = 1000;
		post.setNumberOfLikes(theNumberOfLikes);

		assertEquals(theUsername, post.getUsername());
		assertEquals(theNumberOfLikes, post.getNumberOfLikes());
		assertEquals(theComments, post.getComments());

	}

	/**
	 * GIVEN A post with theUsername as username
	 * <p>
	 * WHEN The number of likes is set to -1
	 * <p>
	 * THEN a RuntimeException is thrown
	 */
	@Test
	void invalidNumberOfLikes() {
		final int theNumberOfLikes = -1;
		try {
			post.setNumberOfLikes(theNumberOfLikes);
		} catch (Exception e) {
			assertEquals("The numberOfLikes must be positive", e.getMessage());
		}

	}

	/**
	 * GIVEN A post with theUsername as username
	 * <p>
	 * WHEN The number of likes is set to -1000
	 * <p>
	 * THEN a RuntimeException is thrown
	 */
	@Test
	void invalidNumberOfLikes2() {
		final int theNumberOfLikes = -1000;
		try {
			post.setNumberOfLikes(theNumberOfLikes);
		} catch (Exception e) {
			assertEquals("The numberOfLikes must be positive", e.getMessage());
		}

	}

}
