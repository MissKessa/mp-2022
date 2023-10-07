package uo.mp.minesweeper.game.square.actions;

/**
 * It's the set of events triggered when method stepOn()is invoked on a Square.
 * 
 * @author Paula
 * @version 2023
 *
 */
public interface Action {
	/**
	 * It causes appropriate actions to run when calling StepOn().
	 * 
	 * @return true if it was safe to execute this action (no hit a mine), false
	 *         otherwise
	 */
	public boolean execute();
}
