package uo.mp.minesweeper.game.square;

import uo.mp.minesweeper.game.square.actions.Action;
import uo.mp.minesweeper.util.check.ArgumentChecks;

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
 * The action defines what actions are going to be trigered when the stepOn() is
 * called
 * 
 * @author Paula D�az �lvarez
 * @version 2023
 */

public class Square {
	/**
	 * It's the value associated to a square without a mine and without a mine
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
	 * It's the symbol that represents a square without a mine and without a mine
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
	 * Sets the box to OPENED status if it is CLOSED and it causes associated
	 * actions to run by triggering execute().
	 * 
	 * @return true if it was safe to execute the action, or if the square was
	 *         opened or flagged
	 */
	public boolean stepOn() {
		if (this.state == StateSquare.CLOSED) {
			this.state = StateSquare.OPENED;
			return this.action.execute();
		}
		return true;
	}

	/**
	 * Set the box to OPENED status unconditionally
	 */
	public void open() {
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
	 */
	public void flag() {
		if (this.state == StateSquare.CLOSED)
			this.state = StateSquare.FLAGGED;
	}

	/**
	 * If the box is FLAGGED, its status is set to CLOSED. Otherwise, it does
	 * nothing.
	 */
	public void unflag() {
		if (this.state == StateSquare.FLAGGED)
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
