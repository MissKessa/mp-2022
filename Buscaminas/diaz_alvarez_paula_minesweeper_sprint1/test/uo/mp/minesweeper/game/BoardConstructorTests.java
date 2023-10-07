package uo.mp.minesweeper.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;

public class BoardConstructorTests {
	private Board board;
	private Square[][] squares;
	private Square emptySquare;
	private Square mineSquare;

	@BeforeEach
	public void setUp() {
		emptySquare = new Square();
		mineSquare = new Square();
		mineSquare.putMine();
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board of 2 by 2 with 50% of mines
	 * <p>
	 * THEN: a board of 2 by 2 with half of mines is created
	 */
	@Test
	void boardWithHalfMines() {
		final int length = 2;
		final int percentage = 50;
		board = new Board(length, length, percentage);
		assertEquals(length, board.getNumberOfMines());
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board of 3 by 3 with 100% of mines
	 * <p>
	 * THEN: a board of 3 by 3 with full of mines is created
	 */
	@Test
	void boardWithFullMines() {
		final int length = 3;
		final int percentage = 100;
		squares = new Square[][] { { mineSquare, mineSquare, mineSquare }, { mineSquare, mineSquare, mineSquare },
				{ mineSquare, mineSquare, mineSquare } };
		board = new Board(length, length, percentage);
		assertArrayEquals(squares, board.getSquares());
		assertEquals(length * length, board.getNumberOfMines());

	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board of 2 by 2 with 0% of mines
	 * <p>
	 * THEN: a board of 2 by 2 with no mines is created
	 */
	@Test
	void boardWithNoMines() {
		final int length = 2;
		final int percentage = 0;
		squares = new Square[][] { { emptySquare, emptySquare }, { emptySquare, emptySquare } };
		board = new Board(length, length, percentage);
		assertArrayEquals(squares, board.getSquares());
		assertEquals(0, board.getNumberOfMines());
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board with a negative width
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void wrongNegativeWidth() {
		final int width = -1;
		final int height = 2;
		final int percentage = 0;
		try {
			board = new Board(width, height, percentage);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_WIDTH, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board with a negative width
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void wrongMoreNegativeWidth() {
		final int width = -100;
		final int height = 2;
		final int percentage = 0;
		try {
			board = new Board(width, height, percentage);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_WIDTH, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board with a negative height
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void wrongNegativeHeight() {
		final int width = 2;
		final int height = -1;
		final int percentage = 0;
		try {
			board = new Board(width, height, percentage);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_HEIGHT, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board with a negative height
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void wrongMoreNegativeHeight() {
		final int width = 2;
		final int height = -100;
		final int percentage = 0;
		try {
			board = new Board(width, height, percentage);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_HEIGHT, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board with a negative percentage
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void wrongNegativePercentage() {
		final int width = 2;
		final int height = 2;
		final int percentage = -1;
		try {
			board = new Board(width, height, percentage);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_PERCENTAGE, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board with a negative percentage
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void wrongMoreNegativePercentage() {
		final int width = 2;
		final int height = 2;
		final int percentage = -100;
		try {
			board = new Board(width, height, percentage);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_PERCENTAGE, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board with a percentage greater than 100
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void wrongBigPercentage() {
		final int width = 2;
		final int height = 2;
		final int percentage = 101;
		try {
			board = new Board(width, height, percentage);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_PERCENTAGE, e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board with a percentage greater than 100
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void wrongBiggerPercentage() {
		final int width = 2;
		final int height = 2;
		final int percentage = 2000;
		try {
			board = new Board(width, height, percentage);
		} catch (Exception e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_PERCENTAGE, e.getMessage());
		}
	}

}
