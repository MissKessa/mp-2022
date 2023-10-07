package uo.mp.minesweeper.game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.game.square.Square;

public class GameExecuteMoveTests {

	private Board board;
	private Game game;
	private Square[][] squares;
	private char[][] state;
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
		game = new Game(board);
	}

	/**
	 * GIVEN: a game with a board with 2 mines, an empty squares and a square with a
	 * value
	 * <p>
	 * WHEN: we call execute(s,0,0), on the emptySquare
	 * <p>
	 * THEN: the move is executed and the square is opened
	 */
	@Test
	void executeStepOn() {
		state = new char[][] { { Square.EMPTY_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, '2' } };
		game.executeMove("s", 0, 0);
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a game with a board with 2 mines, an empty squares and a square with a
	 * value
	 * <p>
	 * WHEN: we call execute(f,0,0), on the emptySquare
	 * <p>
	 * THEN: the move is executed and the square is flagged
	 */
	@Test
	void executeFlag() {
		state = new char[][] { { Square.FLAG_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		game.executeMove("f", 0, 0);
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a game with a board with 2 mines, an empty square and a square with a
	 * value
	 * <p>
	 * WHEN: we call execute(u,0,0), on the emptySquare (that has a flag)
	 * <p>
	 * THEN: the move is executed and the square is unflagged
	 */
	@Test
	void executeUnflag() {
		state = new char[][] { { Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL },
				{ Square.CLOSED_SQUARE_SYMBOL, Square.CLOSED_SQUARE_SYMBOL } };
		game.executeMove("f", 0, 0);
		game.executeMove("u", 0, 0);
		assertArrayEquals(state, board.getState());
	}

	/**
	 * GIVEN: a game with a board with 2 mines, an empty square and a square with a
	 * value
	 * <p>
	 * WHEN: we call execute() with an invalid move
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void invalidMove() {
		try {
			game.executeMove("a", 0, 0);
		} catch (Exception e) {
			assertEquals(e.getMessage(), Game.ERROR_MESSAGE_WRONG_MOVE);
		}
	}

	/**
	 * GIVEN: a game with a board with 2 mines, an empty square and a square with a
	 * value
	 * <p>
	 * WHEN: we call execute() with a negative xCoordinate
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void invalidNegativeXCoordinate() {
		try {
			game.executeMove("s", -1, 0);
		} catch (Exception e) {
			assertEquals(e.getMessage(), Board.ERROR_MESSAGE_WRONG_X_COORDINATE);
		}
	}

	/**
	 * GIVEN: a game with a board with 2 mines, an empty square and a square with a
	 * value
	 * <p>
	 * WHEN: we call execute() with a xCoordinate greater than or equal to the
	 * number of columns
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void invalidGreaterXCoordinate() {
		try {
			game.executeMove("s", board.getSquares()[0].length, 0);
		} catch (Exception e) {
			assertEquals(e.getMessage(), Board.ERROR_MESSAGE_WRONG_X_COORDINATE);
		}
		;
	}

	/**
	 * GIVEN: a game with a board with 2 mines, an empty square and a square with a
	 * value
	 * <p>
	 * WHEN: we call execute() with a negative yCoordinate
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void invalidNegativeYCoordinate() {
		try {
			game.executeMove("s", 0, -1);
		} catch (Exception e) {
			assertEquals(e.getMessage(), Board.ERROR_MESSAGE_WRONG_Y_COORDINATE);
		}
	}

	/**
	 * GIVEN: a game with a board with 2 mines, an empty square and a square with a
	 * value
	 * <p>
	 * WHEN: we call execute() with a yCoordinate greater than or equal to the
	 * number of rows
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void invalidGreaterYCoordinate() {
		try {
			game.executeMove("s", 0, board.getSquares().length);
		} catch (Exception e) {
			assertEquals(e.getMessage(), Board.ERROR_MESSAGE_WRONG_Y_COORDINATE);
		}
	}

}
