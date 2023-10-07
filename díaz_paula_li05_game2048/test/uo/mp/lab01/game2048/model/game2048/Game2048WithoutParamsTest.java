package uo.mp.lab01.game2048.model.game2048;

import static org.junit.Assert.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;

import uo.mp.lab01.game2048.model.Game2048;

public class Game2048WithoutParamsTest {
	/**
	 * GIVEN: No parameters
	 * <p>
	 * WHEN: a game is created with a board.
	 * <p>
	 * THEN: no error should be thrown and the board must be a matrix with all zeros
	 * and and {@value #DEFAULT_BOARD_SIZE} as dimension.
	 */
	@Test
	public void Game2048WithoutParams() {
		final int[][] board = new int[Game2048.DEFAULT_BOARD_SIZE][Game2048.DEFAULT_BOARD_SIZE];

		Game2048 game = new Game2048();
		assertArrayEquals(board, game.getBoard());
	}

}
