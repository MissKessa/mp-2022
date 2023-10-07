package uo.mp.minesweeper.game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;
import uo.mp.minesweeper.util.exceptions.GameException;
import uo.mp.minesweeper.util.exceptions.UserInteractionException;

public class BoardFlagTests {
	private Board board;
	private Square[][] squares;
	private char[][] state;
	private int numberOfMines;

	@BeforeEach
	public void setUp() {
		Square emptySquare = new Square();
		Square numberSquare = new Square();
		numberSquare.setValue(2);
		Square mineSquare = new Square();
		mineSquare.putMine();
		Square mineSquare2 = new Square();
		mineSquare2.putMine();

		squares = new Square[][] { { emptySquare, mineSquare }, { mineSquare2, numberSquare } };
		numberOfMines = 2;
		board = new Board(numberOfMines, squares);
	}

	/**
	 * GIVEN: a board with 2 mines, an empty square and a square with a value
	 * <p>
	 * WHEN: we call flag() on a mine
	 * <p>
	 * THEN: the square is flag once. A flag is used and the number of mines
	 * decreases by 1
	 */
	@Test
	void flagOnUnmarkedMineBox() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.FLAG_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		final int numberOfMinesLeft = numberOfMines - 1;
		final int numberOfFlagsLeft = numberOfMines - 1;

		assertArrayEquals(squares, board.getSquares());
		try {
			board.flag(1, 0);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfFlagsLeft, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMinesLeft, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty square and a square with a value
	 * <p>
	 * WHEN: we call flag() on an empty square
	 * <p>
	 * THEN: the square is flag once. A flag is used and the number of mines is the
	 * number of mines of the board
	 */
	@Test
	void flagOnUnmarkedBoxWithoutMine() {
		state = new char[][] { { Square.FLAG_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		final int numberOfMinesLeft = numberOfMines;
		final int numberOfFlagsLeft = numberOfMines - 1;

		assertArrayEquals(squares, board.getSquares());
		try {
			board.flag(0, 0);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfFlagsLeft, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMinesLeft, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares with a flag and a square with a
	 * value
	 * <p>
	 * WHEN: we call flag() on an empty square with a flag
	 * <p>
	 * THEN: Nothing happens (it stays flagged), (GameException is thrown). A the
	 * number of flags remains and the number of mines remains
	 */
	@Test
	void flagOverAlreadyMarkedBox() {
		state = new char[][] { { Square.FLAG_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		final int numberOfMinesLeft = numberOfMines;
		final int numberOfFlagsLeft = numberOfMines - 1;

		try {
			squares[0][0].flag();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(squares, board.getSquares());
		try {
			board.flag(0, 0);
			fail("An exception should be thrown");
		} catch (GameException e) {

		} catch (UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfFlagsLeft, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMinesLeft, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty square and a square with a value
	 * <p>
	 * WHEN: we call flag() on an empty square and then call it again
	 * <p>
	 * THEN: the square is flag once, and after calling again nothing happens (it
	 * stays flagged), (GameException is thrown). A flag is used and the number of
	 * mines left is the number of mine sin the board
	 */
	@Test
	void flagOverASquareTwice() {
		state = new char[][] { { Square.FLAG_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		final int numberOfMinesLeft = numberOfMines;
		final int numberOfFlagsLeft = numberOfMines - 1;

		assertArrayEquals(squares, board.getSquares());
		try {
			board.flag(0, 0);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());
		try {
			board.flag(0, 0);
			fail("An exception should be thrown");
		} catch (GameException e) {

		} catch (UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfFlagsLeft, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMinesLeft, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares and a square with a value
	 * <p>
	 * WHEN: we call flag() on an mine square and then call it again
	 * <p>
	 * THEN: the square is flag once, and after calling again nothing happens (it
	 * stays flagged), (GameException is thrown). A flag is used and the number of
	 * mines left is the number of mine sin the board
	 */
	@Test
	void flagOverMineBoxTwice() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.FLAG_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		assertArrayEquals(squares, board.getSquares());
		try {
			board.flag(1, 0);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());
		try {
			board.flag(1, 0);
			fail("An exception should be thrown");
		} catch (GameException e) {

		} catch (UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfMines - 1, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMines - 1, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares and a square with a value
	 * <p>
	 * WHEN: we call flag() on an empty square
	 * <p>
	 * THEN: the square is flag once, (GameException is thrown). A flag is used and
	 * the number of mines is the number of mines of the board
	 */
	@Test
	void flagUnveilBoxWithoutMine() {
		state = new char[][] { { Square.EMPTY_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		assertArrayEquals(squares, board.getSquares());
		try {
			squares[0][0].open();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		try {
			board.flag(0, 0);
			fail("An exception should be thrown");
		} catch (GameException e) {

		} catch (UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfMines, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMines, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares and a square with a value
	 * <p>
	 * WHEN: we call flag() on an empty square and then call it again when there are
	 * no more flags
	 * <p>
	 * THEN: the square is flag once, and after calling again nothing happens (it
	 * stays flagged). A flag is used and the number of mines left is the number of
	 * mine sin the board
	 */
	@Test
	void flagWhenThereIsNoMoreFlags() { // *
		state = new char[][] { { Square.FLAG_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		final int numberOfMinesLeft = numberOfMines;
		final int numberOfFlagsLeft = numberOfMines - 1;

		assertArrayEquals(squares, board.getSquares());
		try {
			board.flag(0, 0);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());
		try {
			board.flag(0, 0);
			fail("An exception should be thrown");
		} catch (GameException e) {

		} catch (UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfFlagsLeft, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMinesLeft, board.getNumberOfMinesLeft());
	}
}
