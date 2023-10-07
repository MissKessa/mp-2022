package uo.mp.minesweeper.game.square;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	 * THEN: Nothing changes because it's not CLOSED
	 */
	@Test
	void flagOnOpenedBox() {
		square.open();
		assertTrue(square.isOpened());
		square.flag();
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
		square.flag();
		assertTrue(square.isFlagged());
	}

	/**
	 * GIVEN: a square with the state FLAGGED
	 * <p>
	 * WHEN: call flag()
	 * <p>
	 * THEN: Nothing changes because it's already FLAGGED
	 */
	@Test
	void flagOnFlaggedBox() {
		square.flag();
		assertTrue(square.isFlagged());
		square.flag();
		assertTrue(square.isFlagged());
	}
}
