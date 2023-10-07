package uo.mp.minesweeper.game.square.actions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NullActionExecuteTests {
	private NullAction action;

	@BeforeEach
	public void setUp() {
		action = new NullAction();
	}

	/**
	 * GIVEN: an action of type NullAction
	 * <p>
	 * WHEN: calling execute()
	 * <p>
	 * THEN: it returns true as the square associated with this type of action
	 * doesn't have a mine
	 */
	@Test
	void execute() {
		assertTrue(action.execute());
	}

}
