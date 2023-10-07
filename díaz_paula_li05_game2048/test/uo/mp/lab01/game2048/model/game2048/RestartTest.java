package uo.mp.lab01.game2048.model.game2048;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uo.mp.lab01.game2048.model.Game2048;
import uo.mp.lab01.game2048.util.ForTesting;

public class RestartTest {

	/**
	 * GIVEN: a game with a full board
	 * <p>
	 * WHEN: calling restart()
	 * <p>
	 * THEN: no error should be thrown, a empty board should be generated and then a
	 * 2 should be generated at a random position
	 */
	@Test
	public void restartWhenFull() {
		final int sum = 2;

		Game2048 game = new Game2048(ForTesting.FULL);
		game.restart();
		assertEquals(sum, ForTesting.getSum(game.getBoard()));
	}

	/**
	 * GIVEN: a game with a empty board
	 * <p>
	 * WHEN: calling restart()
	 * <p>
	 * THEN: no error should be thrown, a empty board should be generated and then a
	 * 2 should be generated at a random position
	 */
	@Test
	public void restartWhenEmpty() {
		final int[][] board = new int[Game2048.DEFAULT_BOARD_SIZE][Game2048.DEFAULT_BOARD_SIZE];
		final int sum = 2;

		Game2048 game = new Game2048(board);
		game.restart();
		assertEquals(sum, ForTesting.getSum(game.getBoard()));
	}

	/**
	 * GIVEN: a game with a half full board
	 * <p>
	 * WHEN: calling restart()
	 * <p>
	 * THEN: no error should be thrown, a empty board should be generated and then a
	 * 2 should be generated at a random position
	 */
	@Test
	public void restartWhenSemiFull() {
		final int sum = 2;

		Game2048 game = new Game2048(ForTesting.SEMIFULL31);
		game.restart();
		assertEquals(sum, ForTesting.getSum(game.getBoard()));
	}

}
