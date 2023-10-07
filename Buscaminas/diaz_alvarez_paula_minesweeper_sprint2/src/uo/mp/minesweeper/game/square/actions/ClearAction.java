package uo.mp.minesweeper.game.square.actions;

import java.util.List;

import uo.mp.minesweeper.game.square.Square;
import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * It is intended to be associated with an empty box
 * 
 * @author Paula
 * @version 2023
 *
 */

public class ClearAction implements Action {

	/**
	 * It's the error message shown if the neighbours introduced is null
	 */
	public final static String ERROR_MESSAGE_NULL_LIST_OF_NEIGHBOURS = "The list neighbours must be non null";
	/**
	 * It's the error message shown if the neighbours introduced is null
	 */
	public final static String ERROR_MESSAGE_NULL_NEIGHBOURS = "The list of neighbours cannot have null elements";

	private List<Square> neighbours;

	/**
	 * It's the main constructor. It receives as argument a list of squares
	 * containing all the safe neighbors of the square associated with this action.
	 * 
	 * @param neighbours is the list of safe neighbours
	 * @throws IllegalArgumentException if the parameter is null or has some null
	 *                                  elements
	 */
	public ClearAction(List<Square> neighbours) {
		ArgumentChecks.notNull(neighbours, ERROR_MESSAGE_NULL_LIST_OF_NEIGHBOURS);
		for (Square neighbour : neighbours)
			ArgumentChecks.notNull(neighbour, ERROR_MESSAGE_NULL_NEIGHBOURS);
		this.neighbours = neighbours;
	}

	/**
	 * It causes method stepOn to be run on the neighbour squares.
	 * 
	 * @return true, as this was a safe square
	 */
	@Override
	public boolean execute() {
		// clearNeighbours();
		for (Square neighbour : this.neighbours) {
			neighbour.stepOn();
		}
		return true;
	}

}
