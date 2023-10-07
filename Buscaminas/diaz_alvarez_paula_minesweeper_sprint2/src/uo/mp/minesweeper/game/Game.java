package uo.mp.minesweeper.game;

import uo.mp.minesweeper.util.check.ArgumentChecks;
import uo.mp.minesweeper.util.console.Console;

/**
 * It manages the main logic of the game. It is the class in charge of executing
 * the loop of iterations of the game. Only from this class it is possible to
 * interact with the user.
 * 
 * @author Paula Díaz Álvarez
 * @version 2023
 *
 */
public class Game {
	/**
	 * It's the error message shown if null board is introduced
	 */
	public final static String ERROR_MESSAGE_NULL_BOARD = "The board cannot be null";
	/**
	 * It's the error message shown if a wrong move is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_MOVE = "The move must be s, u or f";

	private Board board;

	/**
	 * It's the main constructor. It creates a game with the given board
	 * 
	 * @param board is the given board. It must be non-null
	 * @throws IllegalArgumentException is the parameter is wrong
	 */
	public Game(Board board) {
		ArgumentChecks.isTrue(board != null, ERROR_MESSAGE_NULL_BOARD);
		this.board = board;

	}

	/**
	 * Initialises the game (messages to the user, timer, etc.). It then executes a
	 * loop in which the user is asked for a move and the corresponding square is
	 * discovered/(un)marked. When it is detected that the user has lost (has
	 * stepped on a mine) or the board is in a winning situation (there are no more
	 * mines to find and mark), the game stops, the board is completely uncovered,
	 * and the user is informed of his victory/defeat.
	 */
	public void play() {
		System.out.println("Ready to start!");
		long time = System.currentTimeMillis() / 1000;
		long end = 0;

		while (!this.board.hasWin() && !this.board.hasExploded()) {
			end = System.currentTimeMillis() / 1000;
			System.out.println("Time: " + (end - time) + " seconds");
			System.out.println("Flags left: " + this.board.getNumberOfFlagsLeft());
			this.board.printBoard();

			String selectedOption = askMove();
			int selectedXCoordinate = askXCoordinate();
			int selectedYCoordinate = askYCoordinate();
			executeMove(selectedOption, selectedXCoordinate, selectedYCoordinate);
		}

		if (this.board.hasExploded()) {
			System.out.println("BOOOOMM!!!");
		} else
			System.out.println("You win!!! in " + (end - time) + " seconds");

		this.board.unveil();
		this.board.printBoard();
		System.out.println("The game has finished");

	}

	/**
	 * Executes the given move of the user in the square with that x-coordinate and
	 * y-coordinate
	 * 
	 * @param move        is the move to execute. It must be "s", "f" or "u"
	 * @param xCoordinate is the x-coordinate of the square. It must be greater than
	 *                    or equal to 0 and less than the row length
	 * @param yCoordinate is the y-coordinate of the square. It must be greater than
	 *                    or equal to 0 and less than the column length
	 * @throws IllegalArgumentExpression if one of the parameters is wrong
	 * 
	 */
	public void executeMove(String move, int xCoordinate, int yCoordinate) {
		int maximumRow = this.board.getNumberOfRows() - 1;
		int maximumColumn = this.board.getNumberOfColumns() - 1;
		ArgumentChecks.isTrue(move.equals("s") | move.equals("f") | move.equals("u"), ERROR_MESSAGE_WRONG_MOVE);
		ArgumentChecks.isTrue(xCoordinate >= 0 && xCoordinate <= maximumColumn, Board.ERROR_MESSAGE_WRONG_X_COORDINATE);
		ArgumentChecks.isTrue(yCoordinate >= 0 && yCoordinate <= maximumRow, Board.ERROR_MESSAGE_WRONG_Y_COORDINATE);

		if (move.equals("s"))
			this.board.stepOn(xCoordinate, yCoordinate);
		else if (move.equals("f"))
			this.board.flag(xCoordinate, yCoordinate);
		else
			this.board.unflag(xCoordinate, yCoordinate);
	}

	/**
	 * Asks for the x-coordinate until is correct (It must be greater than or equal
	 * to 0 and less than the row length)
	 * 
	 * @return the x-coordinate given by the user (it's a valid value)
	 */
	private int askXCoordinate() {
		int maximumColumn = this.board.getNumberOfColumns() - 1;
		int xCoordinate = Console.readInt("Write the x-coordinate");
		while (!(xCoordinate >= 0 && xCoordinate <= maximumColumn)) {
			xCoordinate = Console.readInt("Wrong x-coordinate. Write a valid x-coordinate");
		}
		return xCoordinate;
	}

	/**
	 * Asks for the y-coordinate until is correct (It must be greater than or equal
	 * to 0 and less than the column length)
	 * 
	 * @return the y-coordinate given by the user (it's a valid value)
	 */
	private int askYCoordinate() {
		int maximumRow = this.board.getNumberOfRows() - 1;
		int yCoordinate = Console.readInt("Write the y-coordinate");
		while (!(yCoordinate >= 0 && yCoordinate <= maximumRow)) {
			yCoordinate = Console.readInt("Wrong y-coordinate. Write a valid y-coordinate");
		}
		return yCoordinate;
	}

	/**
	 * Asks for the move until is correct(It must be "s", "f" or "u")
	 * 
	 * @return the move given by the user (it's a valid string)
	 */
	private String askMove() {
		String move = Console.readString("Write the move (s|f|u)").toLowerCase();
		while (!(move.equals("s") | move.equals("f") | move.equals("u"))) {
			move = Console.readString("Wrong move. Write a valid move").toLowerCase();
		}
		return move;
	}
}
