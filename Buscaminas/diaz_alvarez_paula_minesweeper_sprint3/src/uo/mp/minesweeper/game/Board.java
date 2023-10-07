package uo.mp.minesweeper.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uo.mp.minesweeper.game.square.Square;
import uo.mp.minesweeper.game.square.actions.BlowUpAction;
import uo.mp.minesweeper.game.square.actions.ClearAction;
import uo.mp.minesweeper.game.square.actions.NullAction;
import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * This class represents the minesweeper board and stores the current state of
 * the game. Internally, it contains a reference to a two-dimensional array of
 * Square objects. It also contains the number of mines that the board has and
 * if the board has exploded or not.
 * 
 * @author Paula Díaz Álvarez
 * @version 2023
 */
public class Board {
	/**
	 * It's the error message shown if the width introduced is less than or equal to
	 * 0
	 */
	public final static String ERROR_MESSAGE_WRONG_WIDTH = "The width of the board must be greater than 0";

	/**
	 * It's the error message shown if the height introduced is less than or equal
	 * to 0
	 */
	public final static String ERROR_MESSAGE_WRONG_HEIGHT = "The height of the board must be greater than 0";

	/**
	 * It's the error message shown if the percentage of mines introduced is
	 * negative
	 */
	public final static String ERROR_MESSAGE_WRONG_PERCENTAGE = "The percentage of mines must be greater than or equal to 0 and less than or equal to 100";

	/**
	 * It's the error message shown if the x-coordinate introduced is negative or
	 * greater than or equal to the width of the board
	 */
	public final static String ERROR_MESSAGE_WRONG_X_COORDINATE = "The x-coordinate of the board must be greater than or equal to 0 and less than the width of the board";

	/**
	 * It's the error message shown if the y-coordinate introduced is negative or
	 * greater than or equal to the height of the board
	 */

	public final static String ERROR_MESSAGE_WRONG_Y_COORDINATE = "The y-coordinate of the board must be greater than or equal to 0 and less than the height of the board";

	/**
	 * It's the error message shown if the number of mines introduced is negative or
	 * greater than the number of squares in the board
	 */
	public final static String ERROR_MESSAGE_WRONG_NUMBER_OF_MINES = "The number of mines must be greater than or equal to 0 and less than or equal to the number of squares in the board";

	/**
	 * It's the error message shown if a null board is introduced
	 */
	public final static String ERROR_MESSAGE_NULL_BOARD = "The board cannot be null";

	/**
	 * It's the error message shown if a not initialized board (has some null
	 * elements) is introduced
	 */
	public final static String ERROR_MESSAGE_NOT_INITIALIZED_BOARD = "The board hasn't been initialized correctly (it has some null squares)";

	/**
	 * It's the error message shown if a non rectangular board is introduced (the
	 * width of each row varies or the height of each column varies)
	 */
	public final static String ERROR_MESSAGE_NOT_RECTANGULAR_BOARD = "The board is not rectangular";

	/**
	 * It's the error message shown if the board passed doesn't have the
	 * numberOfMines passed
	 */
	public final static String ERROR_MESSAGE_BOARD_DOESNT_HAVE_THE_NUMBRE_OF_MINES = "The board must have the number of mines indicated";

	/**
	 * It's the error message shown if the square passed is null
	 */
	public final static String ERROR_MESSAGE_NULL_SQUARE = "The square must be not null";

	private Square[][] squares;
	private int numberOfMines;
	private boolean exploded;

	/**
	 * Construct a board of width x height positions, on which the percentage of the
	 * squares containing a mine is marked by percentage. The number of mines will
	 * be rounded up from the percentage. Then the constructor will add the mines
	 * randomly and put a value between -1 and 8 on every square. After setting all
	 * the board, it sets all the actions of the squares
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
		setAllActions();
	}

	/**
	 * This constructor will be used for test cases only. Mines marks the number of
	 * mines, and squares maps directly to the internal reference to the array of
	 * squares. After setting all the board, it sets all the actions if the squares
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
		setAllActions();

	}

	/**
	 * Checks if the x-coordinate and the y-coordinate are correct. If not an
	 * IllegalArgumentException is thrown
	 * 
	 * @param x is the x-coordinate of the square in the board. It must be in
	 *          [0,width)
	 * @param y is the y-coordinate of the square in the board. It must be in [0,
	 *          height)
	 * @throws IllegalArgumentExpression if any of the parameters is wrong
	 */
	private void checkCoordinates(int x, int y) {
		ArgumentChecks.isTrue(x >= 0 && x < getNumberOfColumns(), ERROR_MESSAGE_WRONG_X_COORDINATE);
		ArgumentChecks.isTrue(y >= 0 && y < getNumberOfRows(), ERROR_MESSAGE_WRONG_Y_COORDINATE);
	}

	/**
	 * Calculates the number of mines needed to put in the board from the percentage
	 * of mines, the width and the height of the board
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
	private void calculateNumberOfMines(int width, int height, int percentage) {
		ArgumentChecks.isTrue(width > 0, ERROR_MESSAGE_WRONG_WIDTH);
		ArgumentChecks.isTrue(height > 0, ERROR_MESSAGE_WRONG_HEIGHT);
		ArgumentChecks.isTrue(percentage >= 0 && percentage <= 100, ERROR_MESSAGE_WRONG_PERCENTAGE);

		this.numberOfMines = (int) Math.ceil(width * height * percentage / 100);
	}

	/**
	 * Puts randomly the mines on the board according to the numberOfMines field
	 */
	private void putMines() {
		Random generator = new Random();
		int width = getNumberOfColumns();
		int height = getNumberOfRows();

		int minesLeftToPut = this.numberOfMines;
		int emptySquares = width * height;

		for (int i = 0; i < height && minesLeftToPut > 0; i++) {
			for (int j = 0; j < width && minesLeftToPut > 0; j++) {

				if (generator.nextInt(emptySquares) < minesLeftToPut) {
					this.squares[i][j].putMine();
					--minesLeftToPut;
				}
				--emptySquares;
			}
		}
	}

	/**
	 * Assigns the numbers/values of the squares according to the mines they have
	 * around
	 */
	private void putValues() {
		for (int i = 0; i < getNumberOfRows(); i++) {
			for (int j = 0; j < getNumberOfColumns(); j++) {
				if (this.squares[i][j].hasMine())
					continue;

				int numberOfMinesAround = 0;

				for (int k = i - 1; k <= i + 1; k++) {
					if (k < 0)
						continue; // if the row is out of the board from above, we skip to the next row

					else if (k >= getNumberOfRows())
						break; // if the row is out of the board from below, it ends because there aren't
								// more rows

					for (int l = j - 1; l <= j + 1; l++) {
						if (l < 0 || (k == i && l == j))
							continue; // if the column is out of the board from the left or we are in the element
										// [i][j], we skip to the next column
						if (l >= getNumberOfColumns())
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
	 * Sets the field numberOfMines as the given number of mines
	 * 
	 * @param numberOfMines is the given number of mines. It must be non-negative
	 *                      and less than or equal to the number of squares in the
	 *                      board. The numberOfMines must be equal to the real
	 *                      number of mines in the board
	 * @throws IllegalArgumentException if one of the parameters is not valid
	 */
	private void setNumberOfMines(int numberOfMines) {
		ArgumentChecks.isTrue(numberOfMines >= 0 && numberOfMines <= getNumberOfColumns() * getNumberOfRows(),
				ERROR_MESSAGE_WRONG_NUMBER_OF_MINES);

		int realNumberOfMines = 0;

		for (int i = 0; i < getNumberOfRows(); i++) {
			for (int j = 0; j < getNumberOfColumns(); j++) {
				if (squares[i][j].hasMine())
					realNumberOfMines++;
			}
		}
		ArgumentChecks.isTrue(numberOfMines == realNumberOfMines, ERROR_MESSAGE_BOARD_DOESNT_HAVE_THE_NUMBRE_OF_MINES);

		this.numberOfMines = numberOfMines;
	}

	/**
	 * @return a copy of the internally managed Square array.
	 */
	Square[][] getSquares() {
		Square[][] copy = new Square[this.squares.length][this.squares[0].length];

		for (int i = 0; i < getNumberOfRows(); i++) {
			for (int j = 0; j < getNumberOfColumns(); j++) {
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

	/**
	 * @return an array of characters representing the state of the game board. The
	 *         (x, y) position character shall represent the state of the (x, y)
	 *         position of the board.
	 */
	public char[][] getState() {
		char[][] state = new char[this.squares.length][this.squares[0].length];

		for (int i = 0; i < getNumberOfRows(); i++) {
			for (int j = 0; j < getNumberOfColumns(); j++) {
				state[i][j] = this.squares[i][j].getSymbol();
			}
		}
		return state;

	}

	/**
	 * @return the numberOfMines of the board
	 */
	public int getNumberOfMines() {
		return this.numberOfMines;
	}

	/**
	 * @return the number of mines that have not yet been covered with a flag.
	 */
	int getNumberOfMinesLeft() {
		int numberOfMinesLeft = numberOfMines;

		for (int i = 0; i < getNumberOfRows(); i++) {
			for (int j = 0; j < getNumberOfColumns(); j++) {
				if (this.squares[i][j].hasMine() && this.squares[i][j].isFlagged())
					numberOfMinesLeft--;

				if (numberOfMinesLeft == 0)
					return 0;
			}
		}
		return numberOfMinesLeft;
	}

	/**
	 * Sets the state to exploded if a mine has exploded on the board.
	 * 
	 * @return true if a mine has exploded on the board, false otherwise
	 */
	public boolean hasExploded() {
		markAsExploded();
		return this.exploded;
	}

	/**
	 * It places the internal state of the board as exploded (because some mine has
	 * been discovered). If a mine has not been discovered, the state doesn't change
	 */
	void markAsExploded() {
		for (int i = 0; i < getNumberOfRows(); i++) {
			for (int j = 0; j < getNumberOfColumns(); j++) {
				if (this.squares[i][j].hasMine() && this.squares[i][j].isOpened()) {
					this.exploded = true;
					break;
				}
			}
		}
	}

	/**
	 * @return true if the user wins (number of mines left is 0)
	 */
	public boolean hasWin() {
		return getNumberOfMinesLeft() == 0;
	}

	/**
	 * If it is not uncovered, uncovers the (x, y) coordinate box
	 * 
	 * @param x is the x-coordinate of the square in the board
	 * @param y is the y-coordinate of the square in the board
	 * @throws IllegalArgumentException if any of the parameters is wrong
	 *                                  (checkCoordinates(x,y))
	 */
	public boolean stepOn(int x, int y) {
		checkCoordinates(x, y);
		return this.squares[y][x].stepOn();
	}

	/**
	 * Returns a list with all the neighbours of the given square that are closed
	 * and doesn't have a mine
	 * 
	 * @param square is the given square to get the neighbours from. It must be not
	 *               null
	 * @param x      is the x-coordinate of the square in the board
	 * @param y      is the y-coordinate of the square in the board
	 * @return a list with all the neighbours of the given square that are closed
	 *         and doesn't have a mine
	 * @throws IllegalArgumentExpression if any of the parameters is wrong. The
	 *                                   coordinates are checked with
	 *                                   checkCoordinates(x,y)
	 */
	private List<Square> getNeighbours(Square square, int x, int y) {
		ArgumentChecks.isTrue(square != null, ERROR_MESSAGE_NULL_SQUARE);
		checkCoordinates(x, y);

		List<Square> neighbours = new ArrayList<>();
		for (int i = y - 1; i <= y + 1; i++) {
			if (i < 0)
				continue;
			if (i >= this.squares.length)
				break;

			for (int j = x - 1; j <= x + 1; j++) {
				if (j < 0)
					continue;
				if (j >= this.squares[i].length)
					break;

				if (this.squares[i][j].isClosed() && !this.squares[i][j].hasMine())
					neighbours.add(this.squares[i][j]);

			}
		}
		return neighbours;
	}

	/**
	 * Sets the Actions of a given square according to its value
	 * 
	 * @param square     is the given square to set the action according to its
	 *                   state. It must be not null
	 * @param neighbours is a list with the neighbours of the given square that
	 *                   doesn't have a mine and are closed. It must be not null and
	 *                   not have null elements
	 * @throws IllegalArgumentExpression if any of the parameters is wrong
	 */
	private void setAction(Square square, List<Square> neighbours) {
		ArgumentChecks.isTrue(square != null, ERROR_MESSAGE_NULL_SQUARE);
		ArgumentChecks.isTrue(neighbours != null, ClearAction.ERROR_MESSAGE_NULL_LIST_OF_NEIGHBOURS);
		for (Square neighbour : neighbours)
			ArgumentChecks.notNull(neighbour, ClearAction.ERROR_MESSAGE_NULL_NEIGHBOURS);

		if (square.isEmpty()) {
			square.setAction(new ClearAction(neighbours));

		} else if (square.hasMine()) {
			square.setAction(new BlowUpAction());
		} else {
			square.setAction(new NullAction());
		}
	}

	/**
	 * Sets all the actions of all the squares in the board according to its value
	 */
	private void setAllActions() {
		for (int i = 0; i < getNumberOfRows(); i++) {
			for (int j = 0; j < getNumberOfColumns(); j++) {
				List<Square> neighbours = getNeighbours(this.squares[i][j], j, i);
				setAction(this.squares[i][j], neighbours);
			}
		}
	}

	/**
	 * If there is no flag already and the box is not uncovered, a flag is placed in
	 * the (x, y) coordinate box. If there are no more flags left, nothing happens
	 * 
	 * @param x is the x-coordinate of the square in the board
	 * @param y is the y-coordinate of the square in the board
	 * @throws IllegalArgumentExpression if any of the parameters is wrong
	 *                                   (checkCoordinates(x,y))
	 */
	public void flag(int x, int y) {
		checkCoordinates(x, y);
		if (this.getNumberOfFlagsLeft() != 0)
			this.squares[y][x].flag();
	}

	/**
	 * Returns the number of flags that have not yet been set. The initial number of
	 * flags is equal to the numberOfMines
	 * 
	 * @return the number of flags left
	 */
	public int getNumberOfFlagsLeft() {
		int numberOfFlagsUsed = 0;
		for (int i = 0; i < getNumberOfRows(); i++) {
			for (int j = 0; j < getNumberOfColumns(); j++) {
				if (this.squares[i][j].isFlagged())
					numberOfFlagsUsed++;

				if (numberOfFlagsUsed == numberOfMines)
					return 0;
			}
		}
		return this.numberOfMines - numberOfFlagsUsed;
	}

	/**
	 * If there is a flag, remove a flag in the (x, y) coordinate box.
	 * 
	 * @param x is the x-coordinate of the square in the board
	 * @param y is the y-coordinate of the square in the board
	 * @throws IllegalArgumentExpression if any of the parameters is wrong
	 *                                   (checkCoordinates(x,y))
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
		for (int i = 0; i < getNumberOfRows(); i++) {
			for (int j = 0; j < getNumberOfColumns(); j++) {
				this.squares[i][j].open();
			}
		}
	}

	/**
	 * Find a random empty square and run stepOn() on it to reveal an island. The
	 * randomly chosen square cannot be a corner of the board.
	 */
	public void uncoverWelcomeArea() {
		int width = getNumberOfColumns();
		int height = getNumberOfRows();

		int totalSquares = width * height - this.numberOfMines;
		Random generator = new Random();
		boolean uncovered = false;

		if (totalSquares != 0) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if ((i == 0 && j == 0) || (i == 0 && j == width - 1) || (i == height - 1 && j == width - 1)
							|| (i == height - 1 && j == 0))
						continue;

					if (this.squares[i][j].isEmpty() && generator.nextInt(totalSquares) < width * height / 2) {
						this.squares[i][j].stepOn();
						uncovered = true;
						break;
					}
					--totalSquares;
				}
				if (uncovered) {
					break;
				}
			}
		}
	}
}
