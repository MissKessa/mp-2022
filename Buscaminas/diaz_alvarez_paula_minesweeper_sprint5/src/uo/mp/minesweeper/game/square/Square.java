package uo.mp.minesweeper.game.square;

import uo.mp.minesweeper.game.square.actions.Action;
import uo.mp.minesweeper.util.check.ArgumentChecks;
import uo.mp.minesweeper.util.exceptions.GameException;

/**
 * This class represents the content and state of each square on the board. It
 * offers operations to change it's state and value.
 * <p>
 * The state can be CLOSED, OPENED or FLAGGED.
 * <p>
 * The value can be {@value #SQUARE_WITH_MINE_VALUE}; or greater than or equal
 * to {@value #EMPTY_SQUARE_VALUE} and less than or equal to
 * {@value #MAXIMUM_MINES_AROUND_VALUE}
 * <p>
 * The action defines what actions are going to be triggered when the stepOn()
 * is called
 * 
 * @author Paula Díaz Álvarez
 * @version 2023
 */

public class Square {
	/**
	 * It's the value associated to a square without a mine and without any mines
	 * around
	 */
	public final static int EMPTY_SQUARE_VALUE = 0;

	/**
	 * It's the value associated to a square with a mine
	 */
	public final static int SQUARE_WITH_MINE_VALUE = -1;

	/**
	 * It's the value associated to a square with the maximum number of mines around
	 */
	public final static int MAXIMUM_MINES_AROUND_VALUE = 8;

	/**
	 * It's the symbol that represents a square without a mine and without any mine
	 * around
	 */
	public final static char EMPTY_SQUARE_SYMBOL = ' ';

	/**
	 * It's the symbol that represents a square with a mine
	 */
	public final static char MINE_SYMBOL = '@';

	/**
	 * It's the symbol that represents a square that is closed (not discovered)
	 */
	public final static char CLOSED_SQUARE_SYMBOL = '#';

	/**
	 * It's the symbol that represents a square that is flagged
	 */
	public final static char FLAG_SYMBOL = ((char) 182);

	/**
	 * It's the error message shown if an invalid value for a square is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_VALUE = String.format(
			"The value must be greater than or equal to %o (empty square) and less than or equal to %o; or equal to %o (has a mine)",
			EMPTY_SQUARE_VALUE, MAXIMUM_MINES_AROUND_VALUE, SQUARE_WITH_MINE_VALUE);

	/**
	 * It's the error message shown if a null action is introduced
	 */
	public final static String ERROR_MESSAGE_NULL_ACTION = "The action must be not null";

	/**
	 * It's the error message shown if you try to step on a not closed box
	 */
	public final static String ERROR_MESSAGE_STEP_ON_NOT_CLOSED_BOX = "You tried to step on a box that is not closed";

	/**
	 * It's the error message shown if you try to open an opened box
	 */
	public static final String ERROR_MESSAGE_OPEN_OPENED_BOX = "You tried to open a box that is already opened";

	/**
	 * It's the error message shown if you try to unflag a box that is not flagged
	 */
	public static final String ERROR_MESSAGE_UNFLAG_NOT_FLAGGED_BOX = "You tried to unflag a box that is not flagged";

	/**
	 * It's the error message shown if you try to flag a box that is not closed
	 */
	public static final String ERROR_MESSAGE_FLAG_NOT_CLOSED_BOX = "You tried to flag a box that is not closed";

	private StateSquare state;
	private int value;
	private Action action;

	/**
	 * Main constructor. This builder creates a square with CLOSED status and
	 * {@value #EMPTY_SQUARE_VALUE} as value.
	 */
	public Square() {
		this.state = StateSquare.CLOSED;
		setValue(EMPTY_SQUARE_VALUE);
	}

	/**
	 * Sets the numeric value of the square
	 * 
	 * @param value is the new value for the square
	 * @throws IllegalArgumentException if the value is not
	 *                                  {@value #SQUARE_WITH_MINE_VALUE} or in the
	 *                                  range
	 *                                  [{@value #EMPTY_SQUARE_VALUE},{@value #MAXIMUM_MINES_AROUND_VALUE}].
	 */

	public void setValue(int value) {
		ArgumentChecks.isTrue(
				(value >= EMPTY_SQUARE_VALUE && value <= MAXIMUM_MINES_AROUND_VALUE) || value == SQUARE_WITH_MINE_VALUE,
				ERROR_MESSAGE_WRONG_VALUE);
		this.value = value;
	}

	/**
	 * @return the numeric value of the box
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * @return true if the box contains a mine. Otherwise, returns false.
	 */
	public boolean hasMine() {
		return getValue() == SQUARE_WITH_MINE_VALUE;
	}

	/**
	 * Sets the value of the box to {@value #SQUARE_WITH_MINE_VALUE}.
	 */
	public void putMine() {
		setValue(SQUARE_WITH_MINE_VALUE);
	}

	/**
	 * @return the character symbol representing the state of the square
	 */
	public char getSymbol() {
		if (isOpened()) {
			if (isEmpty())
				return EMPTY_SQUARE_SYMBOL;

			else if (!hasMine())
				return (char) (this.value + '0');

			else if (hasMine())
				return MINE_SYMBOL;

		} else if (isFlagged())
			return FLAG_SYMBOL;

		return CLOSED_SQUARE_SYMBOL;
	}

	/**
	 * Assign the given value to the attribute action in the square
	 * 
	 * @param action is the new value of the action field square. It must be not
	 *               null
	 * @throw IllegalArgumentException if the parameter is not valid
	 */
	public void setAction(Action action) {
		ArgumentChecks.notNull(action, ERROR_MESSAGE_NULL_ACTION);
		this.action = action;
	}

	/**
	 * Sets the box to OPENED status if it is CLOSED, and it causes associated
	 * actions to run by triggering execute().
	 * 
	 * @return true if it was safe to execute the action
	 * @throws GameException if the box was not closed
	 */
	public boolean stepOn() throws GameException {
		if (!isClosed())
			throw new GameException(ERROR_MESSAGE_STEP_ON_NOT_CLOSED_BOX);

		this.state = StateSquare.OPENED;
		return this.action.execute();
	}

	/**
	 * Set the box to OPENED status unconditionally
	 * 
	 * @throws GameException if the box was already opened
	 */
	public void open() throws GameException {
		if (isOpened())
			throw new GameException(ERROR_MESSAGE_OPEN_OPENED_BOX);
		this.state = StateSquare.OPENED;
	}

	/**
	 * @return true if the box status is OPENED and false otherwise.
	 */
	public boolean isOpened() {
		return this.state == StateSquare.OPENED;
	}

	/**
	 * 
	 * @return true if the state of the box is CLOSED and false otherwise
	 */
	public boolean isClosed() {
		return !isOpened() && !isFlagged();
	}

	/**
	 * If the box is CLOSED, its status is set to FLAGGED. Otherwise, it does
	 * nothing.
	 * 
	 * @throws GameException if the box was already flagged or open
	 */
	public void flag() throws GameException {
		if (!isClosed())
			throw new GameException(ERROR_MESSAGE_FLAG_NOT_CLOSED_BOX);

		this.state = StateSquare.FLAGGED;
	}

	/**
	 * If the box is FLAGGED, its status is set to CLOSED. Otherwise, it does
	 * nothing.
	 * 
	 * @throws GameException if the box was not flagged
	 */
	public void unflag() throws GameException {
		if (!isFlagged())
			throw new GameException(ERROR_MESSAGE_UNFLAG_NOT_FLAGGED_BOX);

		this.state = StateSquare.CLOSED;
	}

	/**
	 * @return true if the status of the box is FLAGGED and false otherwise.
	 */
	public boolean isFlagged() {
		return this.state == StateSquare.FLAGGED;
	}

	/**
	 * 
	 * @return true if the box is EMPTY and false otherwise
	 */
	public boolean isEmpty() {
		return this.value == EMPTY_SQUARE_VALUE;
	}

	/**
	 * Compares the content of a given object with this square.
	 * 
	 * @param object is the object to compare with the square
	 * @return true if the 2 squares have the same state and value
	 */
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Square) || object == null)
			return false;
		Square square = (Square) object;
		return this.getSymbol() == square.getSymbol() && this.getValue() == square.getValue();
	}

	/**
	 * Returns the character that represents the box graphically according to its
	 * current value and state
	 * 
	 * @return String corresponding to the graphical representation of the box
	 */
	@Override
	public String toString() {
		return "" + this.getSymbol();
	}

}
