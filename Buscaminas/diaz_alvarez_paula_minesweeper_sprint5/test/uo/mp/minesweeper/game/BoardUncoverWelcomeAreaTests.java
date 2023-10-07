package uo.mp.minesweeper.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;

public class BoardUncoverWelcomeAreaTests {
	private Board board;
	private Square[][] squares;
	private char[][] state;

	/**
	 * GIVEN: a board: [[-1,1,0],[1,1,0],[0,0,0]] (all the squares are closed)
	 * <p>
	 * WHEN: we call uncoverWelcomeArea()
	 * <p>
	 * THEN: all the board is discovered except the mine
	 */
	@Test
	void case1() {
		Square mineSquare = new Square();
		mineSquare.putMine();

		Square valueSquare = new Square();
		valueSquare.setValue(1);

		Square valueSquare2 = new Square();
		valueSquare2.setValue(1);

		Square valueSquare3 = new Square();
		valueSquare3.setValue(1);

		Square emptySquare = new Square();
		Square emptySquare2 = new Square();
		Square emptySquare3 = new Square();
		Square emptySquare4 = new Square();
		Square emptySquare5 = new Square();

		int numberOfMines = 1;
		squares = new Square[][] { { mineSquare, valueSquare, emptySquare },
				{ valueSquare2, valueSquare3, emptySquare2 }, { emptySquare3, emptySquare4, emptySquare5 } };

		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, '1', Square.EMPTY_SQUARE_SYMBOL },
				{ '1', '1', Square.EMPTY_SQUARE_SYMBOL },
				{ Square.EMPTY_SQUARE_SYMBOL, Square.EMPTY_SQUARE_SYMBOL, Square.EMPTY_SQUARE_SYMBOL } };

		board = new Board(numberOfMines, squares);
		board.uncoverWelcomeArea();
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board: [[-1,1,0,2,-1,2],[1,1,0,3,-1,3],[0,0,0,2,-1,2]] (all the
	 * squares are closed)
	 * <p>
	 * WHEN: we call uncoverWelcomeArea()
	 * <p>
	 * THEN: all the board is discovered except the mine and the last column
	 */
	@Test
	void case2() {
		Square mineSquare = new Square();
		mineSquare.putMine();

		Square mineSquare2 = new Square();
		mineSquare2.putMine();

		Square mineSquare3 = new Square();
		mineSquare3.putMine();

		Square mineSquare4 = new Square();
		mineSquare4.putMine();

		Square valueSquare11 = new Square();
		valueSquare11.setValue(1);

		Square valueSquare12 = new Square();
		valueSquare12.setValue(1);

		Square valueSquare13 = new Square();
		valueSquare13.setValue(1);

		Square valueSquare31 = new Square();
		valueSquare31.setValue(3);

		Square valueSquare32 = new Square();
		valueSquare32.setValue(3);

		Square valueSquare21 = new Square();
		valueSquare21.setValue(2);

		Square valueSquare22 = new Square();
		valueSquare22.setValue(2);

		Square valueSquare23 = new Square();
		valueSquare23.setValue(2);

		Square valueSquare24 = new Square();
		valueSquare24.setValue(2);

		Square emptySquare = new Square();
		Square emptySquare2 = new Square();
		Square emptySquare3 = new Square();
		Square emptySquare4 = new Square();
		Square emptySquare5 = new Square();

		int numberOfMines = 4;

		squares = new Square[][] {
				{ mineSquare, valueSquare11, emptySquare, valueSquare21, mineSquare2, valueSquare22 },
				{ valueSquare12, valueSquare13, emptySquare2, valueSquare31, mineSquare3, valueSquare32 },
				{ emptySquare3, emptySquare4, emptySquare5, valueSquare23, mineSquare4, valueSquare24 } };

		state = new char[][] {
				{ Square.CLOSED_SQUARE_SYMBOL, '1', Square.EMPTY_SQUARE_SYMBOL, '2', Square.CLOSED_SQUARE_SYMBOL,
						Square.CLOSED_SQUARE_SYMBOL },
				{ '1', '1', Square.EMPTY_SQUARE_SYMBOL, '3', Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.EMPTY_SQUARE_SYMBOL, Square.EMPTY_SQUARE_SYMBOL, Square.EMPTY_SQUARE_SYMBOL, '2',
						Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };

		board = new Board(numberOfMines, squares);
		board.uncoverWelcomeArea();
		assertArrayEquals(state, board.getState());
	}
}
