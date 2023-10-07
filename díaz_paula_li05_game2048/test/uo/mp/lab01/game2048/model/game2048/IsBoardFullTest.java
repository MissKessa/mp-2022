package uo.mp.lab01.game2048.model.game2048;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import uo.mp.lab01.game2048.model.Game2048;
import uo.mp.lab01.game2048.util.ForTesting;

public class IsBoardFullTest {

	/**
	 * GIVEN: a full matrix
	 * <p>
	 * WHEN: a game with that matrix as board is created and checked if the board is
	 * full.
	 * <p>
	 * THEN: no error should be thrown and the method isBoardFull() should return
	 * true.
	 */
	@Test
	public void fullBoard() {
		Game2048 game = new Game2048(ForTesting.FULL);
		assertTrue(game.isBoardFull()); // da error y a la vez no??
	}

	/**
	 * GIVEN: a half full matrix
	 * <p>
	 * WHEN: a game with that matrix as board is created and checked if the board is
	 * full.
	 * <p>
	 * THEN: no error should be thrown and the method isBoardFull() should return
	 * false.
	 */
	@Test
	public void partiallyFullBoard() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL22);
		assertFalse(game.isBoardFull());
	}

	/**
	 * GIVEN: an empty matrix
	 * <p>
	 * WHEN: a game with that matrix as board is created and checked if the board is
	 * full.
	 * <p>
	 * THEN: no error should be thrown and the method isBoardFull() should return
	 * false.
	 */
	@Test
	public void emptyBoard() {
		final int[][] emptyBoard = new int[Game2048.DEFAULT_BOARD_SIZE][Game2048.DEFAULT_BOARD_SIZE];
		Game2048 game = new Game2048(emptyBoard);
		assertFalse(game.isBoardFull());
	}

}
