package uo.mp.lab01.game2048.model.game2048;

import static org.junit.Assert.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;

import uo.mp.lab01.game2048.model.Game2048;

public class Game2048WithIntParamTest {

	/**
	 * GIVEN: a dimension between the {@value #MINIMUM_DIMENSION} and
	 * {@value #MAXIMUM_DIMENSION}.
	 * <p>
	 * WHEN: a game is created with a board of that dimension that has as elements
	 * zeros.
	 * <p>
	 * THEN: no error should be thrown and the board must be a matrix with all zeros
	 * and that dimension.
	 */
	@Test
	public void betweenLimits() {
		final int dimension = (Game2048.MAXIMUM_DIMENSION + Game2048.MINIMUM_DIMENSION) / 2;
		final int[][] board = new int[dimension][dimension];

		Game2048 game = new Game2048(dimension);
		assertArrayEquals(board, game.getBoard());

	}

	/**
	 * GIVEN: a dimension equal to {@value #MINIMUM_DIMENSION}.
	 * <p>
	 * WHEN: a game is created with a board of that dimension that has as elements
	 * zeros.
	 * <p>
	 * THEN: no error should be thrown and the board must be a matrix with all zeros
	 * and that dimension.
	 */
	@Test
	public void minDimension() {
		final int dimension = Game2048.MINIMUM_DIMENSION;
		final int[][] board = new int[Game2048.MINIMUM_DIMENSION][Game2048.MINIMUM_DIMENSION];

		Game2048 game = new Game2048(dimension);
		assertArrayEquals(board, game.getBoard());
	}

	/**
	 * GIVEN: a dimension equal to {@value #MAXIMUM_DIMENSION}.
	 * <p>
	 * WHEN: a game is created with a board of that dimension that has as elements
	 * zeros.
	 * <p>
	 * THEN: no error should be thrown and the board must be a matrix with all zeros
	 * and that dimension.
	 */
	@Test
	public void maxDimension() {
		final int dimension = Game2048.MAXIMUM_DIMENSION;
		final int[][] board = new int[Game2048.MAXIMUM_DIMENSION][Game2048.MAXIMUM_DIMENSION];

		Game2048 game = new Game2048(dimension);
		assertArrayEquals(board, game.getBoard());
	}

	/**
	 * GIVEN: a dimension less than {@value #MINIMUM_DIMENSION}.
	 * <p>
	 * WHEN: a game is created with a board of that dimension that has as elements
	 * zeros.
	 * <p>
	 * THEN: no error should be thrown and the board must be a matrix with all zeros
	 * and {@value #DEFAULT_BOARD_SIZE} as dimension.
	 */
	@Test
	public void lessThanMinDimension() {
		final int dimension1 = Game2048.MINIMUM_DIMENSION - 1;
		final int dimension2 = -1 * Game2048.MINIMUM_DIMENSION;
		final int[][] board = new int[Game2048.DEFAULT_BOARD_SIZE][Game2048.DEFAULT_BOARD_SIZE];

		Game2048 game = new Game2048(dimension1);
		assertArrayEquals(board, game.getBoard());

		game = new Game2048(dimension2);
		assertArrayEquals(board, game.getBoard());
	}

	/**
	 * GIVEN: a dimension greater than {@value #MAXIMUM_DIMENSION}.
	 * <p>
	 * WHEN: a game is created with a board of that dimension that has as elements
	 * zeros.
	 * <p>
	 * THEN: no error should be thrown and the board must be a matrix with all zeros
	 * and {@value #DEFAULT_BOARD_SIZE} as dimension.
	 */
	@Test
	public void MoreThanMaxDimension() {
		final int dimension1 = Game2048.MAXIMUM_DIMENSION + 1;
		final int dimension2 = 2 * Game2048.MAXIMUM_DIMENSION;
		final int[][] board = new int[Game2048.DEFAULT_BOARD_SIZE][Game2048.DEFAULT_BOARD_SIZE];

		Game2048 game = new Game2048(dimension1);
		assertArrayEquals(board, game.getBoard());

		game = new Game2048(dimension2);
		assertArrayEquals(board, game.getBoard());
	}

}
