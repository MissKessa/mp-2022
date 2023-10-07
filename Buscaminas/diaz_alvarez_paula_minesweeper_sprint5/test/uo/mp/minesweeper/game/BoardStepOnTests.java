package uo.mp.minesweeper.game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;
import uo.mp.minesweeper.game.square.actions.BlowUpAction;
import uo.mp.minesweeper.game.square.actions.ClearAction;
import uo.mp.minesweeper.game.square.actions.NullAction;
import uo.mp.minesweeper.util.exceptions.GameException;
import uo.mp.minesweeper.util.exceptions.UserInteractionException;

public class BoardStepOnTests {
	private Board board;
	private Square[][] squares;
	private int numberOfMines;
	private char[][] state;

	@BeforeEach
	public void setUp() {
		Square emptySquare = new Square();
		Square numberSquare = new Square();
		numberSquare.setValue(2);
		Square mineSquare = new Square();
		mineSquare.putMine();
		Square mineSquare2 = new Square();
		mineSquare2.putMine();

		List<Square> neighbours = new ArrayList<>();
		neighbours.add(numberSquare);
		emptySquare.setAction(new ClearAction(neighbours));
		numberSquare.setAction(new NullAction());
		mineSquare.setAction(new BlowUpAction());
		mineSquare2.setAction(new BlowUpAction());

		squares = new Square[][] { { emptySquare, mineSquare }, { mineSquare2, numberSquare } };
		numberOfMines = 2;
		board = new Board(numberOfMines, squares);
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares (open) and a square with a
	 * value
	 * <p>
	 * WHEN: we call stepOn() on the closed empty square
	 * <p>
	 * THEN: it's discovered and the safe neighbours are discovered
	 */
	@Test
	void stepOnClosedEmptyBox() {
		state = new char[][] { { Square.EMPTY_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, '2' } };
		try {
			board.stepOn(0, 0);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());

	}

	/**
	 * GIVEN: a board with 2 mines, an empty square and a square with a value
	 * <p>
	 * WHEN: we call stepOn() on a mine
	 * <p>
	 * THEN: the mine is opened
	 */
	@Test
	void stepOnClosedMineBox() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.MINE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		try {
			board.stepOn(1, 0);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty square and a square with a value
	 * <p>
	 * WHEN: we call stepOn() on the square with the value that is closed
	 * <p>
	 * THEN: the square is opened
	 */
	@Test
	void stepOnClosedNumericalBox() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, '2' } };
		try {
			board.stepOn(1, 1);
		} catch (GameException | UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty squares with flag and a square with a
	 * value
	 * <p>
	 * WHEN: we call stepOn() on the flag empty square
	 * <p>
	 * THEN: nothing happens, (GameException is thrown)
	 */
	@Test
	void stepOnEmptyBoxWithFlag() {
		state = new char[][] { { Square.FLAG_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		try {
			squares[0][0].flag();
		} catch (GameException e) {
		}
		try {
			board.stepOn(0, 0);
			fail("An exception should be thrown");
		} catch (GameException e) {
			assertEquals(String.format(GameException.MESSAGE_FORMAT, Square.ERROR_MESSAGE_STEP_ON_NOT_CLOSED_BOX),
					e.getMessage());
		} catch (UserInteractionException e) {
			fail("No exception should be thrown");
		}

		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines (with a flag), an empty squares and a square with
	 * a value
	 * <p>
	 * WHEN: we call stepOn() on the flag mine square
	 * <p>
	 * THEN: nothing happens, (GameException is thrown)
	 */
	@Test
	void stepOnMineBoxWithFlag() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.FLAG_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		try {
			squares[0][1].flag();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		try {
			board.stepOn(1, 0);
			fail("An exception should be thrown");
		} catch (GameException e) {
			assertEquals(String.format(GameException.MESSAGE_FORMAT, Square.ERROR_MESSAGE_STEP_ON_NOT_CLOSED_BOX),
					e.getMessage());
		} catch (UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty square and a square with a value
	 * <p>
	 * WHEN: we call stepOn() on the square with the value that is flagged
	 * <p>
	 * THEN: nothing happens, (GameException is thrown)
	 */
	@Test
	void stepOnNumericalBoxWithFlag() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.FLAG_SYMBOL } };
		try {
			squares[1][1].flag();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		try {
			board.stepOn(1, 1);
			fail("An exception should be thrown");
		} catch (GameException e) {
			assertEquals(String.format(GameException.MESSAGE_FORMAT, Square.ERROR_MESSAGE_STEP_ON_NOT_CLOSED_BOX),
					e.getMessage());
		} catch (UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines, an open empty square and a square with a value
	 * <p>
	 * WHEN: we call stepOn() on the open empty square
	 * <p>
	 * THEN: nothing happens, (GameException is thrown)
	 */
	@Test
	void stepOnOpenEmptyBox() {
		state = new char[][] { { Square.EMPTY_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		try {
			squares[0][0].open();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		try {
			board.stepOn(0, 0);
			fail("An exception should be thrown");
		} catch (GameException e) {
			assertEquals(String.format(GameException.MESSAGE_FORMAT, Square.ERROR_MESSAGE_STEP_ON_NOT_CLOSED_BOX),
					e.getMessage());
		} catch (UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a board with 2 mines, an empty square and a square with a value
	 * <p>
	 * WHEN: we call stepOn() on the square with the value that is opened
	 * <p>
	 * THEN: nothing happens, (GameException is thrown)
	 */
	@Test
	void stepOnOpenNumericalBox() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, '2' } };
		try {
			squares[1][1].open();
		} catch (GameException e) {
			fail("No exception should be thrown");
		}
		try {
			board.stepOn(1, 1);
			fail("An exception should be thrown");
		} catch (GameException e) {
			assertEquals(String.format(GameException.MESSAGE_FORMAT, Square.ERROR_MESSAGE_STEP_ON_NOT_CLOSED_BOX),
					e.getMessage());
		} catch (UserInteractionException e) {
			fail("No exception should be thrown");
		}
		assertArrayEquals(state, board.getState());
	}
}
