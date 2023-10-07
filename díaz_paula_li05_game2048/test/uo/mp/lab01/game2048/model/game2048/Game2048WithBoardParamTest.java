package uo.mp.lab01.game2048.model.game2048;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;

import uo.mp.lab01.game2048.model.Game2048;

public class Game2048WithBoardParamTest {
	/**
	 * It's a board with {@value #DEFAULT_BOARD_SIZE} as dimension and it's elements
	 * are zeros
	 */
	public final static int[][] DEFAULT_BOARD = new int[Game2048.DEFAULT_BOARD_SIZE][Game2048.DEFAULT_BOARD_SIZE];
	/**
	 * It's a board with a dimension between the {@value #MINIMUM_DIMENSION} and
	 * {@value #MAXIMUM_DIMENSION}
	 */
	public final static int[][] BOARD_INTERMEDIATE_DIMENSION = { { 2, 4, 8, 16 }, { 0, 0, 0, 0 }, { 32, 64, 128, 256 },
			{ 0, 0, 0, 0 } };
	/**
	 * It's a board with {@value #MINIMUM_DIMENSION} as dimension
	 */
	public final static int[][] BOARD_MIN_DIMENSION = { { 2, 4, 8 }, { 0, 0, 0 }, { 32, 64, 128 } };
	/**
	 * It's a board with {@value #MAXIMUM_DIMENSION} as dimension
	 */
	public final static int[][] BOARD_MAX_DIMENSION = { { 0, 0, 0, 0, 0 }, { 2, 4, 8, 16, 32 }, { 0, 0, 0, 0, 0 },
			{ 16, 32, 64, 128, 256 }, { 0, 0, 0, 0, 0 } };
	/**
	 * It's a board with a dimension less than {@value #MINIMUM_DIMENSION}
	 */
	public final static int[][] BOARD_LESS_DIMENSION = { { 2, 4 }, { 0, 0 } };
	/**
	 * It's a board with a dimension greater than {@value #MAXIMUM_DIMENSION}
	 */
	public final static int[][] BOARD_GREATER_DIMENSION = { { 0, 0, 0, 0, 0, 0 }, { 2, 4, 8, 16, 32, 64 },
			{ 8, 16, 32, 64, 128, 256 }, { 0, 0, 0, 0, 0, 0 }, { 8, 16, 32, 64, 128, 256 }, { 0, 0, 0, 0, 0, 0 } };
	/*
	 * It's a board with elements that are not powers of 2
	 */
	public final static int[][] BOARD_NO_POWER2 = { { 1, 2 }, { 3, 4 } };
	/*
	 * It's a board that is not squared
	 */
	public final static int[][] BOARD_NOT_SQUARED = { { 0, 0, 2 }, { 2, 0 } };

	/**
	 * GIVEN: a matrix with a dimension between the {@value #MINIMUM_DIMENSION} and
	 * {@value #MAXIMUM_DIMENSION}.
	 * <p>
	 * WHEN: a game with that matrix as board is created.
	 * <p>
	 * THEN: no error should be thrown and the board must be that matrix.
	 */
	@Test
	public void betweenLimits() {
		Game2048 game = new Game2048(BOARD_INTERMEDIATE_DIMENSION);
		assertArrayEquals(BOARD_INTERMEDIATE_DIMENSION, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with the {@value #MINIMUM_DIMENSION} dimension.
	 * <p>
	 * WHEN: a game with that matrix as board is created.
	 * <p>
	 * THEN: no error should be thrown and the board must be that matrix.
	 */
	@Test
	public void minDimension() {
		Game2048 game = new Game2048(BOARD_MIN_DIMENSION);
		assertArrayEquals(BOARD_MIN_DIMENSION, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with the {@value #MAXIMUM_DIMENSION} dimension.
	 * <p>
	 * WHEN: a game with that matrix as board is created.
	 * <p>
	 * THEN: no error should be thrown and the board must be that matrix.
	 */
	@Test
	public void maxDimension() {
		Game2048 game = new Game2048(BOARD_MAX_DIMENSION);
		assertArrayEquals(BOARD_MAX_DIMENSION, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a dimension less than {@value #MINIMUM_DIMENSION}.
	 * <p>
	 * WHEN: a game with that matrix as board is created.
	 * <p>
	 * THEN: no error should be thrown and the board must a matrix with all zeros
	 * with {@value #DEFAULT_BOARD_SIZE} dimension.
	 */
	@Test
	public void lessThanMinDimension() {
		Game2048 game = new Game2048(BOARD_LESS_DIMENSION);
		assertArrayEquals(DEFAULT_BOARD, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a dimension higher than {@value #MAXIMUM_DIMENSION}.
	 * <p>
	 * WHEN: a game with that matrix as board is created.
	 * <p>
	 * THEN: no error should be thrown and the board must a matrix with all zeros
	 * with {@value #DEFAULT_BOARD_SIZE} dimension.
	 */
	@Test
	public void MoreThanMaxDimension() {
		Game2048 game = new Game2048(BOARD_GREATER_DIMENSION);
		assertArrayEquals(DEFAULT_BOARD, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a elements that are not power of 2.
	 * <p>
	 * WHEN: a game with that matrix as board is created.
	 * <p>
	 * THEN: no error should be thrown and the board must a matrix with all zeros
	 * with {@value #DEFAULT_BOARD_SIZE} dimension.
	 */
	@Test
	public void noPower2() {
		Game2048 game = new Game2048(BOARD_NO_POWER2);
		assertArrayEquals(DEFAULT_BOARD, game.getBoard());
	}

	/**
	 * GIVEN: a matrix that is not squared.
	 * <p>
	 * WHEN: a game with that matrix as board is created.
	 * <p>
	 * THEN: no error should be thrown and the board must a matrix with all zeros
	 * with {@value #DEFAULT_BOARD_SIZE} dimension.
	 */
	@Test
	public void notSquare() {
		Game2048 game = new Game2048(BOARD_NOT_SQUARED);
		assertArrayEquals(DEFAULT_BOARD, game.getBoard());
	}

	/**
	 * GIVEN: a matrix that is null.
	 * <p>
	 * WHEN: a game with that matrix as board is created.
	 * <p>
	 * THEN: an error should be thrown.
	 */
	@Test
	public void nullMatrix() {
		final int[][] board = null;
		try {
			new Game2048(board);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), Game2048.ERROR_NULL_BOARD);
		}

	}

}
