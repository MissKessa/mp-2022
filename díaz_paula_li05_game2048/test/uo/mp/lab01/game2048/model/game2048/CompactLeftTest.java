package uo.mp.lab01.game2048.model.game2048;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import uo.mp.lab01.game2048.model.Game2048;
import uo.mp.lab01.game2048.util.ForTesting;

public class CompactLeftTest {
	/**
	 * GIVEN: a matrix with a value in every row of column 0.
	 * <p>
	 * WHEN: a game with that matrix as board is created and left compacted.
	 * <p>
	 * THEN: the board will have all the values on the last column.
	 */
	@Test
	public void oneValueForRowInColumn0() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL11);
		game.compactLeft();
		assertArrayEquals(ForTesting.SEMIFULL1_LEFTCOMPACTED, game.getBoard());

		game = new Game2048(ForTesting.SEMIFULL11_2);
		game.compactLeft();
		assertArrayEquals(ForTesting.SEMIFULL1_LEFTCOMPACTED_2, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every row of column 1.
	 * <p>
	 * WHEN: a game with that matrix as board is created and left compacted.
	 * <p>
	 * THEN: the board will have all the values on the last column.
	 */
	@Test
	public void oneValueForRowInColumn1() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL12);
		game.compactLeft();
		assertArrayEquals(ForTesting.SEMIFULL1_LEFTCOMPACTED, game.getBoard());

		game = new Game2048(ForTesting.SEMIFULL12_2);
		game.compactLeft();
		assertArrayEquals(ForTesting.SEMIFULL1_LEFTCOMPACTED_2, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every row of column 2.
	 * <p>
	 * WHEN: a game with that matrix as board is created and left compacted.
	 * <p>
	 * THEN: the board will have all the values on the last column.
	 */
	@Test
	public void oneValueForRowInColumn2() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL13);
		game.compactLeft();
		assertArrayEquals(ForTesting.SEMIFULL1_LEFTCOMPACTED, game.getBoard());

		game = new Game2048(ForTesting.SEMIFULL13_2);
		game.compactLeft();
		assertArrayEquals(ForTesting.SEMIFULL1_LEFTCOMPACTED_2, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every row of column 1 and 2.
	 * <p>
	 * WHEN: a game with that matrix as board is created and left compacted.
	 * <p>
	 * THEN: the board will have all the values on the 2 last columns.
	 */
	@Test
	public void twoValuesForRowInColumn1And2() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL21);
		game.compactLeft();
		assertArrayEquals(ForTesting.SEMIFULL2_LEFTCOMPACTED, game.getBoard());

		game = new Game2048(ForTesting.SEMIFULL21_2);
		game.compactLeft();
		assertArrayEquals(ForTesting.SEMIFULL2_LEFTCOMPACTED_2, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every row of column 0 and 1.
	 * <p>
	 * WHEN: a game with that matrix as board is created and left compacted.
	 * <p>
	 * THEN: the board will have all the values on the 2 last columns.
	 */
	@Test
	public void twoValuesForRowInColumn0And1() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL22);
		game.compactLeft();
		assertArrayEquals(ForTesting.SEMIFULL2_LEFTCOMPACTED, game.getBoard());

		game = new Game2048(ForTesting.SEMIFULL22_2);
		game.compactLeft();
		assertArrayEquals(ForTesting.SEMIFULL2_LEFTCOMPACTED_2, game.getBoard());
	}

	/**
	 * GIVEN: a matrix with a value in every row of column 0 and 2.
	 * <p>
	 * WHEN: a game with that matrix as board is created and left compacted.
	 * <p>
	 * THEN: the board will have all the values on the 2 last columns.
	 */
	@Test
	public void twoValuesForRowInColumn0And2() {
		Game2048 game = new Game2048(ForTesting.SEMIFULL23);
		game.compactLeft();
		assertArrayEquals(ForTesting.SEMIFULL2_LEFTCOMPACTED, game.getBoard());

		game = new Game2048(ForTesting.SEMIFULL23_2);
		game.compactLeft();
		assertArrayEquals(ForTesting.SEMIFULL2_LEFTCOMPACTED_2, game.getBoard());
	}

	/**
	 * GIVEN: a full matrix.
	 * <p>
	 * WHEN: a game with that matrix as board is created and left compacted.
	 * <p>
	 * THEN: the board will remain the same.
	 */
	@Test
	public void threeValuesForRow() {
		Game2048 game = new Game2048(ForTesting.FULL);
		game.compactLeft();
		assertArrayEquals(ForTesting.FULL, game.getBoard());
	}
}
