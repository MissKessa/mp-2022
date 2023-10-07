package uo.mp.minesweeper.game;

import java.util.Random;

import uo.mp.minesweeper.game.square.Square;
import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * This class represents the minesweeper board and stores the current state of
 * the game. Internally, it contains a reference to a two-dimensional array of
 * Square objects.
 * 
 * @author Paula Díaz Álvarez
 * @version 2023
 */
public class Board {
	/**
	 * It's the error message shown if an invalid width is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_WIDTH = "The width of the board must be greater than 0";

	/**
	 * It's the error message shown if an invalid height is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_HEIGHT = "The height of the board must be greater than 0";

	/**
	 * It's the error message shown if an invalid percentage of mines is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_PERCENTAGE = "The percentage of mines must be greater than or equal to 0 and less than or equal to 100";

	/**
	 * It's the error message shown if an invalid x-coordinate is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_X_COORDINATE = "The x-coordinate of the board must be greater than or equal to 0 and less than the width of the board";

	/**
	 * It's the error message shown if an invalid y-coordinate is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_Y_COORDINATE = "The y-coordinate of the board must be greater than or equal to 0 and less than the height of the board";

	/**
	 * It's the error message shown if an invalid number of mines is introduced
	 */
	public final static String ERROR_MESSAGE_WRONG_NUMBER_OF_MINES = "The number of mines must be greater than or equal to 0 and less than the number of squares in the board";

	/**
	 * It's the error message shown if a null board is introduced
	 */
	public final static String ERROR_MESSAGE_NULL_BOARD = "The board cannot be null";

	/**
	 * It's the error message shown if a not initialized board (has some null
	 * elements) is introduced
	 */
	public final static String ERROR_MESSAGE_NOT_INITIALIZED_BOARD = "The board hasn't been initialized (it has some null squares)";
	/**
	 * It's the error message shown if a non rectangular board is introduced
	 */
	public final static String ERROR_MESSAGE_NOT_RECTANGULAR_BOARD = "The board is not rectangular";
	/**
	 * It's the error message shown if a the board passed doesn't have the
	 * numberOfMines passed
	 */
	public final static String ERROR_MESSAGE_BOARD_DOESNT_HAVE_THE_NUMBRE_OF_MINES = "The board must have the number of mines indicated";

	private Square[][] squares;
	private int numberOfMines;
	private boolean exploded;

	/**
	 * Construct a board of width x height positions, on which the percentage of the
	 * squares containing a mine is marked by percentage. The number of mines will
	 * be rounded up from the percentage. Then the constructor will add the mines
	 * randomly and put a value between -1 and 8 on every square.
	 * 
	 * @param width      is the width of the board. It must be a non negative and
	 *                   non zero integer.
	 * @param height     is the height of the board. It must be a non negative and
	 *                   non zero integer.
	 * @param percentage is the percentage of mines in the board. It a non-negative
	 *                   and non zero integer. It must be greater than or equal to 0
	 *                   and less than or equal to 100
	 * @throws IllegalArgumentException if one of the parameters is not valid
	 */
	public Board(int width, int height, int percentage) {
		ArgumentChecks.isTrue(width > 0, ERROR_MESSAGE_WRONG_WIDTH);
		ArgumentChecks.isTrue(height > 0, ERROR_MESSAGE_WRONG_HEIGHT);
		ArgumentChecks.isTrue(percentage >= 0 && percentage <= 100, ERROR_MESSAGE_WRONG_PERCENTAGE);

		this.squares = new Square[height][width];
		this.exploded = false;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.squares[i][j] = new Square();
			}
		}
		calculateNumberOfMines(width, height, percentage);
		putMines();
		putValues();
	}

	/**
	 * This constructor will be used for test cases only. Mines marks the number of
	 * mines, and squares maps directly to the internal reference to the array of
	 * squares.
	 * 
	 * @param mines   is the number of mines on the board. It's a non-negative value
	 *                less than or equal to the number of squares in the board. The
	 *                mines must be numberOfMines of the squares
	 * @param squares is the board at a given moment. It must be rectangular matrix
	 *                with non null squares, and not null.
	 * @throws IllegalArgumentException if one of the parameters is not valid
	 */
	protected Board(int mines, Square[][] squares) {
		ArgumentChecks.isTrue(squares != null, ERROR_MESSAGE_NULL_BOARD);
		ArgumentChecks.isTrue(mines >= 0 && mines <= squares[0].length * squares.length,
				ERROR_MESSAGE_WRONG_NUMBER_OF_MINES);

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				ArgumentChecks.isTrue(squares[i][j] != null, ERROR_MESSAGE_NOT_INITIALIZED_BOARD);
				ArgumentChecks.isTrue(squares[0].length == squares[i].length, ERROR_MESSAGE_NOT_RECTANGULAR_BOARD);
			}
		}
		this.squares = squares;
		setNumberOfMines(mines);
	}

	/**
	 * Calculates the number of mines needed to put in the board from the percentage
	 * of mines, the width and the height
	 * 
	 * @param width      is the width of the board. It must be a non negative and
	 *                   non zero integer.
	 * @param height     is the height of the board. It must be a non negative and
	 *                   non zero integer.
	 * @param percentage is the percentage of mines in the board. It a non-negative
	 *                   and non zero double. It must be greater than or equal to 0
	 *                   and less than or equal to 100
	 * @throws IllegalArgumentException if one of the parameters is not valid
	 */
	private void calculateNumberOfMines(int width, int height, int percentage) {
		ArgumentChecks.isTrue(width > 0, ERROR_MESSAGE_WRONG_WIDTH);
		ArgumentChecks.isTrue(height > 0, ERROR_MESSAGE_WRONG_HEIGHT);
		ArgumentChecks.isTrue(percentage >= 0 && percentage <= 100, ERROR_MESSAGE_WRONG_PERCENTAGE);

		this.numberOfMines = (int) Math.ceil(width * height * percentage / 100);
	}

	/**
	 * @return the numberOfMines of the board
	 */
	public int getNumberOfMines() {
		return numberOfMines;
	}

	/**
	 * Sets the field numberOfMines as the given number of mines
	 * 
	 * @param numberOfMines is the given number of mines. It must be non-negative
	 *                      and less than or equal to the number of squares in the
	 *                      board. The numberOfMines must be equal to the real
	 *                      number of mines in the board
	 * @throws IllegalArgumentException if one of the parameters is not valid
	 */
	private void setNumberOfMines(int numberOfMines) {
		ArgumentChecks.isTrue(numberOfMines >= 0 && numberOfMines <= squares[0].length * squares.length,
				ERROR_MESSAGE_WRONG_NUMBER_OF_MINES);

		int mines = 0;

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				if (squares[i][j].hasMine())
					mines++;
			}
		}
		ArgumentChecks.isTrue(numberOfMines == mines, ERROR_MESSAGE_BOARD_DOESNT_HAVE_THE_NUMBRE_OF_MINES);

		this.numberOfMines = numberOfMines;
	}

	/**
	 * Puts randomly the mines on the board according to the numberOfMines field
	 */
	private void putMines() {
		int width = this.squares[0].length;
		int height = this.squares.length;

		int minesLeft = this.numberOfMines;
		int emptySquares = width * height;
		Random generator = new Random();

		for (int i = 0; i < height && minesLeft > 0; i++) {
			for (int j = 0; j < width && minesLeft > 0; j++) {

				if (generator.nextInt(emptySquares) < minesLeft) {
					this.squares[i][j].putMine();
					--minesLeft;
				}
				--emptySquares;
			}
		}
	}

	/**
	 * Checks if the x coordinate and the y coordinate are correct. If not an
	 * IllegalArgumentException is thrown
	 * 
	 * @param x is the x-coordinate of the square in the board [0,width)
	 * @param y is the y-coordinate of the square in the board [0, height)
	 * @throws IllegalArgumentExpression if any of the parameters is wrong
	 */
	private void checkCoordinates(int x, int y) {
		ArgumentChecks.isTrue(x >= 0 && x < squares[0].length, ERROR_MESSAGE_WRONG_X_COORDINATE);
		ArgumentChecks.isTrue(y >= 0 && y < squares.length, ERROR_MESSAGE_WRONG_Y_COORDINATE);
	}

	/**
	 * Assigns the numbers of the squares in the squares near the mines
	 */
	private void putValues() {
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				if (this.squares[i][j].hasMine()) // if it's a mine skip to the next
					continue;

				int numberOfMinesAround = 0;

				for (int k = i - 1; k <= i + 1; k++) {
					if (k < 0)
						continue; // if the row is out of the board from above, we skip to the next row

					else if (k >= this.squares.length)
						break; // if the row is out of the board from below, it ends because there aren't
								// more rows

					for (int l = j - 1; l <= j + 1; l++) {
						if (l < 0 || (k == i && l == j))
							continue; // if the column is out of the board from the left or we are in the element
										// [i][j], we skip to the next column
						if (l >= this.squares[i].length)
							break; // if the column is out of the board from the right, ends because there
									// aren't more columns

						if (this.squares[k][l].hasMine())
							numberOfMinesAround++;
					}
				}
				this.squares[i][j].setValue(numberOfMinesAround);
			}
		}
	}

	/**
	 * Returns the value of the exploded field
	 * 
	 * @return true if a mine has exploded on the board, false otherwise
	 */
	public boolean hasExploded() {
		return this.exploded;
	}

	/**
	 * If it is not uncovered, uncover the (x, y) coordinate box
	 * 
	 * @param x is the x-coordinate of the square in the board
	 * @param y is the y-coordinate of the square in the board
	 */
	public void stepOn(int x, int y) {
		checkCoordinates(x, y);
		this.squares[y][x].stepOn();
	}

	/**
	 * If there is no flag already and the box is not uncovered, a flag is placed in
	 * the (x, y) coordinate box. If there are no more flags left, nothing happens
	 * 
	 * @param x is the x-coordinate of the square in the board
	 * @param y is the y-coordinate of the square in the board
	 * @throws IllegalArgumentExpression if any of the parameters is wrong
	 */
	public void flag(int x, int y) {
		checkCoordinates(x, y);
		if (this.getNumberOfFlagsLeft() != 0)
			this.squares[y][x].flag();
	}

	/**
	 * If there is a flag, remove a flag in the (x, y) coordinate box.
	 * 
	 * @param x is the x-coordinate of the square in the board
	 * @param y is the y-coordinate of the square in the board
	 * @throws IllegalArgumentExpression if any of the parameters is wrong
	 */
	public void unflag(int x, int y) {
		checkCoordinates(x, y);
		this.squares[y][x].unflag();
	}

	/**
	 * Discover all the squares on the board independently of the states of the
	 * squares
	 */
	public void unveil() {
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				this.squares[i][j].open();
			}
		}
	}

	/**
	 * Returns the number of flags that have not yet been set. The initial number of
	 * flags is equal to the numberOfMines
	 * 
	 * @return the number of flags left
	 */
	public int getNumberOfFlagsLeft() {
		int numberOfFlagsUsed = 0;
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				if (this.squares[i][j].isFlagged())
					numberOfFlagsUsed++;

				if (numberOfFlagsUsed == numberOfMines)
					return 0;
			}
		}
		return this.numberOfMines - numberOfFlagsUsed;
	}

	/**
	 * @return the number of mines that have not yet been covered with a flag.
	 */
	public int getNumberOfMinesLeft() {
		int numberOfMinesLeft = numberOfMines;
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				if (this.squares[i][j].hasMine() && this.squares[i][j].isFlagged())
					numberOfMinesLeft--;

				if (numberOfMinesLeft == 0)
					return 0;
			}
		}
		return numberOfMinesLeft;
	}

	/**
	 * It places the internal state of the board as exploded(because some mine has
	 * been discovered). If a mine has not been discovered, the state doesn't change
	 */
	public void markAsExploded() {
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				if (this.squares[i][j].hasMine() && this.squares[i][j].isOpened()) {
					this.exploded = true;
					break;
				}
			}
		}
	}

	/**
	 * @return an array of characters representing the state of the game board. The
	 *         (x, y) position character shall represent the state of the (x, y)
	 *         position of the board.
	 */
	public char[][] getState() {
		char[][] state = new char[this.squares.length][this.squares[0].length];
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				state[i][j] = this.squares[i][j].getState();
			}
		}
		return state;

	}

	/**
	 * Prints the state of board. Each row begins with "[" and end with "]". Each
	 * square is printed with the character that represent it, and separated by
	 * commas
	 */
	public void printBoard() {
		char[][] state = this.getState();
		for (int i = 0; i < state.length; i++) {
			System.out.print("[");
			for (int j = 0; j < state[i].length; j++) {
				System.out.print(state[i][j]);

				if (j < state[i].length - 1)
					System.out.print(",");
			}
			System.out.println("]");
		}
	}

	/**
	 * @return a copy of the internally managed Square array.
	 */
	Square[][] getSquares() {
		Square[][] copy = new Square[this.squares.length][this.squares[0].length];
		for (int i = 0; i < this.squares.length; i++) {
			for (int j = 0; j < this.squares[i].length; j++) {
				copy[i][j] = this.squares[i][j];
			}
		}
		return copy;
	}

	/**
	 * 
	 * @return the number of rows of the board
	 */
	public int getNumberOfRows() {
		return this.squares.length;
	}

	/**
	 * 
	 * @return the number of columns of the board
	 */
	public int getNumberOfColumns() {
		return this.squares[0].length;
	}
}
