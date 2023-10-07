package uo.mp.minesweeper.game.square;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.util.exceptions.GameException;

public class SquareToStringTests {

	private Square square;

	@BeforeEach
	public void setUp() {
		square = new Square();
	}

	/**
	 * GIVEN: a square with the state CLOSED
	 * <p>
	 * WHEN: call toString()
	 * <p>
	 * THEN: Returns CLOSED_SQUARE_SYMBOL
	 */
	@Test
	void toStringClosedBox() {
		final String symbol = "" + Square.CLOSED_SQUARE_SYMBOL;
		assertFalse(square.isOpened());
		assertFalse(square.isFlagged());

		assertEquals(symbol, square.toString());
	}

	/**
	 * GIVEN: a square with the state FLAGGED
	 * <p>
	 * WHEN: call toString()
	 * <p>
	 * THEN: Returns FLAG_SYMBOL
	 */
	@Test
	void toStringFlaggedBox() {
		final String symbol = "" + Square.FLAG_SYMBOL;
		try {
			square.flag();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		assertTrue(square.isFlagged());

		assertEquals(symbol, square.toString());
	}

	/**
	 * GIVEN: a square with the state OPENED and SQUARE_WITH_MINE as value
	 * <p>
	 * WHEN: call toString()
	 * <p>
	 * THEN: Returns MINE_SYMBOL
	 */
	@Test
	void toStringOpenedBoxWithMine() {
		final String symbol = "" + Square.MINE_SYMBOL;
		try {
			square.open();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		square.setValue(Square.SQUARE_WITH_MINE_VALUE);
		assertTrue(square.isOpened());

		assertEquals(symbol, square.toString());
	}

	/**
	 * GIVEN: a square with the state OPENED and EMPTY_SQUARE as value
	 * <p>
	 * WHEN: call toString()
	 * <p>
	 * THEN: Returns EMPTY_SQUARE_SYMBOL
	 */
	@Test
	void toStringOpenedEmptyBox() {
		final String symbol = "" + Square.EMPTY_SQUARE_SYMBOL;
		try {
			square.open();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		square.setValue(Square.EMPTY_SQUARE_VALUE);
		assertTrue(square.isOpened());

		assertEquals(symbol, square.toString());
	}

	/**
	 * GIVEN: a square with the state OPENED and a value equal to
	 * MAXIMUM_MINES_AROUND
	 * <p>
	 * WHEN: call toString()
	 * <p>
	 * THEN: Returns a String with that value
	 */
	@Test
	void toStringOpenedNonEmptyBox() {
		final String symbol = "" + Square.MAXIMUM_MINES_AROUND_VALUE;
		try {
			square.open();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		square.setValue(Square.MAXIMUM_MINES_AROUND_VALUE);
		assertFalse(square.hasMine());
		assertTrue(square.isOpened());

		assertEquals(symbol, square.toString());
	}
}
