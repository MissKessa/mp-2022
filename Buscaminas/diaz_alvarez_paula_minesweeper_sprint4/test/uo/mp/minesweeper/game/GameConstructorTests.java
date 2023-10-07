package uo.mp.minesweeper.game;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.util.exceptions.GameException;

public class GameConstructorTests {
	private Board board;

	@BeforeEach
	public void setUp() {
		try {
			board = new Board(Board.MINIMUM_WIDTH, Board.MINIMUM_HEIGHT, 50);
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a game with a board of MINIMUM_WIDTH by MINIMUM_HEIGHT with
	 * 50% of mines
	 * <p>
	 * THEN: the game is created with that board
	 */
	@Test
	void validBoard() {
		Game game = new Game(board);
		assertEquals(board, game.getBoard());
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a game with a null board
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void invalidBoard() {
		try {
			new Game(null);
			fail("An exception should be thrown");
		} catch (IllegalArgumentException e) {
			assertEquals(Game.ERROR_MESSAGE_NULL_BOARD, e.getMessage());
		}
	}

}
