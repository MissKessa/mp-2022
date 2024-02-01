package post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Post;
import model.TextMessage;

public class AddCommentTest {
	private String theUsername;
	private int theNumberOfLikes;
	private Post post;
	private String message;
	private final ArrayList<String> comments = new ArrayList<String>();

	@BeforeEach
	public void setUp() {
		theUsername = "hola123";
		message = "hello world";
		theNumberOfLikes = 0;
		post = new TextMessage(theUsername, message);
	}

	/**
	 * GIVEN A post with theUsername as username
	 * <p>
	 * WHEN Adding a correct comment
	 * <p>
	 * THEN The correct comment is added to the list of the comments of the post
	 */
	@Test
	void addCorrectComment() {
		final String comment = "Hello world";
		comments.add(comment);
		post.addComment(comment);

		assertEquals(theUsername, post.getUsername());
		assertEquals(theNumberOfLikes, post.getNumberOfLikes());
		assertEquals(comments, post.getComments());

	}

	/**
	 * GIVEN A post with theUsername as username
	 * <p>
	 * WHEN Adding a null comment
	 * <p>
	 * THEN A RuntimeException throws
	 */
	@Test
	void addNullComment() {
		final String comment = null;
		try {
			post.addComment(comment);
			fail();
		} catch (Exception e) {
			assertEquals("The comment cannot be null", e.getMessage());
		}
	}

	/**
	 * GIVEN A post with theUsername as username
	 * <p>
	 * WHEN Adding an empty comment
	 * <p>
	 * THEN A RuntimeException throws
	 */
	@Test
	void addEmptyComment() {
		final String comment = "";
		try {
			post.addComment(comment);
			fail();
		} catch (Exception e) {
			assertEquals("The comment cannot be empty", e.getMessage());
		}
	}

}
