package uo.mp.minesweeper.game.square;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SquareUnflagTests {

	private Square square;

	@BeforeEach
	public void setUp() {
		square = new Square();
	}

	/**
	 * GIVEN: a square with the state OPENED
	 * <p>
	 * WHEN: call unflag()
	 * <p>
	 * THEN: Nothing changes because it's not FLAGGED
	 */
	@Test
	void flagOnOpenedBox() {
		square.open();
		assertTrue(square.isOpened());
		square.unflag();
		assertTrue(square.isOpened());
	}

	/**
	 * GIVEN: a square with the state CLOSED
	 * <p>
	 * WHEN: call unflag()
	 * <p>
	 * THEN: Nothing changes because it's not FLAGGED
	 */
	@Test
	void flagOnClosedBox() {
		assertFalse(square.isOpened());
		assertFalse(square.isFlagged());
		square.unflag();
		assertFalse(square.isFlagged());
		assertFalse(square.isOpened());
	}

	/**
	 * GIVEN: a square with the state FLAGGED
	 * <p>
	 * WHEN: call unflag()
	 * <p>
	 * THEN: The state is set to CLOSED
	 */
	@Test
	void flagOnFlaggedBox() {
		square.flag();
		assertTrue(square.isFlagged());
		square.unflag();
		assertFalse(square.isFlagged());
		assertFalse(square.isOpened());
	}
}
