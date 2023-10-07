package uo.mp.lab01.game2048.model.game2048;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

import uo.mp.lab01.game2048.model.Game2048;
import uo.mp.lab01.game2048.util.ForTesting;

class MoveDownTest {
	/**
	 * GIVEN: a matrix with a value in every column of row 0.
	 * <p>
	 * WHEN: a game with that matrix as board is created and move down.
	 * <p>
	 * THEN: the board will have all the values on the last row.
	 */
	@Test
	public void oneValueForColumnInRow0() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL31);
		game.moveDown();
		assertArrayEquals(ForTesting.SEMIFULL3_DOWNMOVED, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every column of row 1.
	 * <p>
	 * WHEN: a game with that matrix as board is created and move down.
	 * <p>
	 * THEN: the board will have all the values on the last row.
	 */
	@Test
	public void oneValueForColumnInRow1() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL32);
		game.moveDown();
		assertArrayEquals(ForTesting.SEMIFULL3_DOWNMOVED, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every column of row 2.
	 * <p>
	 * WHEN: a game with that matrix as board is created and move down.
	 * <p>
	 * THEN: the board will have all the values on the last row.
	 */
	@Test
	public void oneValueForColumnInRow2() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL33);
		game.moveDown();
		assertArrayEquals(ForTesting.SEMIFULL3_DOWNMOVED, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every column of row 0 and 1.
	 * <p>
	 * WHEN: a game with that matrix as board is created and move down.
	 * <p>
	 * THEN: the board will have the values of each column sum and on the last row.
	 */
	@Test
	public void twoValuesForColumnInRow0And1() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL41);
		game.moveDown();
		assertArrayEquals(ForTesting.SEMIFULL4_DOWNMOVED, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every column of row 1 and 2.
	 * <p>
	 * WHEN: a game with that matrix as board is created and move down.
	 * <p>
	 * THEN: the board will have all the values of each column sum and on the last
	 * row.
	 */
	@Test
	public void twoValuesForColumnInRow1And2() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL42);
		game.moveDown();
		assertArrayEquals(ForTesting.SEMIFULL4_DOWNMOVED, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every column of row 0 and 2.
	 * <p>
	 * WHEN: a game with that matrix as board is created and move down.
	 * <p>
	 * THEN: the board will have all the values of each column sum and on the last
	 * row.
	 */
	@Test
	public void twoValuesForColumnInRow0And2() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL43);
		game.moveDown();
		assertArrayEquals(ForTesting.SEMIFULL4_DOWNMOVED, game.getBoard());
	}

	/**
	 * GIVEN: a full matrix.
	 * <p>
	 * WHEN: a game with that matrix as board is created and move down.
	 * <p>
	 * THEN: the board will remain the same.
	 */
	@Test
	public void fullMatrix() {
		Game2048 game = new Game2048(ForTesting.FULL);
		game.moveDown();
		assertArrayEquals(ForTesting.FULL, game.getBoard());
	}
}
