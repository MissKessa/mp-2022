package uo.mp.lab01.game2048.model.game2048;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

import uo.mp.lab01.game2048.model.Game2048;
import uo.mp.lab01.game2048.util.ForTesting;

class MoveRightTest {

	/**
	 * GIVEN: a matrix with a value in every row of column 0.
	 * <p>
	 * WHEN: a game with that matrix as board is created and moved right.
	 * <p>
	 * THEN: the board will have all the values on the last column.
	 */
	@Test
	public void oneValueForRowInColumn0() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL11);
		game.moveRight();
		assertArrayEquals(ForTesting.SEMIFULL1_RIGHTMOVED, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every row of column 1.
	 * <p>
	 * WHEN: a game with that matrix as board is created and moved right.
	 * <p>
	 * THEN: the board will have all the values on the last column.
	 */
	@Test
	public void oneValueForRowInColumn1() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL12);
		game.moveRight();
		assertArrayEquals(ForTesting.SEMIFULL1_RIGHTMOVED, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every row of column 2.
	 * <p>
	 * WHEN: a game with that matrix as board is created and moved right.
	 * <p>
	 * THEN: the board will have all the values on the last column.
	 */
	@Test
	public void oneValueForRowInColumn2() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL13);
		game.moveRight();
		assertArrayEquals(ForTesting.SEMIFULL1_RIGHTMOVED, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every row of column 1 and 2.
	 * <p>
	 * WHEN: a game with that matrix as board is created and moved right.
	 * <p>
	 * THEN: the board will have all the values of each row sum and on the last
	 * column.
	 */
	@Test
	public void twoValuesForRowInColumn1And2() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL21);
		game.moveRight();
		assertArrayEquals(ForTesting.SEMIFULL2_RIGHTMOVED, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every row of column 0 and 1.
	 * <p>
	 * WHEN: a game with that matrix as board is created and moved right.
	 * <p>
	 * THEN: the board will have all the values of each row sum and on the last
	 * column.
	 */
	@Test
	public void twoValuesForRowInColumn0And1() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL22);
		game.moveRight();
		assertArrayEquals(ForTesting.SEMIFULL2_RIGHTMOVED, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every row of column 0 and 2.
	 * <p>
	 * WHEN: a game with that matrix as board is created and moved right.
	 * <p>
	 * THEN: the board will have all the values of each row sum and on the last
	 * column.
	 */
	@Test
	public void twoValuesForRowInColumn0And2() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL23);
		game.moveRight();
		assertArrayEquals(ForTesting.SEMIFULL2_RIGHTMOVED, game.getBoard());
	}

	/**
	 * GIVEN: a full matrix.
	 * <p>
	 * WHEN: a game with that matrix as board is created and moved right.
	 * <p>
	 * THEN: the board will remain the same.
	 */
	@Test
	public void threeValuesForRow() {
		Game2048 game = new Game2048(ForTesting.FULL);
		game.moveRight();
		assertArrayEquals(ForTesting.FULL, game.getBoard());
	}

}
