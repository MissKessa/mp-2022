package uo.mp.minesweeper.game.square;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.actions.Action;

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
	 * action is not executed)
	 */
	@Test
	void stepOnOpenedBox() {
		square.open();
		assertTrue(square.isOpened());
		assertTrue(square.stepOn());
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
		assertTrue(square.stepOn());
		assertTrue(action.executed);
		assertTrue(square.isOpened());
	}

	/**
	 * GIVEN: a square with the state FLAGGED
	 * <p>
	 * WHEN: call stepOn()
	 * <p>
	 * THEN: Returns true and nothing changes because it's not CLOSED (the action is
	 * not executed)
	 */
	@Test
	void stepOnFlaggedBox() {
		square.flag();
		assertTrue(square.isFlagged());
		assertTrue(square.stepOn());
		assertFalse(action.executed);
		assertTrue(square.isFlagged());
	}

	/**
	 * GIVEN: a square with the state CLOSED
	 * <p>
	 * WHEN: call stepOn() twice
	 * <p>
	 * THEN: Returns true and the state is set to OPENED (the action is executed).
	 * Then returns true, because box is OPENED
	 */
	@Test
	void stepOnClosedBoxTwice() {
		assertFalse(square.isOpened());
		assertFalse(square.isFlagged());
		assertTrue(square.stepOn());
		assertTrue(action.executed);
		assertTrue(square.isOpened());
		assertTrue(square.stepOn());
		assertTrue(action.executed);
		assertTrue(square.isOpened());
	}
}
