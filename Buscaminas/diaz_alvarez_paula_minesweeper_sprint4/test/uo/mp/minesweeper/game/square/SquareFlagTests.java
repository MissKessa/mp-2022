package uo.mp.minesweeper.game.square;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.util.exceptions.GameException;

public class SquareFlagTests {
	private Square square;

	@BeforeEach
	public void setUp() {
		square = new Square();
	}

	/**
	 * GIVEN: a square with the state OPENED
	 * <p>
	 * WHEN: call flag()
	 * <p>
	 * THEN: Nothing changes because it's not CLOSED (GameException is thrown)
	 */
	@Test
	void flagOnOpenedBox() {
		try {
			square.open();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		assertTrue(square.isOpened());
		try {
			square.flag();
			fail("An exception should be thrown");
		} catch (GameException e) {

		}
		assertTrue(square.isOpened());
	}

	/**
	 * GIVEN: a square with the state CLOSED
	 * <p>
	 * WHEN: call flag()
	 * <p>
	 * THEN: The state is set to FLAGGED
	 */
	@Test
	void flagOnClosedBox() {
		assertFalse(square.isOpened());
		assertFalse(square.isFlagged());
		try {
			square.flag();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		assertTrue(square.isFlagged());
	}

	/**
	 * GIVEN: a square with the state FLAGGED
	 * <p>
	 * WHEN: call flag()
	 * <p>
	 * THEN: Nothing changes because it's already FLAGGED (GameException is thrown)
	 */
	@Test
	void flagOnFlaggedBox() {
		try {
			square.flag();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		assertTrue(square.isFlagged());
		try {
			square.flag();
			fail("An exception should be thrown");
		} catch (GameException e) {
		}
		assertTrue(square.isFlagged());
	}
}
