package uo.mp.minesweeper.game;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;

public class BoardHasWinTests {
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
	 * WHEN: calling hasWin()
	 * <p>
	 * THEN: returns false
	 */
	@Test
	void coveredBoard() {
		assertFalse(board.hasWin());
	}

	/**
	 * GIVEN: an unveil board with 2 mines, an empty squares and a square with a
	 * value
	 * <p>
	 * WHEN: calling hasWin()
	 * <p>
	 * THEN: the board has exploded because all the mines are opened (it returns
	 * false)
	 */
	@Test
	void uncoveredBoard() {
		board.unveil();
		assertFalse(board.hasWin());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares (open) and a square with a
	 * value (open)
	 * <p>
	 * WHEN: calling hasWin()
	 * <p>
	 * THEN: returns false
	 */
	@Test
	void partialUncoveredBoard() {
		board.stepOn(0, 0);
		board.stepOn(1, 1);
		assertFalse(board.hasWin());
	}

	/**
	 * GIVEN: a board with 2 mines (with one open), an empty squares and a square
	 * with a value
	 * <p>
	 * WHEN: calling hasWin()
	 * <p>
	 * THEN: returns false
	 */
	@Test
	void uncoveredMine() {
		board.stepOn(0, 1);
		assertFalse(board.hasWin());
	}

	/**
	 * GIVEN: a board with 2 mines (both with flags), an empty squares and a square
	 * with a value
	 * <p>
	 * WHEN: calling hasWin()
	 * <p>
	 * THEN: returns true
	 */
	@Test
	void minesWithFlags() {
		board.flag(0, 1);
		board.flag(1, 0);
		assertTrue(board.hasWin());
	}

}
