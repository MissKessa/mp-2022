package uo.mp.minesweeper.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GameMoveConstructorTests {
	private GameMove move;

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a GameMove with valid operation, row and column
	 * <p>
	 * THEN: the GameMove is created correctly with those values
	 */
	@Test
	void validParameters() {
		final char operation = 's';
		final int row = 0;
		final int column = 0;
		final String representation = String.format("GameMove [operation=%s, row=%s, column=%s]", operation, row,
				column);

		move = new GameMove(operation, row, column);

		assertEquals(row, move.getRow());
		assertEquals(column, move.getColumn());
		assertEquals(operation, move.getOperation());
		assertEquals(representation, move.toString());
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a GameMove with an invalid operation, and valid row and column
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void invalidOperation() {
		try {
			final char operation = 'a';
			final int row = 0;
			final int column = 0;
			move = new GameMove(operation, row, column);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), GameMove.ERROR_MESSAGE_WRONG_OPERATION);
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a GameMove with a valid operation and column, and invalid row
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void invalidNegativeRow() {
		try {
			final char operation = 's';
			final int row = -1;
			final int column = 0;
			move = new GameMove(operation, row, column);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), GameMove.ERROR_MESSAGE_WRONG_Y_COORDINATE);
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a GameMove with a valid operation and row, and invalid column
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void invalidNegativeColumn() {
		try {
			final char operation = 's';
			final int row = 0;
			final int column = -1;
			move = new GameMove(operation, row, column);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), GameMove.ERROR_MESSAGE_WRONG_X_COORDINATE);
		}
	}

}
