package uo.mp.minesweeper.game.square;

import uo.mp.minesweeper.game.square.actions.Action;
import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * This class represents the content and state of each square on the board. It
 * offers operations to change it's state and value. The state can be CLOSED,
 * OPENED or FLAGGED. The value can be {@value #SQUARE_WITH_MINE}; or greater
 * than or equal to {@value #EMPTY_SQUARE} and less than or equal to
 * {@value #MAXIMUM_MINES_AROUND}
 * 
 * @author Paula Díaz Álvarez
 * @version 2023
 */

public class Square {
	/**
	 * It's the value associated to a square without a mine and without a mine
	 * around
	 */
	public final static int EMPTY_SQUARE = 0;
	/**
	 * It's the value associated to a square with a mine
	 */
	public final static int SQUARE_WITH_MINE = -1;
	/**
	 * It's the value associated to a square with the maximum number of mines around
	 */
	public final static int MAXIMUM_MINES_AROUND = 8;

	/**
	 * It's the symbol that represents a square without a mine and without a mine
	 */
	public final static char EMPTY_SQUARE_SYMBOL = ' ';
	/**
	 * It's the symbol that represents a square with a mine
	 */
	public final static char MINE_SYMBOL = '@';
	/**
	 * It's the symbol that represents a square that is closed (not discover)
	 */
	public final static char CLOSED_SQUARE_SYMBOL = '#';
	/**
	 * It's the symbol that represents a square that is flagged
	 */
	public final static char FLAG_SYMBOL = ((char) 182);

	/**
	 * It's the error message shown if an invalid value is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_VALUE = String.format(
			"The value must be greater than or equal to %o (empty square) and less than or equal to %o; or equal to %o (has a mine)",
			EMPTY_SQUARE, MAXIMUM_MINES_AROUND, SQUARE_WITH_MINE);

	/**
	 * It's the error message shown if a null action is introduced
	 */
	public final static String ERROR_MESSAGE_NULL_ACTION = "The action must be not null";

	private StateSquare state;
	private int value;
	private Action action;

	/**
	 * Main constructor. This builder creates a square with CLOSED status and
	 * {@value #EMPTY_SQUARE} as value.
	 */
	public Square() {
		this.state = StateSquare.CLOSED;
		setValue(EMPTY_SQUARE);
		// set action?
	}

	/**
	 * Sets the box to OPENED status if it is CLOSED, it causes associated actions
	 * to run by triggering execute() and returns true if it was safe (it doesn't
	 * have a mine). Otherwise (OPENED or FLAGGED) and returns true.
	 * 
	 * @return true if it was safe to execute the action or the square was opened or
	 *         flagged
	 */
	public boolean stepOn() {
		if (this.state == StateSquare.CLOSED) {
			this.state = StateSquare.OPENED;
			return this.action.execute();
		}
		return true;
	}

	/**
	 * Returns the character that represents the box graphically according to its
	 * current value and state
	 * 
	 * @return String corresponding to the graphical representation of the box
	 */
	@Override
	public String toString() {
		return "" + this.getState();
	}

	/**
	 * @return the character symbol representing the state of the square
	 */
	public char getState() {
		if (this.state == StateSquare.OPENED) {
			if (this.value == EMPTY_SQUARE)
				return EMPTY_SQUARE_SYMBOL;

			else if (!this.hasMine()) // and not empty
				return (char) (this.value + '0');

			else if (this.value == SQUARE_WITH_MINE)
				return MINE_SYMBOL;

		} else if (this.state == StateSquare.FLAGGED)
			return FLAG_SYMBOL;

		return CLOSED_SQUARE_SYMBOL;
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
	 * Set the box to OPENED status unconditionally
	 */
	public void open() {
		this.state = StateSquare.OPENED;
	}

	/**
	 * @return the numeric value of the box
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Sets the numeric value of the box
	 * 
	 * @param value is the new value for the box
	 * @throws IllegalArgumentException if the value is not
	 *                                  {@value #SQUARE_WITH_MINE} or in the range
	 *                                  [{@value #EMPTY_SQUARE},{@value #MAXIMUM_MINES_AROUND}].
	 */

	public void setValue(int value) {
		ArgumentChecks.isTrue((value >= EMPTY_SQUARE && value <= MAXIMUM_MINES_AROUND) || value == SQUARE_WITH_MINE,
				ERROR_MESSAGE_WRONG_VALUE);
		this.value = value;
	}

	/**
	 * @return true if the box contains a mine. Otherwise, returns false.
	 */
	public boolean hasMine() {
		return this.value == SQUARE_WITH_MINE;
	}

	/**
	 * Sets the value of the box to {@value #SQUARE_WITH_MINE}.
	 */
	public void putMine() {
		setValue(SQUARE_WITH_MINE);
	}

	/**
	 * @return true if the status of the box is FLAGGED and false otherwise.
	 */
	public boolean isFlagged() {
		return this.state == StateSquare.FLAGGED;
	}

	/**
	 * @return true if the box status is OPENED and false otherwise.
	 */
	public boolean isOpened() {
		return this.state == StateSquare.OPENED;
	}

	/**
	 * 
	 * @return true if the box is EMPTY and false otherwise
	 */
	public boolean isEmpty() {
		return this.value == EMPTY_SQUARE;
	}

	/**
	 * 
	 * @return true if the state of the box is CLOSED and false otherwise
	 */
	public boolean isClosed() {
		return !isOpened() && !isFlagged();
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
		return this.getState() == square.getState() && this.getValue() == square.getValue();

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

}
