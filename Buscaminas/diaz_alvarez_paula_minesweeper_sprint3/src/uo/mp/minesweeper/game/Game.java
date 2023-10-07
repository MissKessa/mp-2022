package uo.mp.minesweeper.game;

import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * It manages the main logic of the game. It is the class in charge of executing
 * the loop of iterations of the game. Only from this class it is possible to
 * interact with the user.
 * 
 * @author Paula Díaz Álvarez
 * @version 2023
 */
public class Game {
	/**
	 * It's the error message shown if a null board is introduced
	 */
	public final static String ERROR_MESSAGE_NULL_BOARD = "The board cannot be null";

	/**
	 * It's the error message shown if a null interactor is introduced
	 */
	public final static String ERROR_MESSAGE_NULL_INTERACTOR = "The interactor cannot be null";

	/**
	 * It's the error message shown if the interactor is null when calling play()
	 */
	public final static String ERROR_MESSAGE_NON_INITIALIZED_INTERACTOR = "The interactor must be initialized before playing the game";

	/**
	 * It's the error message shown if the gameMove is null
	 */
	public final static String ERROR_MESSAGE_NULL_MOVE = "The move must be non null";

	private Board board;
	private GameInteractor interactor;

	/**
	 * It's the main constructor. It creates a game with the given board
	 * 
	 * @param board is the given board. It must be non-null
	 * @throws IllegalArgumentException is the parameter is wrong
	 */
	public Game(Board board) {
		ArgumentChecks.isTrue(board != null, ERROR_MESSAGE_NULL_BOARD);
		this.board = board;
	}

	/**
	 * Method for only testing. It returns the board field
	 * 
	 * @return the board field
	 */
	protected Board getBoard() {
		return this.board;
	}

	/**
	 * Sets the interactor field to the given one
	 * 
	 * @param interactor is the new value of the interactor
	 * @throws IllegalArgumentException if the interactor is null
	 */
	public void setInteractor(GameInteractor interactor) {
		ArgumentChecks.isTrue(interactor != null, ERROR_MESSAGE_NULL_INTERACTOR);
		this.interactor = interactor;
	}

	/**
	 * Initialises the game (messages to the user, timer, etc.). It then executes a
	 * loop in which the user is asked for a move and the corresponding square is
	 * discovered/(un)marked. When it is detected that the user has lost (has
	 * stepped on a mine) or the board is in a winning situation (there are no more
	 * mines to find and mark), the game stops, the board is completely uncovered,
	 * and the user is informed of his victory/defeat.
	 * 
	 * @throws IllegalArgumentException if the interactor hasn't been initiliazed
	 *                                  yet
	 */
	public void play() {
		ArgumentChecks.isTrue(interactor != null, ERROR_MESSAGE_NON_INITIALIZED_INTERACTOR);
		interactor.showReadyToStart();
		this.board.uncoverWelcomeArea();
		long time = System.currentTimeMillis() / 1000;
		long end = 0;

		int maximumRow = this.board.getNumberOfRows() - 1;
		int maximumColumn = this.board.getNumberOfColumns() - 1;

		while (!this.board.hasWin() && !this.board.hasExploded()) {
			end = System.currentTimeMillis() / 1000;
			interactor.showGame(end - time, this.board);
			GameMove move = interactor.askMove(maximumRow, maximumColumn);
			executeMove(move);
		}
		interactor.showGameFinished();

		if (this.board.hasExploded())
			interactor.showBooommm();
		else
			interactor.showCongratulations(end - time);

		this.board.unveil();
		interactor.showGame(end - time, this.board);
	}

	/**
	 * Executes the given move of the user in the square with that x-coordinate and
	 * y-coordinate
	 * 
	 * @param move is the move to execute. It must be 's', 'f' or 'u'
	 * @throws IllegalArgumentExpression if the move is null
	 * 
	 */
	private void executeMove(GameMove move) {
		ArgumentChecks.isTrue(move != null, ERROR_MESSAGE_NULL_MOVE);

		char operation = move.getOperation();
		int xCoordinate = move.getColumn();
		int yCoordinate = move.getRow();

		if (operation == 's')
			this.board.stepOn(xCoordinate, yCoordinate);
		else if (operation == 'f')
			this.board.flag(xCoordinate, yCoordinate);
		else
			this.board.unflag(xCoordinate, yCoordinate);
	}

}
