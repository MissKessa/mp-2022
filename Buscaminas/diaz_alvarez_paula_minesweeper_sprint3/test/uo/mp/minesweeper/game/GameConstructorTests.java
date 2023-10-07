package uo.mp.minesweeper.game;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameConstructorTests {
	private Board board;

	@BeforeEach
	public void setUp() {
		board = new Board(2, 2, 50);
	}

	/**
	 * GIVEN:
	 * <p>
	 * WHEN: creating a game with a board of 2 by 2 with 50% of mines
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
	 * THEN: an illegal argument exception is thrown
	 */
	@Test
	void invalidBoard() {
		try {
			new Game(null);
			fail();
		} catch (Exception e) {
			assertEquals(Game.ERROR_MESSAGE_NULL_BOARD, e.getMessage());
		}
	}

}
