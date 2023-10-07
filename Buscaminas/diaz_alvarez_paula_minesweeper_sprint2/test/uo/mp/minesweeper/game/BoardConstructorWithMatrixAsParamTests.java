package uo.mp.minesweeper.game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;

public class BoardConstructorWithMatrixAsParamTests {
	private Board board;
	private Square[][] squares;
	private int numberOfMines;

	@BeforeEach
	public void setUp() {
		Square emptySquare = new Square();
		Square mineSquare = new Square();
		mineSquare.putMine();

		squares = new Square[][] { { emptySquare, mineSquare }, { mineSquare, emptySquare } };
		numberOfMines = 2;

	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board by passing squares and numberOfMines
	 * <p>
	 * THEN: a board with squares as matrix and that numberOfMines
	 */
	@Test
	void validParameters() {
		board = new Board(numberOfMines, squares);
		assertArrayEquals(squares, board.getSquares());
		assertEquals(numberOfMines, board.getNumberOfMines());
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board by passing squares and a different numberOfMines from
	 * the mines of the squares
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void differentNumberOfMines() {
		numberOfMines++;
		try {
			board = new Board(numberOfMines, squares);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_BOARD_DOESNT_HAVE_THE_NUMBRE_OF_MINES, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board by passing squares and a negative numberOfMines
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void wrongNegativeMines() {
		numberOfMines = -1;
		try {
			board = new Board(numberOfMines, squares);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_NUMBER_OF_MINES, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board by passing squares and a negative numberOfMines
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void wrongMoreNegativeMines() {
		numberOfMines = -1;
		try {
			board = new Board(numberOfMines, squares);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_NUMBER_OF_MINES, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board by passing squares and a numberOfMines greater than
	 * the number of squares in the board
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void wrongBigNumberOfMines() {
		numberOfMines = squares.length * squares[0].length + 1;
		try {
			board = new Board(numberOfMines, squares);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_NUMBER_OF_MINES, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board by passing squares and a numberOfMines greater than
	 * the number of squares in the board
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void wrongBiggerNumberOfMines() {
		numberOfMines = squares.length * squares[0].length * 100;
		try {
			board = new Board(numberOfMines, squares);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_NUMBER_OF_MINES, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board by passing a null matrix and numberOfMines
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void nullBoard() {
		squares = null;
		try {
			board = new Board(numberOfMines, squares);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_NULL_BOARD, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board by passing squares with null elements and
	 * numberOfMines
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void nullElementsBoard() {
		squares[0][0] = null;
		try {
			board = new Board(numberOfMines, squares);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_NOT_INITIALIZED_BOARD, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board by passing squares with that is not square and
	 * numberOfMines
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void nonRectangularBoard() {
		squares = new Square[][] { { new Square(), new Square() }, { new Square() } };
		numberOfMines = 0;
		try {
			board = new Board(numberOfMines, squares);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_NOT_RECTANGULAR_BOARD, e.getMessage());
		}
	}

}
