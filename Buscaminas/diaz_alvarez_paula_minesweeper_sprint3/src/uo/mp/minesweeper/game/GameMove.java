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
	 * It's the error message shown if a wrong type of move is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_OPERATION = "The operation must be s, u or f";

	/**
	 * It's the error message shown if a negative x-coordinate is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_X_COORDINATE = "The x-coordinate (column) of the board must be greater than or equal to 0";

	/**
	 * It's the error message shown if a negative y-coordinate is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_Y_COORDINATE = "The y-coordinate (row) of the board must be greater than or equal to 0";

	private char operation;
	private int row;
	private int column;

	/**
	 * It receives a character ('s', 'f', or 'u') representing the operation
	 * performed, and two integers row and column indicating the coordinates of the
	 * box where the action will be executed.
	 * 
	 * @param operation is the character representing the operation. It must be 's',
	 *                  'f', or 'u'.
	 * @param row       is the y-coordinate of the box where the action will be
	 *                  executed. It must be non negative
	 * @param column    is the x-coordinate of the box where the action will be
	 *                  executed. It must be non negative
	 * @throws IllegalArgumentException if any of the parameters is invalid
	 */
	public GameMove(char operation, int row, int column) {
		ArgumentChecks.isTrue(operation == 's' || operation == 'f' || operation == 'u', ERROR_MESSAGE_WRONG_OPERATION);
		ArgumentChecks.isTrue(column >= 0, ERROR_MESSAGE_WRONG_X_COORDINATE);
		ArgumentChecks.isTrue(row >= 0, ERROR_MESSAGE_WRONG_Y_COORDINATE);

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
		return String.format("GameMove [operation=%s, row=%s, column=%s]", getOperation(), getRow(), getColumn());
	}
}
