package uo.mp.minesweeper.game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;

public class BoardHasExplodedTests {
	private Board board;
	private Square[][] squares;
	private int numberOfMines;

	@BeforeEach
	public void setUp() {
		Square emptySquare = new Square();
		Square numberSquare = new Square();
		numberSquare.setValue(2);
		Square mineSquare = new Square();
		mineSquare.putMine();
		Square mineSquare2 = new Square();
		mineSquare2.putMine();

		squares = new Square[][] { { emptySquare, mineSquare }, { mineSquare2, numberSquare } };
		numberOfMines = 2;
		board = new Board(numberOfMines, squares);
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares and a square with a value
	 * <p>
	 * WHEN: we call markAsExploded() and hasExploded()
	 * <p>
	 * THEN: the board hasn't exploded (it returns false)
	 */
	@Test
	void coveredBoard() {
		board.markAsExploded();
		assertFalse(board.hasExploded());
	}

	/**
	 * GIVEN: an unveil board with 2 mines, an empty squares and a square with a
	 * value
	 * <p>
	 * WHEN: we call markAsExploded() and hasExploded()
	 * <p>
	 * THEN: the board has exploded because all the mines are opened (it returns
	 * true)
	 */
	@Test
	void uncoveredBoard() {
		board.unveil();
		board.markAsExploded();
		assertTrue(board.hasExploded());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares (open) and a square with a
	 * value (open)
	 * <p>
	 * WHEN: we call markAsExploded() and hasExploded()
	 * <p>
	 * THEN: the board hasn't exploded (it returns false)
	 */
	@Test
	void partialUncoveredBoard() {
		board.stepOn(0, 0);
		board.stepOn(1, 1);
		board.markAsExploded();
		assertFalse(board.hasExploded());
	}

	/**
	 * GIVEN: a board with 2 mines (with one open), an empty squares and a square
	 * with a value
	 * <p>
	 * WHEN: we call markAsExploded() and hasExploded()
	 * <p>
	 * THEN: the board has exploded (it returns true)
	 */
	@Test
	void uncoveredMine() {
		board.stepOn(0, 1);
		board.markAsExploded();
		assertTrue(board.hasExploded());
	}

	/**
	 * GIVEN: a board with 2 mines (both with flags), an empty squares and a square
	 * with a value
	 * <p>
	 * WHEN: we call markAsExploded() and hasExploded()
	 * <p>
	 * THEN: the board hasn't exploded (it returns false)
	 */
	@Test
	void minesWithFlags() {
		board.flag(0, 1);
		board.flag(1, 0);
		board.markAsExploded();
		assertFalse(board.hasExploded());
	}
}
