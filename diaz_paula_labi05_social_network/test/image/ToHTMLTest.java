package image;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.Image;

public class ToHTMLTest {
	private final Image image = new Image("hola123", "hola.png", "hello world");
	private final String imageToHTML = String.format("<img src=%s>%s</img>", image.getFilename(), image.getCaption());
	private final Image image2 = new Image("jaime", "eeee.jpg", "hey");
	private final String image2ToHTML = String.format("<img src=%s>%s</img>", image2.getFilename(),
			image2.getCaption());

	/**
	 * GIVEN Image with user: "hola123", filename: "hola.png", and caption: "hello
	 * world"
	 * <p>
	 * WHEN calling to toHTML()
	 * <p>
	 * THEN A string representation in HTML format of the image is returned
	 */
	@Test
	void imageToHTML() {
		assertEquals(imageToHTML, image.toHTML());
	}

	/**
	 * GIVEN Image2 with user: "jaime", filename: "eeee.jpg", and caption: "hey"
	 * <p>
	 * WHEN calling to toHTML()
	 * <p>
	 * THEN A string representation in HTML format of the image2 is returned
	 */
	@Test
	void image2ToHTML() {
		assertEquals(image2ToHTML, image2.toHTML());
	}

}
