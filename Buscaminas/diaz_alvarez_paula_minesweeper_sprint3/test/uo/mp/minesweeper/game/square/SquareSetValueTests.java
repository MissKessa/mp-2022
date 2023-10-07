package uo.mp.minesweeper.game.square;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SquareSetValueTests {

	private Square square;

	@BeforeEach
	public void setUp() {
		square = new Square();
	}

	/**
	 * GIVEN: a square
	 * <p>
	 * WHEN: call setValue(Square.EMPTY_SQUARE)
	 * <p>
	 * THEN: The value is set to Square.EMPTY_SQUARE
	 */
	@Test
	void setEmptyMineValue() {
		square.setValue(Square.EMPTY_SQUARE_VALUE);
		assertEquals(Square.EMPTY_SQUARE_VALUE, square.getValue());
	}

	/**
	 * GIVEN: a square
	 * <p>
	 * WHEN: call setValue(Square.SQUARE_WITH_MINE)
	 * <p>
	 * THEN: The value is set to Square.SQUARE_WITH_MINE
	 */
	@Test
	void setMineValue() {
		square.setValue(Square.SQUARE_WITH_MINE_VALUE);
		assertEquals(Square.SQUARE_WITH_MINE_VALUE, square.getValue());
	}

	/**
	 * GIVEN: a square
	 * <p>
	 * WHEN: call setValue(Square.MAXIMUM_MINES_AROUND)
	 * <p>
	 * THEN: The value is set to Square.MAXIMUM_MINES_AROUND
	 */
	@Test
	void setNonEmptyValue() {
		square.setValue(Square.MAXIMUM_MINES_AROUND_VALUE);
		assertEquals(Square.MAXIMUM_MINES_AROUND_VALUE, square.getValue());
	}

	/**
	 * GIVEN: a square
	 * <p>
	 * WHEN: call setValue(Square.SQUARE_WITH_MINE - 1)
	 * <p>
	 * THEN: An IllegalArgumentException is thrown
	 */
	@Test
	void setLowerInvalidValue() {
		try {
			square.setValue(Square.SQUARE_WITH_MINE_VALUE - 1);
			fail();
		} catch (Exception e) {
			assertEquals(Square.ERROR_MESSAGE_WRONG_VALUE, e.getMessage());
		}
	}

	/**
	 * GIVEN: a square
	 * <p>
	 * WHEN: call setValue(-10000)
	 * <p>
	 * THEN: An IllegalArgumentException is thrown
	 */
	@Test
	void setMuchLowerInvalidValue() {
		try {
			square.setValue(-10000);
			fail();
		} catch (Exception e) {
			assertEquals(Square.ERROR_MESSAGE_WRONG_VALUE, e.getMessage());
		}
	}

	/**
	 * GIVEN: a square
	 * <p>
	 * WHEN: call setValue(Square.MAXIMUM_MINES_AROUND + 1)
	 * <p>
	 * THEN: An IllegalArgumentException is thrown
	 */
	@Test
	void setGreaterInvalidValue() {
		try {
			square.setValue(Square.MAXIMUM_MINES_AROUND_VALUE + 1);
			fail();
		} catch (Exception e) {
			assertEquals(Square.ERROR_MESSAGE_WRONG_VALUE, e.getMessage());
		}
	}

	/**
	 * GIVEN: a square
	 * <p>
	 * WHEN: call setValue(Square.MAXIMUM_MINES_AROUND * 1000)
	 * <p>
	 * THEN: An IllegalArgumentException is thrown
	 */
	@Test
	void setMuchGreaterInvalidValue() {
		try {
			square.setValue(Square.MAXIMUM_MINES_AROUND_VALUE * 1000);
			fail();
		} catch (Exception e) {
			assertEquals(Square.ERROR_MESSAGE_WRONG_VALUE, e.getMessage());
		}
	}
}
