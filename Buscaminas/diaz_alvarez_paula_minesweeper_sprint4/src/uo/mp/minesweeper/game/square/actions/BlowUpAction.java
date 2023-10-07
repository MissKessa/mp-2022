package uo.mp.minesweeper.game.square.actions;

/**
 * It is the action associated with a mine box.
 * 
 * @author Paula
 * @version 2023
 *
 */
public class BlowUpAction implements Action {

	/**
	 * @return false as a mine was hit
	 */
	@Override
	public boolean execute() {
		return false;
	}

}
