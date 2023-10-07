package uo.mp.minesweeper.game;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;

public class BoardGetStateTests {
	private Board board;
	private Square[][] squares;
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
		int numberOfMines = 2;
		board = new Board(numberOfMines, squares);
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares and a square with a value
	 * <p>
	 * WHEN: we call getState() when it's covered
	 * <p>
	 * THEN: the state array has all the elements as CLOSED_SQUARE_SYMBOL
	 */
	@Test
	void getStateCoveredBoard() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		assertArrayEquals(squares, board.getSquares());
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares and a square with a value
	 * <p>
	 * WHEN: we call getState() when it's unveil
	 * <p>
	 * THEN: the state array has the symbols: EMPTY_SQUARE_SYMBOL, MINE_SYMBOL and a
	 * char of the value of the square
	 */
	@Test
	void getStateUncoveredBoard() {
		state = new char[][] { { Square.EMPTY_SQUARE_SYMBOL, Square.MINE_SYMBOL }, { Square.MINE_SYMBOL, '2' } };
		assertArrayEquals(squares, board.getSquares());
		board.unveil();
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares and a square with a value
	 * <p>
	 * WHEN: we call getState() when it has some uncovered squares, some with flags
	 * <p>
	 * THEN: the state array has some elements open, other flagged and other closed
	 */
	@Test
	void getStateIntermediateBoard() {
		state = new char[][] { { Square.EMPTY_SQUARE_SYMBOL, Square.FLAG_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, '2' } };
		assertArrayEquals(squares, board.getSquares());
		board.stepOn(0, 0);
		board.flag(1, 0);
		assertArrayEquals(state, board.getState());
	}

}
