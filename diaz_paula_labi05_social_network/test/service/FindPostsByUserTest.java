package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.Service;
import model.Image;
import model.Post;
import model.TextMessage;

public class FindPostsByUserTest {
	private final Service service = new Service();;
	private ArrayList<Post> thePosts;

	private final String user1 = "hola123";
	private final String user2 = "jaime";
	private final String user3 = "paula";

	private final Image post = new Image(user1, "eeee.jpg", "hey");
	private final Image image = new Image(user1, "hola.png", "hello world");
	private final TextMessage text = new TextMessage(user1, "hello world");
	private final Image post2 = new Image(user2, "eeee.jpg", "hey");

	@BeforeEach
	public void setUp() {
		service.addPost(post);
		service.addPost(image);
		service.addPost(text);
		service.addPost(post2);

		thePosts = new ArrayList<Post>();
	}

	/**
	 * GIVEN A service with post, image, text and post2. Post, image and text of
	 * user1; and post2 of user2
	 * <p>
	 * WHEN Searching for the posts of user1
	 * <p>
	 * THEN An ArrayList with all the posts of user 1 is returned
	 */
	@Test
	void findPostsUser1() {
		thePosts.add(post);
		thePosts.add(image);
		thePosts.add(text);

		assertEquals(thePosts, service.findPostsByUser(user1));

	}

	/**
	 * GIVEN A service with post, image, text and post2. Post, image and text of
	 * user1; and post2 of user2
	 * <p>
	 * WHEN Searching for the posts of user2
	 * <p>
	 * THEN An ArrayList with all the posts of user2 is returned
	 */
	@Test
	void findPostsUser2() {
		thePosts.add(post2);

		assertEquals(thePosts, service.findPostsByUser(user2));

	}

	/**
	 * GIVEN A service with post, image, text and post2. Post, image and text of
	 * user1; and post2 of user2
	 * <p>
	 * WHEN Searching for the posts of user3 (doesn't have posted anything)
	 * <p>
	 * THEN An empty ArrayList is returned
	 */
	@Test
	void findPostsUser3() {
		assertEquals(thePosts, service.findPostsByUser(user3));
	}

	/**
	 * GIVEN A service with post, image, text and post2. Post, image and text of
	 * user1; and post2 of user2
	 * <p>
	 * WHEN Searching for the posts of a null username
	 * <p>
	 * THEN A RuntimeException is thrown
	 */
	@Test
	void findPostsNullUser() {
		try {
			service.findPostsByUser(null);
			fail();
		} catch (Exception e) {
			assertEquals("The username cannot be null", e.getMessage());
		}
	}

	/**
	 * GIVEN A service with post, image, text and post2. Post, image and text of
	 * user1; and post2 of user2
	 * <p>
	 * WHEN Searching for the posts of a empty username
	 * <p>
	 * THEN A RuntimeException is thrown
	 */
	@Test
	void findPostsEmptyUser() {
		try {
			service.findPostsByUser("");
			fail();
		} catch (Exception e) {
			assertEquals("The username cannot be empty", e.getMessage());
		}
	}
}
