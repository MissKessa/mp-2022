package uo.mp.lab01.game2048.model.game2048;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import uo.mp.lab01.game2048.model.Game2048;
import uo.mp.lab01.game2048.util.ForTesting;

public class NextTest {

	/**
	 * GIVEN: a game with an empty board
	 * <p>
	 * WHEN: calling next()
	 * <p>
	 * THEN: no error should be thrown, the method next() should return true and a 2
	 * should be generated at a random position
	 */
	@Test
	public void emptyBoard() {
		final String board = "0 0 0 \n0 0 0 \n0 0 0 \n";
		final int sum = 2;

		Game2048 game = new Game2048();
		assertTrue(game.next());
		assertNotEquals(board, game.toString());
		assertEquals(sum, ForTesting.getSum(game.getBoard()));
	}

	/**
	 * GIVEN: a game with a half full board
	 * <p>
	 * WHEN: calling next()
	 * <p>
	 * THEN: no error should be thrown, the method next() should return true and a 2
	 * should be generated at a random position
	 */
	@Test
	public void semiEmptyBoard() {
		final int[][] board = { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 0 } };
		final int[][] full_board = { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } };
		final int sum = 18;

		Game2048 game = new Game2048(board);
		assertTrue(game.next());
		assertArrayEquals(full_board, game.getBoard());
		assertEquals(sum, ForTesting.getSum(game.getBoard()));
	}

	/**
	 * GIVEN: a game with a full board
	 * <p>
	 * WHEN: calling next()
	 * <p>
	 * THEN: no error should be thrown, the method next() should return false
	 */
	@Test
	public void fullBoard() {
		final int[][] full_board = { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } };

		Game2048 game = new Game2048(full_board);
		assertFalse(game.next());
		assertArrayEquals(full_board, game.getBoard());
	}

}
