package image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Image;

public class ConstructorTest {
	private String theUsername;
	private String theFilename;
	private String theCaption;
	private String theContent; // of the toString
	private int theNumberOfLikes;
	private ArrayList<String> theComments;
	private Image image;

	@BeforeEach
	public void setUp() {
		theUsername = "hola123";
		theFilename = "photo.png";
		theCaption = "Hello world";
		theNumberOfLikes = 0;
		theComments = new ArrayList<String>(0);
		theContent = String.format("Image: [username=%s, numberOfLikes=%s, comments=%s, filename=%s, caption=%s]",
				theUsername, theNumberOfLikes, theComments, theFilename, theCaption);

	}

	/**
	 * GIVEN
	 * <p>
	 * WHEN Create a image by passing valid parameters
	 * <p>
	 * THEN A correct image is created
	 */
	@Test
	void validParams() {
		image = new Image(theUsername, theFilename, theCaption);
		assertEquals(theUsername, image.getUsername());
		assertEquals(theFilename, image.getFilename());
		assertEquals(theCaption, image.getCaption());
		assertEquals(theNumberOfLikes, image.getNumberOfLikes());
		assertEquals(theComments, image.getComments());

		assertEquals(theContent, image.toString());
	}

	/**
	 * GIVEN
	 * <p>
	 * WHEN Create a image by passing null instead of username
	 * <p>
	 * THEN RuntimeException throws
	 */
	@Test
	void nullUsername() {
		theUsername = null;
		try {
			image = new Image(theUsername, theFilename, theCaption);
			fail();
		} catch (Exception e) {
			assertEquals("The username cannot be null", e.getMessage());
		}
	}

	/**
	 * GIVEN
	 * <p>
	 * WHEN Create a image by passing a blank instead of username
	 * <p>
	 * THEN RuntimeException throws
	 */
	@Test
	void blankUsername() {
		theUsername = "";
		try {
			image = new Image(theUsername, theFilename, theCaption);
			fail();
		} catch (Exception e) {
			assertEquals("The username cannot be empty", e.getMessage());
		}
	}

	/**
	 * GIVEN
	 * <p>
	 * WHEN Create a image by passing null instead of filename
	 * <p>
	 * THEN RuntimeException throws
	 */
	@Test
	void nullFilename() {
		theFilename = null;
		try {
			image = new Image(theUsername, theFilename, theCaption);
			fail();
		} catch (Exception e) {
			assertEquals("The filename cannot be null", e.getMessage());
		}
	}

	/**
	 * GIVEN
	 * <p>
	 * WHEN Create a image by passing a blank instead of filename
	 * <p>
	 * THEN RuntimeException throws
	 */
	@Test
	void blankFilename() {
		theFilename = "";
		try {
			image = new Image(theUsername, theFilename, theCaption);
			fail();
		} catch (Exception e) {
			assertEquals("The filename cannot be empty", e.getMessage());
		}

	}

	/**
	 * GIVEN
	 * <p>
	 * WHEN Create a image by passing null instead of caption
	 * <p>
	 * THEN RuntimeException throws
	 */
	@Test
	void nullCaption() {
		theCaption = null;
		try {
			image = new Image(theUsername, theFilename, theCaption);
			fail();
		} catch (Exception e) {
			assertEquals("The caption cannot be null", e.getMessage());
		}
	}

	/**
	 * GIVEN
	 * <p>
	 * WHEN Create a image by passing blank instead of caption
	 * <p>
	 * THEN RuntimeException throws
	 */
	@Test
	void blankCaption() {
		theCaption = "";
		try {
			image = new Image(theUsername, theFilename, theCaption);
			fail();
		} catch (Exception e) {
			assertEquals("The caption cannot be empty", e.getMessage());
		}
	}

}
