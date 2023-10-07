package uo.mp.minesweeper.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;

public class BoardUnveilTests {
	private Board board;
	private Square[][] squares;
	private Square[][] unveilBoard;

	@BeforeEach
	public void setUp() {
		Square emptySquare = new Square();
		Square mineSquare = new Square();
		mineSquare.putMine();
		squares = new Square[][] { { emptySquare, mineSquare }, { mineSquare, emptySquare } };
		int numberOfMines = 2;
		board = new Board(numberOfMines, squares);

		Square emptySquare2 = new Square();
		emptySquare2.open();
		Square mineSquare2 = new Square();
		mineSquare2.putMine();
		mineSquare2.open();
		unveilBoard = new Square[][] { { emptySquare2, mineSquare2 }, { mineSquare2, emptySquare2 } };
	}

	/**
	 * GIVEN: a board with 2 mines and 2 empty squares
	 * <p>
	 * WHEN: we call unveil()
	 * <p>
	 * THEN: the board is full opened
	 */
	@Test
	void unveilCoveredBoard() {
		assertArrayEquals(squares, board.getSquares());
		board.unveil();
		assertArrayEquals(unveilBoard, board.getSquares());
	}

	/**
	 * GIVEN: a board with 2 mines and 2 empty squares. It has 2 squares flagged
	 * <p>
	 * WHEN: we call unveil()
	 * <p>
	 * THEN: the board is full opened
	 */
	@Test
	void unveilBoardWithSomeFlags() {
		assertArrayEquals(squares, board.getSquares());
		board.flag(0, 0);
		board.flag(0, 1);
		board.unveil();
		assertArrayEquals(unveilBoard, board.getSquares());
	}

	/**
	 * GIVEN: a board with 2 mines and 2 empty squares. It has 2 squares already
	 * opened
	 * <p>
	 * WHEN: we call unveil()
	 * <p>
	 * THEN: the board is full opened
	 */
	@Test
	void unveilBoardABitUnconvered() {
		assertArrayEquals(squares, board.getSquares());
		board.stepOn(0, 0);
		board.stepOn(0, 1);
		board.unveil();
		assertArrayEquals(unveilBoard, board.getSquares());
	}

	/**
	 * GIVEN: a board with 2 mines and 2 empty squares. It has at least one square
	 * opened, one flagged and one closed
	 * <p>
	 * WHEN: we call unveil()
	 * <p>
	 * THEN: the board is full opened
	 */
	@Test
	void unveilBoardWithALotOfStates() {
		assertArrayEquals(squares, board.getSquares());
		board.flag(0, 1);
		board.stepOn(0, 0);

		board.unveil();
		assertArrayEquals(unveilBoard, board.getSquares());
	}

}
