package textMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.TextMessage;

public class ToHTMLTest {
	private final TextMessage text = new TextMessage("hola123", "hello world");
	private final String textToHTML = String.format("<p>%s</p>", text.getMessage());
	private final TextMessage text2 = new TextMessage("jaime", "hey");
	private final String text2ToHTML = String.format("<p>%s</p>", text2.getMessage());

	/**
	 * GIVEN text with user: "hola123", message: "hello world"
	 * <p>
	 * WHEN calling to toHTML()
	 * <p>
	 * THEN A string representation in HTML format of the text is returned
	 */
	@Test
	void imageToHTML() {
		assertEquals(textToHTML, text.toHTML());
	}

	/**
	 * GIVEN Image2 with user: "jaime", message: "hey"
	 * <p>
	 * WHEN calling to toHTML()
	 * <p>
	 * THEN A string representation in HTML format of the text2 is returned
	 */
	@Test
	void image2ToHTML() {
		assertEquals(text2ToHTML, text2.toHTML());
	}

}
