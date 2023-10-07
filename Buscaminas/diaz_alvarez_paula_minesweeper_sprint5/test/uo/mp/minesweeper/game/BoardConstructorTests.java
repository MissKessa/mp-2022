package uo.mp.minesweeper.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;
import uo.mp.minesweeper.util.exceptions.GameException;

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
	 * WHEN: creating a board of the minimum length with 50% of mines
	 * <p>
	 * THEN: a board of the minimum length with half of mines is created
	 */
	@Test
	void boardWithHalfMines() {
		final int length = Board.MINIMUM_HEIGHT;
		final int percentage = 50;
		final int numberOfMines = 40;
		try {
			board = new Board(length, length, percentage);
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		assertEquals(numberOfMines, board.getNumberOfMines());
		assertFalse(board.hasExploded());
		assertEquals(length, board.getNumberOfColumns());
		assertEquals(length, board.getNumberOfRows());
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board of minimum length +1 with 100% of mines
	 * <p>
	 * THEN: a board of minimum length +1 with full of mines is created
	 */
	@Test
	void boardWithFullMines() {
		final int length = Board.MINIMUM_HEIGHT + 1;
		;
		final int percentage = 100;
		final int numberOfMines = length * length;
		squares = new Square[][] {
				{ mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare,
						mineSquare, mineSquare },
				{ mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare,
						mineSquare, mineSquare },
				{ mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare,
						mineSquare, mineSquare },
				{ mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare,
						mineSquare, mineSquare },
				{ mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare,
						mineSquare, mineSquare },
				{ mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare,
						mineSquare, mineSquare },
				{ mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare,
						mineSquare, mineSquare },
				{ mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare,
						mineSquare, mineSquare },
				{ mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare,
						mineSquare, mineSquare },
				{ mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare, mineSquare,
						mineSquare, mineSquare } };
		try {
			board = new Board(length, length, percentage);
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(squares, board.getSquares());
		assertEquals(numberOfMines, board.getNumberOfMines());
		assertFalse(board.hasExploded());
		assertEquals(length, board.getNumberOfColumns());
		assertEquals(length, board.getNumberOfRows());

	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board of the minimum length with 0% of mines
	 * <p>
	 * THEN: a board of the minimum length with no mines is created
	 */
	@Test
	void boardWithNoMines() {
		final int length = Board.MINIMUM_HEIGHT;
		final int percentage = 0;
		final int numberOfMines = 0;
		squares = new Square[][] {
				{ emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare,
						emptySquare, emptySquare },
				{ emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare,
						emptySquare, emptySquare },
				{ emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare,
						emptySquare, emptySquare },
				{ emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare,
						emptySquare, emptySquare },
				{ emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare,
						emptySquare, emptySquare },
				{ emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare,
						emptySquare, emptySquare },
				{ emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare,
						emptySquare, emptySquare },
				{ emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare,
						emptySquare, emptySquare },
				{ emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare, emptySquare,
						emptySquare, emptySquare } };
		try {
			board = new Board(length, length, percentage);
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(squares, board.getSquares());
		assertEquals(numberOfMines, board.getNumberOfMines());
		assertFalse(board.hasExploded());
		assertEquals(length, board.getNumberOfColumns());
		assertEquals(length, board.getNumberOfRows());
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board with a negative width
	 * <p>
	 * THEN: an GameException is thrown
	 */
	@Test
	void wrongNegativeWidth() {
		final int width = -1;
		final int height = Board.MINIMUM_HEIGHT;
		final int percentage = 0;
		try {
			board = new Board(width, height, percentage);
		} catch (GameException e) {
			assertEquals(String.format(GameException.MESSAGE_FORMAT, Board.ERROR_MESSAGE_WRONG_WIDTH), e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board with a negative width
	 * <p>
	 * THEN: an GameException is thrown
	 */
	@Test
	void wrongMoreNegativeWidth() {
		final int width = -100;
		final int height = Board.MINIMUM_HEIGHT;
		final int percentage = 0;
		try {
			board = new Board(width, height, percentage);
		} catch (GameException e) {
			assertEquals(String.format(GameException.MESSAGE_FORMAT, Board.ERROR_MESSAGE_WRONG_WIDTH), e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board with a negative height
	 * <p>
	 * THEN: an GameException is thrown
	 */
	@Test
	void wrongNegativeHeight() {
		final int width = Board.MINIMUM_WIDTH;
		final int height = -1;
		final int percentage = 0;
		try {
			board = new Board(width, height, percentage);
		} catch (GameException e) {
			assertEquals(String.format(GameException.MESSAGE_FORMAT, Board.ERROR_MESSAGE_WRONG_HEIGHT), e.getMessage());
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a board with a negative height
	 * <p>
	 * THEN: an GameException is thrown
	 */
	@Test
	void wrongMoreNegativeHeight() {
		final int width = Board.MINIMUM_WIDTH;
		final int height = -100;
		final int percentage = 0;
		try {
			board = new Board(width, height, percentage);
		} catch (GameException e) {
			assertEquals(String.format(GameException.MESSAGE_FORMAT, Board.ERROR_MESSAGE_WRONG_HEIGHT), e.getMessage());
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
		final int width = Board.MINIMUM_WIDTH;
		final int height = Board.MINIMUM_HEIGHT;
		final int percentage = -1;
		try {
			board = new Board(width, height, percentage);
		} catch (IllegalArgumentException e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_PERCENTAGE, e.getMessage());
		} catch (GameException e) {
			fail("No exception should be thrown");
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
		final int width = Board.MINIMUM_WIDTH;
		final int height = Board.MINIMUM_HEIGHT;
		final int percentage = -100;
		try {
			board = new Board(width, height, percentage);
		} catch (IllegalArgumentException e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_PERCENTAGE, e.getMessage());
		} catch (GameException e) {
			fail("No exception should be thrown");
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
		final int width = Board.MINIMUM_WIDTH;
		final int height = Board.MINIMUM_HEIGHT;
		final int percentage = 101;
		try {
			board = new Board(width, height, percentage);
		} catch (IllegalArgumentException e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_PERCENTAGE, e.getMessage());
		} catch (GameException e) {
			fail("No exception should be thrown");
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
		final int width = Board.MINIMUM_WIDTH;
		final int height = Board.MINIMUM_HEIGHT;
		final int percentage = 2000;
		try {
			board = new Board(width, height, percentage);
		} catch (IllegalArgumentException e) {
			assertEquals(Board.ERROR_MESSAGE_WRONG_PERCENTAGE, e.getMessage());
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
	}

}
