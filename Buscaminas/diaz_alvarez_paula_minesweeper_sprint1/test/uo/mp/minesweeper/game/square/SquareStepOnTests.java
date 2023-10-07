package uo.mp.minesweeper.game.square;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SquareStepOnTests {
	private Square square;

	@BeforeEach
	public void setUp() {
		square = new Square();
	}

	/**
	 * GIVEN: a square with the state OPENED
	 * <p>
	 * WHEN: call stepOn()
	 * <p>
	 * THEN: Nothing changes because it's already OPENED
	 */
	@Test
	void stepOnOpenedBox() {
		square.open();
		assertTrue(square.isOpened());
		square.stepOn();
		assertTrue(square.isOpened());
	}

	/**
	 * GIVEN: a square with the state CLOSED
	 * <p>
	 * WHEN: call stepOn()
	 * <p>
	 * THEN: The state is set to OPENED
	 */
	@Test
	void stepOnClosedBox() {
		assertFalse(square.isOpened());
		assertFalse(square.isFlagged());
		square.stepOn();
		assertTrue(square.isOpened());
	}

	/**
	 * GIVEN: a square with the state FLAGGED
	 * <p>
	 * WHEN: call stepOn()
	 * <p>
	 * THEN: Nothing changes because it's not CLOSED
	 */
	@Test
	void stepOnFlaggedBox() {
		square.flag();
		assertTrue(square.isFlagged());
		square.stepOn();
		assertTrue(square.isFlagged());
	}

	/**
	 * GIVEN: a square with the state CLOSED
	 * <p>
	 * WHEN: call stepOn() twice
	 * <p>
	 * THEN: The state is set to OPENED
	 */
	@Test
	void stepOnClosedBoxTwice() {
		assertFalse(square.isOpened());
		assertFalse(square.isFlagged());
		square.stepOn();
		assertTrue(square.isOpened());
		square.stepOn();
		assertTrue(square.isOpened());
	}
}
