package uo.mp.minesweeper.game;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;

public class BoardStepOnTests {
	private Board board;
	private Square[][] squares;
	private int numberOfMines;
	private char[][] state;

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
	 * GIVEN: a board with 2 mines, an empty squares (open) and a square with a
	 * value
	 * <p>
	 * WHEN: we call stepOn() on the open empty square
	 * <p>
	 * THEN: nothing happens
	 */
	@Test
	void stepOnClosedEmptyBox() {
		state = new char[][] { { Square.EMPTY_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		board.stepOn(0, 0);
		assertArrayEquals(state, board.getState());

	}

	/**
	 * GIVEN: a board with 2 mines, an empty square and a square with a value
	 * <p>
	 * WHEN: we call stepOn() on a mine
	 * <p>
	 * THEN: the mine is opened
	 */
	@Test
	void stepOnClosedMineBox() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.MINE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		board.stepOn(1, 0);
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty square and a square with a value
	 * <p>
	 * WHEN: we call stepOn() on the square with the value that is closed
	 * <p>
	 * THEN: the square is opened
	 */
	@Test
	void stepOnClosedNumericalBox() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, '2' } };
		board.stepOn(1, 1);
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares with flag and a square with a
	 * value
	 * <p>
	 * WHEN: we call stepOn() on the flag empty square
	 * <p>
	 * THEN: nothing happens
	 */
	@Test
	void stepOnEmptyBoxWithFlag() {
		state = new char[][] { { Square.FLAG_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		squares[0][0].flag();
		board.stepOn(0, 0);
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines (with a flag), an empty squares and a square with
	 * a value
	 * <p>
	 * WHEN: we call stepOn() on the flag mine square
	 * <p>
	 * THEN: nothing happens
	 */
	@Test
	void stepOnMineBoxWithFlag() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.FLAG_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		squares[0][1].flag();
		board.stepOn(1, 0);
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty square and a square with a value
	 * <p>
	 * WHEN: we call stepOn() on the square with the value that is flagged
	 * <p>
	 * THEN: nothing happens
	 */
	@Test
	void stepOnNumericalBoxWithFlag() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.FLAG_SYMBOL } };
		squares[1][1].flag();
		board.stepOn(1, 1);
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty square and a square with a value
	 * <p>
	 * WHEN: we call stepOn() on the empty square
	 * <p>
	 * THEN: the mine is opened
	 */
	@Test
	void stepOnOpenEmptyBox() {
		state = new char[][] { { Square.EMPTY_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		squares[0][0].open();
		board.stepOn(0, 0);
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty square and a square with a value
	 * <p>
	 * WHEN: we call stepOn() on the square with the value that is opened
	 * <p>
	 * THEN: nothing happens
	 */
	@Test
	void stepOnOpenNumericalBox() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, '2' } };
		squares[1][1].open();
		board.stepOn(1, 1);
		assertArrayEquals(state, board.getState());
	}
}
