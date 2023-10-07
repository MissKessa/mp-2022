package uo.mp.minesweeper.console;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.game.GameMove;
import uo.mp.minesweeper.util.check.ArgumentChecks;
import uo.mp.minesweeper.util.console.Console;

/**
 * It's a text-mode interface for the game
 * 
 * @author Paula Díaz ÁLvarez
 * @version 2023
 *
 */
public class ConsoleGameInteractor implements GameInteractor {
	/**
	 * It's the error message shown if a negative maximum column is introduced
	 */
	public final static String ERROR_MESSAGE_NEGATIVE_MAX_COLUMN = "The maximum x-coordinate (column) of the board must be greater than or equal to 0";

	/**
	 * It's the error message shown if a negative maximum row is introduced
	 */
	public final static String ERROR_MESSAGE_NEGATIVE_MAX_ROW = "The maximum y-coordinate (row) of the board must be greater than or equal to 0";

	/**
	 * It's the error message shown if a negative upper limit is introduced
	 */
	public final static String ERROR_MESSAGE_NEGATIVE_UPPER_LIMIT = "The upper limit must be greater than or equal to 0";

	/**
	 * It's the error message shown if a null or blank message is introduced
	 */
	public final static String ERROR_MESSAGE_INVALID_MESSAGE = "The message must be not null neither blank";

	/**
	 * It's the error message shown if a negative time is introduced
	 */
	public final static String ERROR_MESSAGE_NEGATIVE_TIME = "The time must be greater than or equal to 0";

	/**
	 * It's the message shown to ask for the operation
	 */
	public final static String MESSAGE_ASK_OPERATION = "Write the operation (s|f|u)";

	/**
	 * It's the message shown to ask for the x-coordinate
	 */
	public final static String MESSAGE_ASK_X_COORDINATE = "Write the x-coordinate";

	/**
	 * It's the message shown to ask for the y-coordinate
	 */
	public final static String MESSAGE_ASK_Y_COORDINATE = "Write the y-coordinate";

	/**
	 * It's the message that shows the time
	 */
	public final static String MESSAGE_SHOW_TIME = "Time: %s seconds";

	/**
	 * It's the message that shows the flags left
	 */
	public final static String MESSAGE_SHOW_FLAGS_LEFT = "Flags left: %s";

	/**
	 * It's the message shown when the game finishes
	 */
	public final static String MESSAGE_FINISH_GAME = "The game has finished";

	/**
	 * It's the message shown when the user wins
	 */
	public final static String MESSAGE_WIN_GAME = "Congratulations, you have win in %s seconds";

	/**
	 * It's the message shown when the user loses
	 */
	public final static String MESSAGE_LOSE_GAME = "BOOOOOMMMM!!!!";

	/**
	 * It's the message shown when the game starts
	 */
	public final static String MESSAGE_START_GAME = "Ready to start!";

	@Override
	public GameMove askMove(int rows, int columns) {
		ArgumentChecks.isTrue(rows >= 0, ERROR_MESSAGE_NEGATIVE_MAX_ROW);
		ArgumentChecks.isTrue(columns >= 0, ERROR_MESSAGE_NEGATIVE_MAX_COLUMN);

		char move = askOperation(MESSAGE_ASK_OPERATION);
		int xCoordinate = askCoordinate(MESSAGE_ASK_X_COORDINATE, columns);
		int yCoordinate = askCoordinate(MESSAGE_ASK_Y_COORDINATE, rows);

		return new GameMove(move, yCoordinate, xCoordinate);
	}

	/**
	 * Asks for the a coordinate until is correct (It must be greater than or equal
	 * to 0 and less than or equal to the upperLimit)
	 * 
	 * @param message    is the message shown to the user asking for the coordinate
	 * @param upperLimit is the maximum value that the user can enter as coordinate
	 * 
	 * @return the coordinate given by the user (it's a valid value)
	 * @throws IllegalArgumentException if the message is blank or null
	 */
	private int askCoordinate(String message, int upperLimit) {
		ArgumentChecks.isTrue(!message.isBlank() && message != null, ERROR_MESSAGE_INVALID_MESSAGE);
		ArgumentChecks.isTrue(upperLimit >= 0, ERROR_MESSAGE_NEGATIVE_UPPER_LIMIT);

		int coordinate = Console.readInt(message);
		while (!(coordinate >= 0 && coordinate <= upperLimit)) {
			coordinate = Console.readInt("Wrong coordinate. " + message);
		}
		return coordinate;
	}

	/**
	 * Asks for the type of move until is correct(It must be 's', 'f' or 'u')
	 * 
	 * @param messae is the message shown to the user asking for the type of move
	 * @return the move given by the user (it's a valid char)
	 * @throws IllegalArgumentException if the message is blank or null
	 */
	private char askOperation(String message) {
		ArgumentChecks.isTrue(!message.isBlank() && message != null, ERROR_MESSAGE_INVALID_MESSAGE);

		char operation = Character.toLowerCase(Console.readChar(message));
		while (!(operation == 's' | operation == 'f' | operation == 'u')) {
			operation = Character.toLowerCase(Console.readChar("Wrong operation. " + message));
		}
		return operation;
	}

	@Override
	public void showGame(long elapsedTime, Board board) {
		ArgumentChecks.isTrue(elapsedTime >= 0, ERROR_MESSAGE_NEGATIVE_TIME);
		ArgumentChecks.isTrue(board != null, Board.ERROR_MESSAGE_NULL_BOARD);

		System.out.println(String.format(MESSAGE_SHOW_TIME, elapsedTime));
		System.out.println(String.format(MESSAGE_SHOW_FLAGS_LEFT, board.getNumberOfFlagsLeft()));
		printBoard(board);

	}

	/**
	 * Prints the state of board well formated.
	 * 
	 * @param board is a reference of the board. It must be not null
	 * @throws IllegalArgumentException if the parameter is invalid
	 */
	private void printBoard(Board board) {
		ArgumentChecks.isTrue(board != null, Board.ERROR_MESSAGE_NULL_BOARD);

		char[][] state = board.getState();
		int rows = state.length;
		int columns = state[0].length;

		printColumnNumbers(columns);
		printRowDivision(rows);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (j == 0)
					System.out.print(String.format("%s ", i));

				System.out.print(String.format("|   %s   ", state[i][j]));
			}
			System.out.println("|");
			printRowDivision(rows);
		}
	}

	/**
	 * Prints the header of the board with the column numbers
	 * 
	 * @param columns is the total number of columns of the board. It must be
	 *                greater than or equal to 0
	 * @throws IllegalArgumentException if the parameter is invalid
	 */
	private void printColumnNumbers(int columns) {
		ArgumentChecks.isTrue(columns >= 0, ERROR_MESSAGE_NEGATIVE_MAX_COLUMN);
		System.out.print("  ");
		for (int i = 0; i < columns; i++)
			System.out.print(String.format("    %s   ", i));
		System.out.println();
	}

	/**
	 * Prints the division between rows of the board
	 * 
	 * @param rows is the total number of rows of the board. It must be greater than
	 *             or equal to 0
	 * @throws IllegalArgumentException if the parameter is invalid
	 */
	private void printRowDivision(int rows) {
		ArgumentChecks.isTrue(rows >= 0, ERROR_MESSAGE_NEGATIVE_MAX_ROW);
		System.out.print("  ");
		for (int i = 0; i < rows; i++)
			System.out.print("+ - - - ");
		System.out.println("+");
	}

	@Override
	public void showGameFinished() {
		System.out.println(MESSAGE_FINISH_GAME);

	}

	@Override
	public void showCongratulations(long elapsedTime) {
		ArgumentChecks.isTrue(elapsedTime >= 0, ERROR_MESSAGE_NEGATIVE_TIME);
		System.out.println(String.format(MESSAGE_WIN_GAME, elapsedTime));

	}

	@Override
	public void showBooommm() {
		System.out.println(MESSAGE_LOSE_GAME);

	}

	@Override
	public void showReadyToStart() {
		System.out.println(MESSAGE_START_GAME);

	}

}
