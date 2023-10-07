package uo.mp.minesweeper.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;
import uo.mp.minesweeper.util.exceptions.GameException;
import uo.mp.minesweeper.util.exceptions.UserInteractionException;

public class BoardUnveilTests {
	private Board board;
	private Square[][] squares;
	private Square[][] unveilBoard;

	@BeforeEach
	public void setUp() {
		Square emptySquare = new Square();
		Square emptySquare2 = new Square();
		Square mineSquare = new Square();
		mineSquare.putMine();
		Square mineSquare2 = new Square();
		mineSquare2.putMine();
		squares = new Square[][] { { emptySquare, mineSquare }, { mineSquare2, emptySquare2 } };
		int numberOfMines = 2;
		board = new Board(numberOfMines, squares);

		Square emptySquare3 = new Square();
		Square emptySquare4 = new Square();
		try {
			emptySquare3.open();
			emptySquare4.open();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		Square mineSquare3 = new Square();
		mineSquare3.putMine();
		Square mineSquare4 = new Square();
		mineSquare4.putMine();
		try {
			mineSquare3.open();
			mineSquare4.open();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		unveilBoard = new Square[][] { { emptySquare3, mineSquare3 }, { mineSquare4, emptySquare4 } };
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
		try {
			board.flag(0, 0);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
		try {
			board.flag(0, 1);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
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
		try {
			board.stepOn(0, 0);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
		try {
			board.stepOn(0, 1);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
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
		try {
			board.flag(0, 1);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
		try {
			board.stepOn(0, 0);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}

		board.unveil();
		assertArrayEquals(unveilBoard, board.getSquares());
	}

}
