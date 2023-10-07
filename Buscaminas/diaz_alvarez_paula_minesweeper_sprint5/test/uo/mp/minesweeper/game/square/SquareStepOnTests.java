package uo.mp.minesweeper.game.square;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.actions.Action;
import uo.mp.minesweeper.util.exceptions.GameException;

public class SquareStepOnTests {
	private Square square;
	private TestAction action;

	class TestAction implements Action {
		boolean executed = false;

		@Override
		public boolean execute() {
			executed = true;
			return true;
		}
	}

	@BeforeEach
	public void setUp() {
		action = new TestAction();
		square = new Square();
		square.setAction(action);
	}

	/**
	 * GIVEN: a square with the state OPENED
	 * <p>
	 * WHEN: call stepOn()
	 * <p>
	 * THEN: Returns true and nothing changes because it's already OPENED (the
	 * action is not executed), (GameException is thrown)
	 */
	@Test
	void stepOnOpenedBox() {
		try {
			square.open();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		assertTrue(square.isOpened());
		try {
			assertTrue(square.stepOn());
			fail("A exception should be thrown");
		} catch (GameException e) {
			assertEquals(String.format(GameException.MESSAGE_FORMAT, Square.ERROR_MESSAGE_STEP_ON_NOT_CLOSED_BOX),
					e.getMessage());
		}
		assertFalse(action.executed);
		assertTrue(square.isOpened());
	}

	/**
	 * GIVEN: a square with the state CLOSED
	 * <p>
	 * WHEN: call stepOn()
	 * <p>
	 * THEN: Returns true and the state is set to OPENED (the action is executed)
	 */
	@Test
	void stepOnClosedBox() {
		assertFalse(square.isOpened());
		assertFalse(square.isFlagged());
		try {
			assertTrue(square.stepOn());
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		assertTrue(action.executed);
		assertTrue(square.isOpened());
	}

	/**
	 * GIVEN: a square with the state FLAGGED
	 * <p>
	 * WHEN: call stepOn()
	 * <p>
	 * THEN: Returns true and nothing changes because it's not CLOSED (the action is
	 * not executed), (GameException is thrown)
	 */
	@Test
	void stepOnFlaggedBox() {
		try {
			square.flag();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		assertTrue(square.isFlagged());
		try {
			assertTrue(square.stepOn());
			fail("An exception should be thrown");
		} catch (GameException e) {
			assertEquals(String.format(GameException.MESSAGE_FORMAT, Square.ERROR_MESSAGE_STEP_ON_NOT_CLOSED_BOX),
					e.getMessage());
		}
		assertFalse(action.executed);
		assertTrue(square.isFlagged());
	}

	/**
	 * GIVEN: a square with the state CLOSED
	 * <p>
	 * WHEN: call stepOn() twice
	 * <p>
	 * THEN: Returns true and the state is set to OPENED (the action is executed).
	 * Then returns true, because box is OPENED, (GameException is thrown)
	 */
	@Test
	void stepOnClosedBoxTwice() {
		assertFalse(square.isOpened());
		assertFalse(square.isFlagged());
		try {
			assertTrue(square.stepOn());
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		assertTrue(action.executed);
		assertTrue(square.isOpened());
		try {
			assertTrue(square.stepOn());
			fail("An exception should be thrown");
		} catch (GameException e) {
			assertEquals(String.format(GameException.MESSAGE_FORMAT, Square.ERROR_MESSAGE_STEP_ON_NOT_CLOSED_BOX),
					e.getMessage());
		}
		assertTrue(action.executed);
		assertTrue(square.isOpened());
	}
}
