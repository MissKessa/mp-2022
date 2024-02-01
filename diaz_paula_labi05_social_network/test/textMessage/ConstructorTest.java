package textMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.TextMessage;

public class ConstructorTest {
	private String theUsername;
	private String theMessage;
	private TextMessage text;
	private int theNumberOfLikes;
	private ArrayList<String> theComments;
	private String theContent; // of the toString

	@BeforeEach
	public void setUp() {
		theUsername = "hola123";
		theMessage = "Hello world";
		theNumberOfLikes = 0;
		theComments = new ArrayList<String>(0);
		theContent = String.format("TextMessage: [username=%s, numberOfLikes=%s, comments=%s, message=%s]", theUsername,
				theNumberOfLikes, theComments, theMessage);
	}

	/**
	 * GIVEN
	 * <p>
	 * WHEN Create a textMessage by passing valid parameters
	 * <p>
	 * THEN A correct image is created
	 */
	@Test
	void validParams() {
		text = new TextMessage(theUsername, theMessage);
		assertEquals(theUsername, text.getUsername());
		assertEquals(theMessage, text.getMessage());
		assertEquals(theNumberOfLikes, text.getNumberOfLikes());
		assertEquals(theComments, text.getComments());

		assertEquals(theContent, text.toString());
	}

	/**
	 * GIVEN
	 * <p>
	 * WHEN Create a textMessage by passing null instead of username
	 * <p>
	 * THEN RuntimeException throws
	 */
	@Test
	void nullUsername() {
		theUsername = null;
		try {
			text = new TextMessage(theUsername, theMessage);
			fail();
		} catch (Exception e) {
			assertEquals("The username cannot be null", e.getMessage());
		}
	}

	/**
	 * GIVEN
	 * <p>
	 * WHEN Create a textMessage by passing a blank instead of username
	 * <p>
	 * THEN RuntimeException throws
	 */
	@Test
	void blankUsername() {
		theUsername = "";
		try {
			text = new TextMessage(theUsername, theMessage);
			fail();
		} catch (Exception e) {
			assertEquals("The username cannot be empty", e.getMessage());
		}
	}

	/**
	 * GIVEN
	 * <p>
	 * WHEN Create a textMessage by passing null instead of message
	 * <p>
	 * THEN RuntimeException throws
	 */
	@Test
	void nullMessage() {
		theMessage = null;
		try {
			text = new TextMessage(theUsername, theMessage);
			fail();
		} catch (Exception e) {
			assertEquals("The message cannot be null", e.getMessage());
		}
	}

	/**
	 * GIVEN
	 * <p>
	 * WHEN Create a textMessage by passing a blank instead of message
	 * <p>
	 * THEN RuntimeException throws
	 */
	@Test
	void blankMessage() {
		theMessage = "";
		try {
			text = new TextMessage(theUsername, theMessage);
			fail();
		} catch (Exception e) {
			assertEquals("The message cannot be empty", e.getMessage());
		}
	}

}
