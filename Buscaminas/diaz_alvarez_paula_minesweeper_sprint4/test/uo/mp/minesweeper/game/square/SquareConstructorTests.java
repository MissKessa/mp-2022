package uo.mp.minesweeper.game.square;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class SquareConstructorTests {

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: we create a square
	 * <p>
	 * THEN: a square is created closed and empty (no value)
	 */
	@Test
	void defaultConstructorTest() {
		final Square square = new Square();
		assertFalse(square.isOpened());
		assertFalse(square.isFlagged());
		assertEquals(Square.EMPTY_SQUARE_VALUE, square.getValue());
	}

}
