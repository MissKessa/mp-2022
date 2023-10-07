package uo.mp.minesweeper.game.square.actions;

/**
 * It is intended to be associated with boxes with a numerical clue
 * 
 * @author Paula Díaz Álvarez
 * @version 2023
 *
 */

public class NullAction implements Action {

	/**
	 * @return true as it was safe to step on that square
	 */
	@Override
	public boolean execute() {
		return true;
	}

}
