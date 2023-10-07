package uo.mp.lab01.game2048.model;

import java.util.ArrayList;
import java.util.Random;

//import uo.mp.lab01.game2048.ui.GameApp;

/**
 * Abstraction of the game 2048. This abstraction represents the game 2048, more
 * information about this game can be found at:
 * <a href="http://juego2048.es/">http://juego2048.es/</a>. This class allows to
 * create instances of different sizes, the default size being 3x3. In addition,
 * it allows to perform the different actions of interacting with the board:
 * moveRight, moveLeft, moveUp and moveDown.
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class Game2048 {
	/**
	 * It's the value of the default size of the board
	 */
	public final static int DEFAULT_BOARD_SIZE = 3;
	/**
	 * It's the minimum value for the size of the board
	 */
	public final static int MINIMUM_DIMENSION = 3;
	/**
	 * It's the maximum value for the size of the board
	 */
	public final static int MAXIMUM_DIMENSION = 5;

	/*
	 * It's the error message thrown if the board is null
	 */
	public final static String ERROR_NULL_BOARD = "The board must be not null";

	private int[][] board;

	/**
	 * Default builder for the 2048 game. Creates a new game instance with a 3x3
	 * board. It makes use of the {@code DEFAULT_BOARD_SIZE} constant and invokes
	 * the constructor that receives the size as a parameter.
	 */
	public Game2048() {
		this(DEFAULT_BOARD_SIZE);
	}

	/**
	 * This constructor creates an instance of the game 2048 with a board whose size
	 * is given by the constructor parameter. The size has to be within the limits
	 * of {@value #MINIMUM_DIMENSION} and {@value #MAXIMUM_DIMENSION}, both
	 * included. If the given size is not within these limits then the created
	 * instance will use a board with size equal to {@value #DEFAULT_BOARD_SIZE}. To
	 * do this, creates a size x size square integer array and invokes the Game2048
	 * constructor which takes an integer array as a parameter.
	 *
	 * @param size is an integer representing the size of the board to be used
	 *             during the game. If the given value is not between 3 and 5
	 *             inclusive, then the value of {@value #DEFAULT_BOARD_SIZE} is used
	 *             as the board size.
	 */
	public Game2048(int size) {
		int finalSize;
		if (size < MINIMUM_DIMENSION || size > MAXIMUM_DIMENSION) {
			finalSize = DEFAULT_BOARD_SIZE;

		} else {
			finalSize = size;
		}
		board = new int[finalSize][finalSize];
		for (int i = 0; i < finalSize; i++) {
			for (int j = 0; j < finalSize; j++) {
				board[i][j] = 0;
			}
		}
	}

	/**
	 * This constructor creates an instance of the game 2048 and assigns the given
	 * integer array as the game board. To do so, the given matrix must meet the
	 * conditions that it is a square matrix with size between
	 * {@value #MINIMUM_DIMENSION} and {@value #MAXIMUM_DIMENSION}, both included.
	 * If any of these conditions is not met, then the game is initialized with a
	 * default game board. Also, if the reference of the integer array given as
	 * parameter points to null then the NullPointerException is thrown and the game
	 * instance is not created.
	 *
	 * @param board is an array of integers that will be assigned as the board of
	 *              the game to be created. This matrix has to be square with size
	 *              between 3 and 5, both included. It must also be composed of
	 *              numbers that are powers of 2. In case the given matrix does not
	 *              meet any of the conditions, the game will be initialized with
	 *              the default board. If it points to null then
	 *              NullPointerException.
	 * @throws IllegalArgumentException if board is null.
	 */
	public Game2048(int[][] board) {
		if (board == null) {
			throw new NullPointerException(ERROR_NULL_BOARD);
		}

		// Checking if the size is out of range
		boolean invalidBoard = false;
		/*
		 * if (board.length<MINIMUM_DIMENSION || board.length>MAXIMUM_DIMENSION) {
		 * invalidBoard=true; }
		 */
		for (int i = 0; i < board.length; i++) {
			if (board[i].length < MINIMUM_DIMENSION || board[i].length > MAXIMUM_DIMENSION
					|| board[i].length != board.length) {
				invalidBoard = true;
				break;
			}
			for (int j = 0; j < board.length; j++) { // check the elements are powers of 2
				if (!isNumberPowerOfTwo(board[i][j])) {
					invalidBoard = true;
					break;
				}
			}
		}

		if (invalidBoard) { // Creating default board
			this.board = new int[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
			for (int i = 0; i < DEFAULT_BOARD_SIZE; i++) {
				for (int j = 0; j < DEFAULT_BOARD_SIZE; j++) {
					this.board[i][j] = 0;
				}
			}
		} else { // The matrix it's the board
			this.board = board;
		}

	}

	/**
	 * Returns a copy of the board Useful for testing purposes
	 *
	 * @return an exact copy of the board
	 */
	public int[][] getBoard() {
		int[][] copy = new int[board.length][board.length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				copy[i][j] = board[i][j];
			}
		}
		return copy;
	}

	/**
	 * This method resets all board positions to 0 and then calls next() to place a
	 * 2 at a random position on the board.
	 */
	public void restart() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = 0;
			}
		}
		next();
	}

	/**
	 * Understanding that a position containing a 0 on the board is an empty
	 * position, this method tells us whether the board is full or not. That is, if
	 * it does not have any position with a 0 then it returns true and if any
	 * position has a 0 then it returns false. To do this it goes through all the
	 * positions on the board and if it finds any position that is 0 then it returns
	 * false and terminates the execution.
	 *
	 * @return if the board does not have any position with a 0 then it returns true
	 *         and if any position has a 0 then it returns false.
	 */
	public boolean isBoardFull() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @return false if the player has not won yet and there are some free tiles on
	 *         the board; true otherwise. false = not(hasWon) + not (isBoardFull)
	 */
	public boolean isGameFinished() {
		return hasWon() || isBoardFull();
	}

	/**
	 * This method checks if the condition to finish the game and win is reached.
	 * That is, if some tile contains a 2048. Then, it returns true; otherwise,
	 * returns false.
	 * 
	 * @return true if the condition to finish the game and win is reached.
	 */
	private boolean hasWon() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 2048) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks whether the number given as a parameter is a power of 2 or not. If
	 * yes, it returns true, if no, it returns false.
	 *
	 * @param number to check if it is power of 2 or not.
	 * @return true if the given number is a power of 2; false otherwise.
	 */
	private boolean isNumberPowerOfTwo(int number) {
		return (number & number - 1) == 0;
	}

	/**
	 * Writes a 2 value on a random free tile, assuming there is one.
	 *
	 * @return true if there were a free tile; false otherwise.
	 */
	public boolean next() {
		int zeros = 0;
		ArrayList<String> positionZeros = new ArrayList<String>(0);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					zeros++;
					positionZeros.add(i + "," + j);
				}
			}
		}

		int freeTiles = board.length ^ 2 - zeros;
		if (freeTiles == (board.length ^ 2)) {
			return false;
		}

		Random generator = new Random();
		String positionPicked = positionZeros.get(generator.nextInt(0, positionZeros.size()));
		board[Integer.valueOf(positionPicked.substring(0, 1))][Integer.valueOf(positionPicked.substring(2, 3))] = 2;
		return true;
	}

	/**
	 * Moves all the tiles to the right
	 */
	public void compactRight() {
		for (int i = 0; i < board.length; i++) {
			for (int j = board[i].length - 1; j > 0; j--) {
				if (board[i][j] == 0) {
					int k = j;
					while (board[i][k] == 0 && k > 0) {
						k--;
					}
					board[i][j] = board[i][k];
					board[i][k] = 0;
				}
			}
		}

	}

	/**
	 * COmpacta, suma, compacta, next??
	 */
	public void moveRight() {
		compactRight();
		for (int i = 0; i < board.length; i++) {
			for (int j = board.length - 1; j > 0; j--) {
				if (board[i][j - 1] == board[i][j]) {
					board[i][j] += board[i][j - 1];
					board[i][j - 1] = 0;
				}
			}
		}
		compactRight();
	}

	/**
	 * Moves all the tiles to the left
	 */
	public void compactLeft() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length - 1; j++) {
				if (board[i][j] == 0) {
					int k = j;
					while (board[i][k] == 0 && k < board.length - 1) {
						k++;
					}
					board[i][j] = board[i][k];
					board[i][k] = 0;
				}
			}
		}
	}

	/**
	 * COmpacta, suma, compacta, next??
	 */
	public void moveLeft() {
		compactLeft();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length - 1; j++) {
				if (board[i][j + 1] == board[i][j]) {
					board[i][j] += board[i][j + 1];
					board[i][j + 1] = 0;
				}
			}
		}
		compactLeft();
	}

	/**
	 * Moves all the tiles up and sums the consecutive tiles if the tile is equal to
	 * the one above. If they are not equal nothing happens. If they are empty tiles
	 * upwards, then the non empty tiles are moved to that position
	 */
	public void compactUp() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length - 1; j++) {
				if (board[j][i] == 0) {
					int k = j;
					while (board[k][i] == 0 && k < board.length - 1) {
						k++;
					}
					board[j][i] = board[k][i];
					board[k][i] = 0;
				}
			}
		}
	}

	public void moveUp() {
		compactUp();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length - 1; j++) {
				if (board[j + 1][i] == board[j][i]) {
					board[j][i] += board[j + 1][i];
					board[j + 1][i] = 0;
				}
			}
		}
		compactUp();
	}

	/**
	 * Moves all the tiles down and sums the consecutive tiles if the tile is equal
	 * to the one below. If they are not equal nothing happens. If they are empty
	 * tiles downwards, then the non empty tiles are moved to that position
	 */
	public void compactDown() {
		for (int i = 0; i < board.length; i++) {
			for (int j = board.length - 1; j > 0; j--) {
				if (board[j][i] == 0) {
					board[j][i] += board[j - 1][i];
					board[j - 1][i] = 0;
				}
				if (board[j][i] == 0) {
					int k = j;
					while (board[k][i] == 0 && k > 0) {
						k--;
					}
					board[j][i] = board[k][i];
					board[k][i] = 0;
				}
			}
		}
	}

	public void moveDown() {
		compactDown();
		for (int i = 0; i < board.length; i++) {
			for (int j = board.length - 1; j > 0; j--) {
				if (board[j - 1][i] == board[j][i]) {
					board[j][i] += board[j - 1][i];
					board[j - 1][i] = 0;
				}
			}
		}
		compactDown();
	}

	/**
	 * Returns a String representation of this instance of the Game2048.
	 *
	 * @return a String with the content of the board to be printed out. Format each
	 *         string so there are 5 spaces for every cell in the same row. Insert a
	 *         new line between each two rows Example: 2 2 0 2 0 0 2 0 2 will be "2
	 *         2 0 \n2 0 0 \n2 0 2 \n"
	 */
	@Override
	public String toString() {
		String sb = "";
		for (int row = 0; row < this.board.length; row++) {
			for (int col = 0; col < this.board.length; col++) {
				sb += String.format("%5s", board[row][col]);
			}
			sb += "\n";
		}
		return sb;
	}

	public static void main(String[] args) {
		Game2048 g = new Game2048(new int[][] { { 2, 0, 2 }, { 2, 0, 0 }, { 0, 0, 2 } });
		System.out.println(g.toString());
		// g.restart();
		System.out.println();
		// g.next();
		g.compactLeft();
		System.out.println(g.toString());

	}
}
