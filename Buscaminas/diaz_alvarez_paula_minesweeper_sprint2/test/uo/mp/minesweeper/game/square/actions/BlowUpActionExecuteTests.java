package uo.mp.minesweeper.game.square.actions;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlowUpActionExecuteTests {
	private BlowUpAction action;

	@BeforeEach
	public void setUp() {
		action = new BlowUpAction();
	}

	/**
	 * GIVEN: an action of type BlowUpAction
	 * <p>
	 * WHEN: calling execute()
	 * <p>
	 * THEN: it returns false as the square associated with this type of action is a
	 * mine
	 */
	@Test
	void execute() {
		assertFalse(action.execute());
	}

}
