package uo.mp.minesweeper.game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;

public class BoardUnflagTests {
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
	 * GIVEN: a board with 2 mines (one has a flag), an empty squares and a square
	 * with a value
	 * <p>
	 * WHEN: we call unflag() on a mine box with flag
	 * <p>
	 * THEN: the square is unflagged. The number of flags is increased by 1 and the
	 * number of mines left is the number of mines in the board
	 */
	@Test
	void uncheckMineBoxWithFlag() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		board.flag(1, 0);
		assertArrayEquals(squares, board.getSquares());
		board.unflag(1, 0);
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfMines, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMines, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares and a square with a value
	 * <p>
	 * WHEN: we call unflag() on a mine box without flag
	 * <p>
	 * THEN: the square is unflagged. The number of flags remains the same and the
	 * number of mines left is the number of mines in the board
	 */
	@Test
	void uncheckMineBoxWithoutFlag() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		assertArrayEquals(squares, board.getSquares());
		board.unflag(1, 0);
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfMines, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMines, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares and a square with a value
	 * <p>
	 * WHEN: we call unflag() on a empty square
	 * <p>
	 * THEN: the square is unflagged. The number of flags remains the same and the
	 * number of mines left is the number of mines in the board
	 */
	@Test
	void uncheckBoxWithoutFlag() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		assertArrayEquals(squares, board.getSquares());
		board.unflag(0, 0);
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfMines, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMines, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares and a square with a value
	 * <p>
	 * WHEN: we call unflag() on a empty square with a flag
	 * <p>
	 * THEN: the square is unflagged. The number of flags remains the same and the
	 * number of mines left is the number of mines in the board
	 */
	@Test
	void uncheckBoxWithFlag() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		assertArrayEquals(squares, board.getSquares());
		board.flag(0, 0);
		board.unflag(0, 0);
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfMines, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMines, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares and a square with a value
	 * <p>
	 * WHEN: we call unflag() on a empty square twice
	 * <p>
	 * THEN: the square is unflagged. The number of flags remains the same and the
	 * number of mines left is the number of mines in the board
	 */
	@Test
	void uncheckBoxTwice() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		assertArrayEquals(squares, board.getSquares());
		board.unflag(0, 0);
		assertArrayEquals(state, board.getState());
		board.unflag(0, 0);
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfMines, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMines, board.getNumberOfMinesLeft());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares and a square with a value
	 * <p>
	 * WHEN: we call unflag() on a mine square with a flag
	 * <p>
	 * THEN: the square is unflagged. The number of flags remains the same and the
	 * number of mines left is the number of mines in the board
	 */
	@Test
	void uncheckMineBoxTwice() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		assertArrayEquals(squares, board.getSquares());
		board.unflag(1, 0);
		assertArrayEquals(state, board.getState());
		board.unflag(1, 0);
		assertArrayEquals(state, board.getState());

		assertEquals(numberOfMines, board.getNumberOfFlagsLeft());
		assertEquals(numberOfMines, board.getNumberOfMinesLeft());
	}
}
