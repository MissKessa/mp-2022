package uo.mp.minesweeper.console;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.game.GameMove;
import uo.mp.minesweeper.util.check.ArgumentChecks;
import uo.mp.minesweeper.util.console.Console;
import uo.mp.minesweeper.util.exceptions.UserInteractionException;

/**
 * It's a text-mode interface for the game
 * 
 * @author Paula Díaz ÁLvarez
 * @version 2023
 *
 */
public class ConsoleGameInteractor implements GameInteractor {
	/**
	 * It's the error message shown if the coordinate requested is out of bounds
	 */
	private static final String ERROR_MESSAGE_WRONG_COORDINATE = "The coordinate must be in [%s,%s]";

	/**
	 * It's the error message shown if the operation requested is not a character
	 */
	private static final String ERROR_MESSAGE_THE_OPERATION_MUST_BE_A_CHARACTER = "The operation must be a character";

	/**
	 * It's the error message shown if the coordinate requested is not a number
	 */
	public static final String ERROR_MESSAGE_THE_COORDINATE_MUST_BE_A_NUMBER = "The coordinate must be a number";

	/**
	 * It's the error message shown if the maximum column is less than the minimum
	 * coordinate or greater than the maximum
	 */
	public final static String ERROR_MESSAGE_INVALID_MAX_COLUMN = String.format(
			"The maximum x-coordinate (column) of the board must be in [%s,%s]", GameMove.MINIMUM_COORDINATE,
			GameMove.MAXIMUM_COLUMN_COORDINATE);

	/**
	 * It's the error message shown if the maximum row is less is less than the
	 * minimum coordinate or greater than the maximum
	 */
	public final static String ERROR_MESSAGE_INVALID_MAX_ROW = String.format(
			"The maximum y-coordinate (row) of the board must be in [%s,%s]", GameMove.MINIMUM_COORDINATE,
			GameMove.MAXIMUM_ROW_COORDINATE);

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

	/**
	 * It's the minimum time for the time
	 */
	public final static int MINIMUM_TIME = 0;

	@Override
	public GameMove askMove(int rows, int columns) throws UserInteractionException {
		ArgumentChecks.isTrue(rows >= GameMove.MINIMUM_COORDINATE && rows <= GameMove.MAXIMUM_ROW_COORDINATE,
				ERROR_MESSAGE_INVALID_MAX_ROW);

		ArgumentChecks.isTrue(columns >= GameMove.MINIMUM_COORDINATE && columns <= GameMove.MAXIMUM_COLUMN_COORDINATE,
				ERROR_MESSAGE_INVALID_MAX_COLUMN);

		char move;
		int xCoordinate;
		int yCoordinate;

		move = askOperation(MESSAGE_ASK_OPERATION);
		xCoordinate = askCoordinate(MESSAGE_ASK_X_COORDINATE, columns);
		yCoordinate = askCoordinate(MESSAGE_ASK_Y_COORDINATE, rows);
		return new GameMove(move, yCoordinate, xCoordinate);
	}

	/**
	 * Asks for the a coordinate (It must be greater than or equal to
	 * MINIMUM_COORDINATE and less than or equal to the upperLimit)
	 * 
	 * @param message    is the message shown to the user asking for the coordinate
	 * @param upperLimit is the maximum value that the user can enter as coordinate
	 * 
	 * @return the coordinate given by the user (it's a valid value)
	 * @throws UserInteractionException if the coordinate is not correct
	 * @throws IllegalArgumentException if the message is blank or null
	 */
	private int askCoordinate(String message, int upperLimit) throws UserInteractionException {
		ArgumentChecks.isTrue(!message.isBlank() && message != null, ERROR_MESSAGE_INVALID_MESSAGE);
		ArgumentChecks.isTrue(upperLimit >= GameMove.MINIMUM_COORDINATE, ERROR_MESSAGE_NEGATIVE_UPPER_LIMIT);

		int coordinate;
		try {
			coordinate = Console.readInt(message);
		} catch (NumberFormatException exception) {
			throw new UserInteractionException(ERROR_MESSAGE_THE_COORDINATE_MUST_BE_A_NUMBER);
		}
		if (!(coordinate >= GameMove.MINIMUM_COORDINATE && coordinate <= upperLimit)) {
			throw new UserInteractionException(
					String.format(ERROR_MESSAGE_WRONG_COORDINATE, GameMove.MINIMUM_COORDINATE, upperLimit));
		}
		return coordinate;
	}

	/**
	 * Asks for the type of move until is correct (It must be 's', 'f' or 'u')
	 * 
	 * @param messae is the message shown to the user asking for the type of move
	 * @return the move given by the user (it's a valid char)
	 * @throws UserInteractionException if the operation is not correct
	 * @throws IllegalArgumentException if the message is blank or null
	 */
	private char askOperation(String message) throws UserInteractionException {
		ArgumentChecks.isTrue(!message.isBlank() && message != null, ERROR_MESSAGE_INVALID_MESSAGE);
		char operation;
		try {
			operation = Character.toLowerCase(Console.readChar(message));
		} catch (ClassCastException exception) {
			throw new UserInteractionException(ERROR_MESSAGE_THE_OPERATION_MUST_BE_A_CHARACTER);
		}

		if (!(operation == GameMove.STEP_ON_CHARACTER || operation == GameMove.FLAG_CHARACTER
				|| operation == GameMove.UNFLAG_CHARACTER)) {
			throw new UserInteractionException(GameMove.ERROR_MESSAGE_WRONG_OPERATION);
		}
		return operation;
	}

	@Override
	public void showGame(long elapsedTime, Board board) {
		ArgumentChecks.isTrue(elapsedTime >= MINIMUM_TIME, ERROR_MESSAGE_NEGATIVE_TIME);
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
				if (j == 0) {
					if (i < 10)
						System.out.print(String.format("%s  ", i));
					else
						System.out.print(String.format("%s ", i));
				}

				System.out.print(String.format("|   %s   ", state[i][j]));
			}
			System.out.println("|");
			printRowDivision(rows);
		}
	}

	/**
	 * Prints the header of the board with the column numbers
	 * 
	 * @param columns is the total number of columns of the board. It must be in
	 *                [GameMove.MINIMUM_COORDINATE,
	 *                GameMove.MAXIMUM_COLUMN_COORDINATE]
	 * @throws IllegalArgumentException if the parameter is invalid
	 */
	private void printColumnNumbers(int columns) {
		ArgumentChecks.isTrue(
				columns >= GameMove.MINIMUM_COORDINATE && columns <= GameMove.MAXIMUM_COLUMN_COORDINATE + 1,
				ERROR_MESSAGE_INVALID_MAX_COLUMN);
		System.out.print("   ");
		for (int i = 0; i < columns; i++) {
			if (i < 10) {
				System.out.print(String.format("    %s   ", i));
			} else {
				System.out.print(String.format("   %s   ", i));
			}
		}
		System.out.println();
	}

	/**
	 * Prints the division between rows of the board
	 * 
	 * @param rows is the total number of rows of the board. It must be in
	 *             [GameMove.MINIMUM_COORDINATE, GameMove.MAXIMUM_ROW_COORDINATE]
	 * @throws IllegalArgumentException if the parameter is invalid
	 */
	private void printRowDivision(int rows) {
		ArgumentChecks.isTrue(rows >= GameMove.MINIMUM_COORDINATE && rows <= GameMove.MAXIMUM_ROW_COORDINATE,
				ERROR_MESSAGE_INVALID_MAX_ROW);
		System.out.print("   ");
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
		ArgumentChecks.isTrue(elapsedTime >= MINIMUM_TIME, ERROR_MESSAGE_NEGATIVE_TIME);
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
