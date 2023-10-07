package uo.mp.minesweeper.game;

import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * It consists of a simple data container to collect a complete user action:
 * operation, row and column
 * 
 * @author Paula Díaz Álvarez
 * @version 2023
 *
 */
public class GameMove {

	/**
	 * It's the format for the toString method
	 */
	private static final String TO_STRING_FORMAT = "GameMove [operation=%s, row=%s, column=%s]";

	/**
	 * It's the minimum coordinate for a row or column independently of the board
	 */
	public final static int MINIMUM_COORDINATE = 0;

	/**
	 * It's the maximum coordinate for a row independently of the board
	 */
	public final static int MAXIMUM_ROW_COORDINATE = Board.MAXIMUM_HEIGHT + GameMove.MINIMUM_COORDINATE - 1;

	/**
	 * It's the maximum coordinate for a column independently of the board
	 */
	public final static int MAXIMUM_COLUMN_COORDINATE = Board.MAXIMUM_WIDTH + GameMove.MINIMUM_COORDINATE - 1;

	/**
	 * It's the character representing the operation step on
	 */
	public final static char STEP_ON_CHARACTER = 's';

	/**
	 * It's the character representing the operation flag
	 */
	public final static char FLAG_CHARACTER = 'f';

	/**
	 * It's the character representing the operation unflag
	 */
	public final static char UNFLAG_CHARACTER = 'u';

	/**
	 * It's the error message shown if a wrong type of move is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_OPERATION = String.format("The operation must be %s, %s or %s",
			STEP_ON_CHARACTER, FLAG_CHARACTER, UNFLAG_CHARACTER);

	/**
	 * It's the error message shown if a invalid x-coordinate is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_X_COORDINATE = String.format(
			"The x-coordinate (column) of the board must in [%s,%s]", MINIMUM_COORDINATE, MAXIMUM_COLUMN_COORDINATE);

	/**
	 * It's the error message shown if a invalid y-coordinate is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_Y_COORDINATE = String.format(
			"The y-coordinate (column) of the board must in [%s,%s]", MINIMUM_COORDINATE, MAXIMUM_ROW_COORDINATE);

	private char operation;
	private int row;
	private int column;

	/**
	 * It receives a character ('s', 'f', or 'u') representing the operation
	 * performed, and two integers row and column indicating the coordinates of the
	 * box where the action will be executed.
	 * 
	 * @param operation is the character representing the operation. It must be
	 *                  STEP_ON_CHARACTER, FLAG_CHARACTER, or UNFLAG_CHARACTER.
	 * 
	 * @param row       is the y-coordinate of the box where the action will be
	 *                  executed. It must between its minimum and maximum
	 * 
	 * @param column    is the x-coordinate of the box where the action will be
	 *                  executed. It must between its minimum and maximum
	 * 
	 * @throws IllegalArgumentException if any of the parameters is invalid
	 */
	public GameMove(char operation, int row, int column) {
		operation = Character.toLowerCase(operation);
		ArgumentChecks.isTrue(
				operation == STEP_ON_CHARACTER || operation == FLAG_CHARACTER || operation == UNFLAG_CHARACTER,
				ERROR_MESSAGE_WRONG_OPERATION);
		ArgumentChecks.isTrue(column >= MINIMUM_COORDINATE && column <= MAXIMUM_COLUMN_COORDINATE,
				ERROR_MESSAGE_WRONG_X_COORDINATE);
		ArgumentChecks.isTrue(row >= MINIMUM_COORDINATE && row <= MAXIMUM_ROW_COORDINATE,
				ERROR_MESSAGE_WRONG_Y_COORDINATE);

		this.operation = operation;
		this.row = row;
		this.column = column;
	}

	/**
	 * @return the character representing the user operation
	 */
	public char getOperation() {
		return this.operation;
	}

	/**
	 * 
	 * @return the row coordinate.
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * 
	 * @return the column coordinate.
	 */
	public int getColumn() {
		return this.column;
	}

	/**
	 * @return a textual representation of the complete command in the following
	 *         format: "GameMove [operation=%s, row=%s, column=%s]"
	 */
	@Override
	public String toString() {
		return String.format(TO_STRING_FORMAT, getOperation(), getRow(), getColumn());
	}
}
