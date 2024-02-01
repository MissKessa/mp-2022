package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.Service;
import model.Image;
import model.Post;
import model.TextMessage;

public class AddPostTest {
	private Service service;
	private String thePosts;

	@BeforeEach
	public void setUp() {
		service = new Service();
	}

	/**
	 * GIVEN A service with one post
	 * <p>
	 * WHEN Adding again the post
	 * <p>
	 * THEN The post is not added to the posts of the service and addPost() returns
	 * false
	 */
	@Test
	void addPostAgain() {
		final TextMessage post = new TextMessage("hola123", "hello");
		thePosts = post.toString() + "\n";
		service.addPost(post);

		assertFalse(service.addPost(post));
		assertEquals(thePosts, service.getAllPostsAsString());

	}

	/**
	 * GIVEN A service without posts
	 * <p>
	 * WHEN Adding a correct image
	 * <p>
	 * THEN The correct image is added to the posts of the service and addPost()
	 * returns true
	 */
	@Test
	void addImage() {
		final Image image = new Image("hola123", "hola.png", "hello world");
		thePosts = image.toString() + "\n";

		assertTrue(service.addPost(image));
		assertEquals(thePosts, service.getAllPostsAsString());

	}

	/**
	 * GIVEN A service without posts
	 * <p>
	 * WHEN Adding a correct text message
	 * <p>
	 * THEN The correct text message is added to the posts of the service and
	 * addPost() returns true
	 */
	@Test
	void addTextMessage() {
		final TextMessage text = new TextMessage("hola123", "hello world");
		thePosts = text.toString() + "\n";

		assertTrue(service.addPost(text));
		assertEquals(thePosts, service.getAllPostsAsString());

	}

	/**
	 * GIVEN A service without posts
	 * <p>
	 * WHEN Adding a null post
	 * <p>
	 * THEN A RuntimeException is thrown
	 */
	@Test
	void addNullPost() {
		final Post post = null;
		try {
			service.addPost(post);
			fail();
		} catch (Exception e) {
			assertEquals("The post cannot be null", e.getMessage());
		}
	}

}
