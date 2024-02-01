package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.Service;
import model.Image;
import model.TextMessage;

public class GenerateHTMLTest {
	private Service service;

	private final String user1 = "hola123";
	private final String user2 = "jaime";

	private final Image image2 = new Image(user1, "eeee.jpg", "hey");
	private final String image2Html = String.format("<img src=%s>%s</img>", image2.getFilename(), image2.getCaption());

	private final Image image = new Image(user1, "hola.png", "hello world");
	private final String imageHtml = String.format("<img src=%s>%s</img>", image.getFilename(), image.getCaption());

	private final TextMessage text = new TextMessage(user1, "hello world");
	private final String textHtml = String.format("<p>%s</p>", text.getMessage());

	private final Image image3 = new Image(user2, "eeee.jpg", "hey");
	private final String image3Html = String.format("<img src=%s>%s</img>", image3.getFilename(), image3.getCaption());

	@BeforeEach
	public void setUp() {
		service = new Service();
		service.addPost(image2);
		service.addPost(image);
		service.addPost(text);
		service.addPost(image3);
	}

	/**
	 * GIVEN An empty service
	 * <p>
	 * WHEN Calling generateHTML()
	 * <p>
	 * THEN An empty string is returned
	 */
	@Test
	void emptyServiceToHTML() {
		service = new Service();
		String htmlFormat = "";
		assertEquals(htmlFormat, service.generateHTML());
	}

	/**
	 * GIVEN A service with image, iamge2, image3 and text
	 * <p>
	 * WHEN Calling generateHTML()
	 * <p>
	 * THEN A string with all the HTML representations is returned
	 */
	@Test
	void serviceToHTML() {
		String htmlFormat = image2Html + "\n" + imageHtml + "\n" + textHtml + "\n" + image3Html + "\n";
		assertEquals(htmlFormat, service.generateHTML());
	}

}
