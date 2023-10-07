package uo.mp.minesweeper.game;

import uo.mp.minesweeper.session.SessionInteractor;
import uo.mp.minesweeper.util.check.ArgumentChecks;
import uo.mp.minesweeper.util.exceptions.GameException;
import uo.mp.minesweeper.util.exceptions.UserInteractionException;
import uo.mp.minesweeper.util.log.SimpleLogger;

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
	 * It's the error message shown if a null logger is introduced
	 */
	public final static String ERROR_MESSAGE_NULL_LOGGER = "The logger cannot be null";

	/**
	 * It's the error message shown if the interactor is null when calling play()
	 */
	public final static String ERROR_MESSAGE_NON_INITIALIZED_INTERACTOR = "The game interactor and the session interactor must be initialized before playing the game";
	/**
	 * It's the error message shown if the logger is null when calling play()
	 */
	public final static String ERROR_MESSAGE_NON_INITIALIZED_LOGGER = "The logger must be initialized before playing the game";

	/**
	 * It's the error message shown if the gameMove is null
	 */
	public final static String ERROR_MESSAGE_NULL_MOVE = "The move must be non null";

	private Board board;
	private GameInteractor interactor;
	private SessionInteractor sessionInteractor;
	private SimpleLogger logger;
	private long duration;
	private boolean hasWon;

	/**
	 * It's the main constructor. It creates a game with the given board
	 * 
	 * @param board is the given board. It must be non-null
	 * @throws IllegalArgumentException is the parameter is wrong
	 */
	public Game(Board board) {
		ArgumentChecks.isTrue(board != null, ERROR_MESSAGE_NULL_BOARD);
		this.board = board;
		this.interactor = null;
		this.duration = 0;
		this.hasWon = false;
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
	 * Sets the GameInteractor field to the given one
	 * 
	 * @param interactor is the new value of the interactor
	 * @throws IllegalArgumentException if the interactor is null
	 */
	public void setGameInteractor(GameInteractor interactor) {
		ArgumentChecks.isTrue(interactor != null, ERROR_MESSAGE_NULL_INTERACTOR);
		this.interactor = interactor;
	}

	/**
	 * Sets the SessionInteractor field to the given one
	 * 
	 * @param interactor is the new value of the interactor
	 * @throws IllegalArgumentException if the interactor is null
	 */
	public void setSessionInteractor(SessionInteractor interactor) {
		ArgumentChecks.isTrue(interactor != null, ERROR_MESSAGE_NULL_INTERACTOR);
		this.sessionInteractor = interactor;
	}

	/**
	 * Sets the logger field to the given one
	 * 
	 * @param logger is the new value of the logger
	 * @throws IllegalArgumentException if the logger is null
	 */
	public void setLogger(SimpleLogger logger) {
		ArgumentChecks.isTrue(logger != null, ERROR_MESSAGE_NULL_INTERACTOR);
		this.logger = logger;
	}

	/**
	 * Initialises the game (messages to the user, timer, etc.). It then executes a
	 * loop in which the user is asked for a move and the corresponding square is
	 * discovered/(un)marked. When it is detected that the user has lost (has
	 * stepped on a mine) or the board is in a winning situation (there are no more
	 * mines to find and mark), the game stops, the board is completely uncovered,
	 * and the user is informed of his victory/defeat.
	 * 
	 * @throws IllegalArgumentException if the game interactor, session interactor
	 *                                  or logger hasn't been initiliazed yet
	 */
	public void play() {
		if (interactor == null)
			throw new IllegalStateException(ERROR_MESSAGE_NON_INITIALIZED_INTERACTOR);
		if (sessionInteractor == null)
			throw new IllegalStateException(ERROR_MESSAGE_NON_INITIALIZED_INTERACTOR);
		if (logger == null)
			throw new IllegalStateException(ERROR_MESSAGE_NON_INITIALIZED_LOGGER);

		interactor.showReadyToStart();
		this.board.uncoverWelcomeArea();

		long time = System.currentTimeMillis() / 1000;
		long end = 0;

		while (!this.board.hasWin() && !this.board.hasExploded()) {
			end = System.currentTimeMillis() / 1000;
			interactor.showGame(end - time, this.board);
			askMove();
		}
		this.duration = end - time;
		finishGame();

	}

	/**
	 * It asks for the move and executes it. If any GameException has occur (due to
	 * an user error), it catches and asks again
	 */
	private void askMove() {
		int maximumRow = this.board.getNumberOfRows() - 1;
		int maximumColumn = this.board.getNumberOfColumns() - 1;

		while (true) {
			try {
				GameMove move = interactor.askMove(maximumRow, maximumColumn);
				executeMove(move);
				break;
			} catch (GameException exception) {
				sessionInteractor.showErrorMessage(exception.getMessage());
			} catch (UserInteractionException exception) {
				sessionInteractor.showErrorMessage(exception.getMessage());
				logger.log(exception);
			}
		}
	}

	/**
	 * Shows that the game has finished, and the according messages depending if the
	 * user has win or not. The board is uncovered, and the board is shown with the
	 * duration of the game
	 */
	private void finishGame() {
		interactor.showGameFinished();

		if (this.board.hasExploded()) {
			interactor.showBooommm();
			this.hasWon = false;
		} else {
			interactor.showCongratulations(duration);
			this.hasWon = true;
		}

		this.board.unveil();
		interactor.showGame(duration, this.board);
	}

	/**
	 * Executes the given move of the user in the square with that x-coordinate and
	 * y-coordinate
	 * 
	 * @param move is the move to execute. It must be GameMove.STEP_ON_CHARACTER,
	 *             operation GameMove.FLAG_CHARACTER or GameMove.UNFLAG_CHARACTER'
	 * @throws GameException             if we stepOn when the square was open, flag
	 *                                   when it was flagged or unflagged when it
	 *                                   wasn't flagged
	 * @throws IllegalArgumentExpression if the move is null
	 * 
	 */
	private void executeMove(GameMove move) throws GameException, UserInteractionException {
		ArgumentChecks.isTrue(move != null, ERROR_MESSAGE_NULL_MOVE);

		char operation = move.getOperation();
		int xCoordinate = move.getColumn();
		int yCoordinate = move.getRow();

		if (operation == GameMove.STEP_ON_CHARACTER)
			this.board.stepOn(xCoordinate, yCoordinate);
		else if (operation == GameMove.FLAG_CHARACTER)
			this.board.flag(xCoordinate, yCoordinate);

		else
			this.board.unflag(xCoordinate, yCoordinate);

	}

	/**
	 * @return the time the game lasted.
	 */
	public long getDuration() {
		return this.duration;
	}

	/**
	 * @return if the player has won
	 */
	public boolean hasWon() {
		return this.hasWon;
	}

}
