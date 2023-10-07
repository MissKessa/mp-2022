package uo.mp.minesweeper.game.square;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.util.exceptions.GameException;

public class SquareEqualsTests {

	private Square square;
	private Square square2;
	private Square square3;
	private Square square4;
	private Square square5;
	private Board board;

	@BeforeEach
	public void setUp() {
		square = new Square();
		square2 = new Square();
		square3 = new Square();
		square3.putMine();
		square4 = new Square();
		try {
			square4.open();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		square5 = new Square();
		square5.putMine();
		try {
			square5.open();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		try {
			board = new Board(Board.MINIMUM_WIDTH, Board.MINIMUM_HEIGHT, 0);
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
	}

	/**
	 * GIVEN: a square with the state CLOSED and empty
	 * <p>
	 * WHEN: call equals() with the same object
	 * <p>
	 * THEN: returns true because is the same object
	 */
	@Test
	void equalsToSameSquare() {
		assertTrue(square.equals(square));
	}

	/**
	 * GIVEN: a square with the state CLOSED and empty
	 * <p>
	 * WHEN: call equals() with a square with the same content
	 * <p>
	 * THEN: returns true because they have the same content
	 */
	@Test
	void equalsToSquareWithSameContent() {
		assertTrue(square.equals(square2));
	}

	/**
	 * GIVEN: a square with the state CLOSED and empty
	 * <p>
	 * WHEN: call equals() with a square with the same state
	 * <p>
	 * THEN: returns false because they have the different content
	 */
	@Test
	void equalsToSquareWithSameState() {
		assertFalse(square.equals(square3));
	}

	/**
	 * GIVEN: a square with the state CLOSED and empty
	 * <p>
	 * WHEN: call equals() with a square with the same value
	 * <p>
	 * THEN: returns false because they have the different content
	 */
	@Test
	void equalsToSquareWithSameValue() {
		assertFalse(square.equals(square4));
	}

	/**
	 * GIVEN: a square with the state CLOSED and empty
	 * <p>
	 * WHEN: call equals() with a square with different content
	 * <p>
	 * THEN: returns false because they have the different content
	 */
	@Test
	void equalsToSquareWithDifferentContent() {
		assertFalse(square.equals(square5));
	}

	/**
	 * GIVEN: a square with the state CLOSED and empty
	 * <p>
	 * WHEN: call equals() with a board
	 * <p>
	 * THEN: returns false because they are different types of objects
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	void equalsToDifferentType() {
		assertFalse(square.equals(board));
	}

	/**
	 * GIVEN: a square with the state CLOSED and empty
	 * <p>
	 * WHEN: call equals() with null
	 * <p>
	 * THEN: returns false because the square is not null
	 */
	@Test
	void equalsToNull() {
		assertFalse(square.equals(null));
	}

}
