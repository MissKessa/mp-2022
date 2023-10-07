package uo.mp.minesweeper.session;

import java.util.List;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Game;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.ranking.GameRanking;
import uo.mp.minesweeper.ranking.GameRankingEntry;
import uo.mp.minesweeper.util.check.ArgumentChecks;
import uo.mp.minesweeper.util.exceptions.GameException;
import uo.mp.minesweeper.util.exceptions.UserInteractionException;
import uo.mp.minesweeper.util.log.SimpleLogger;

/**
 * The GameSession class will be in charge of carrying the weight of all session
 * logic and launching the game when necessary. The main public method of
 * GameSession is run() which is responsible for launching all session logic:
 * <p>
 * -A user name is requested when starting the application
 * <p>
 * -Starts the main menu loop to collect and process user commands
 * <p>
 * a) When the user selects to play a game it asks for the difficulty level and
 * starts the game. At the end of the game it keeps its results if the user so
 * indicates.
 * <p>
 * b) Displays scores to the user on request
 * <p>
 * -Catches possible RuntimeException to produce an appropriate message to the
 * user and terminate execution in an orderly fashion.
 * 
 * Each time you finish processing an option, the menu will be shown again,
 * unless you choose to exit, in which case the session and the execution of the
 * program will end. This means that in the same session you can play several
 * games and you can consult the scores as many times as you want without having
 * to run the program several times.
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class GameSession {
	/**
	 * It's the error message shown if the game is null
	 */
	private static final String ERROR_MESSAGE_THE_GAME_CANNOT_BE_NULL = "The game cannot be null";
	/**
	 * It's the error message shown if the level is null
	 */
	private static final String ERROR_MESSAGE_THE_LEVEL_CANNOT_BE_NULL = "The level cannot be null";
	/**
	 * It's the error message shown if the userName is blank
	 */
	private static final String ERROR_MESSAGE_THE_USER_NAME_CANNOT_BE_BLANK = "The userName cannot be blank";
	/**
	 * It's the error message shown if the userName is empty
	 */
	private static final String ERROR_MESSAGE_THE_USER_NAME_CANNOT_BE_EMPTY = "The userName cannot be empty";
	/**
	 * It's the error message shown if the userName is null
	 */
	private static final String ERROR_MESSAGE_THE_USERNAME_CANNOT_BE_NULL = "The username cannot be null";
	/**
	 * It's the error message shown if the game wasn't created due to an error
	 */
	private static final String ERROR_MESSAGE_ERROR_CREATING_GAME = "Please choose another level.";
	/**
	 * It's the error message shown if the userName has already been entered
	 */
	private static final String ERROR_MESSAGE_THE_USERNAME_HAS_ALREADY_BEING_ENTERED = "The username has already being entered";
	/**
	 * It's the error message shown if the ranking is null
	 */
	private static final String ERROR_MESSAGE_THE_RANKING_CANNOT_BE_NULL = "The ranking cannot be null";
	/**
	 * It's the error message shown if the logger is null
	 */
	private static final String ERROR_MESSAGE_THE_LOGGER_CANNOT_BE_NULL = "The logger cannot be null";
	/**
	 * It's the error message shown if the game interactor is null
	 */
	private static final String ERROR_MESSAGE_THE_GAME_INTERACTOR_CANNOT_BE_NULL = "The game interactor cannot be null";
	/**
	 * It's the error message shown if the session interactor is null
	 */
	private static final String ERROR_MESSAGE_THE_SESSION_INTERACTOR_CANNOT_BE_NULL = "The session interactor cannot be null";
	/**
	 * It's the value for the exit option
	 */
	public final static int EXIT_OPTION = SessionInteractor.EXIT_OPTION;
	/**
	 * It's the value for the play option
	 */
	public final static int PLAY_OPTION = SessionInteractor.PLAY_OPTION;
	/**
	 * It's the value for the show my results option
	 */
	public final static int SHOW_MY_RESULTS_OPTION = SessionInteractor.SHOW_MY_RESULTS_OPTION;
	/**
	 * It's the value for the show all results option
	 */
	public final static int SHOW_ALL_RESULTS_OPTION = SessionInteractor.SHOW_ALL_RESULTS_OPTION;
	/**
	 * It's the value for the export results option
	 */
	public final static int EXPORT_RESULTS_OPTION = SessionInteractor.EXPORT_RESULTS_OPTION;
	/**
	 * It's the value for the import results option
	 */
	public final static int IMPORT_RESULTS_OPTION = SessionInteractor.IMPORT_RESULTS_OPTION;

	private GameInteractor gameInteractor;
	private SessionInteractor sessionInteractor;
	private SimpleLogger logger;
	private GameRanking ranking;

	/**
	 * Causes GameSession to use the SessionInteractor object received as a
	 * parameter
	 * 
	 * @param consoleSessionInteractor is the given sessionInteractor
	 * @throws IllegalArgumentException if the consoleSessionInteractor is null
	 */
	public void setSessionInteractor(SessionInteractor consoleSessionInteractor) {
		ArgumentChecks.notNull(consoleSessionInteractor, ERROR_MESSAGE_THE_SESSION_INTERACTOR_CANNOT_BE_NULL);
		this.sessionInteractor = consoleSessionInteractor;

	}

	/**
	 * Causes GameSession to use the GameInteractor object received as a parameter
	 * 
	 * @param consoleGameInteractor is the given gameInteractor
	 * @throws IllegalArgumentException if the consoleGameInteractor is null
	 */
	public void setGameInteractor(GameInteractor consoleGameInteractor) {
		ArgumentChecks.notNull(consoleGameInteractor, ERROR_MESSAGE_THE_GAME_INTERACTOR_CANNOT_BE_NULL);
		this.gameInteractor = consoleGameInteractor;
	}

	/**
	 * Causes GameSession to use the SimpleLogger object received as a parameter.
	 * 
	 * @param logger is the given SimpleLogger
	 * @throws IllegalArgumentException if the logger is null
	 */
	public void setLogger(SimpleLogger logger) {
		ArgumentChecks.notNull(logger, ERROR_MESSAGE_THE_LOGGER_CANNOT_BE_NULL);
		this.logger = logger;
		if (ranking != null) {
			this.ranking.setLogger(this.logger);
		}

	}

	/**
	 * Stores the object received as a parameter as a ranking.
	 * 
	 * @param gameRanking is the given ranking
	 * @throws IllegalArgumentException if the gameRanking is null
	 */
	public void setGameRanking(GameRanking gameRanking) {
		ArgumentChecks.notNull(gameRanking, ERROR_MESSAGE_THE_RANKING_CANNOT_BE_NULL);
		this.ranking = gameRanking;
		if (logger != null) {
			this.ranking.setLogger(logger);
		}
	}

	/**
	 * Starts all the main GameSession logic:
	 * <p>
	 * -A user name is requested when starting the application
	 * <p>
	 * -Starts the main menu loop to collect and process user commands
	 * <p>
	 * a) When the user selects to play a game it asks for the difficulty level and
	 * starts the game. At the end of the game it keeps its results if the user so
	 * indicates.
	 * <p>
	 * b) Displays scores to the user on request
	 * <p>
	 * -Catches possible RuntimeException to produce an appropriate message to the
	 * user and terminate execution in an orderly fashion.
	 */
	public void run() {
		try {
			String userName = askUserName();
			executeOption(userName);
		} catch (RuntimeException e) {
			sessionInteractor.showFatalErrorMessage(e.getMessage());
			logger.log(e);
		}

	}

	/**
	 * Asks for the userName and checks if it's a valid userName (is not in the
	 * ranking, and it's in lowerCase). If it's incorrect, the error message is
	 * shown and asks again for the userName
	 * 
	 * @return the userName for the player
	 */
	private String askUserName() {
		String userName = "";
		try {
			userName = sessionInteractor.askUserName();
			checkUsernameIsNotInTheRanking(userName);
			return userName;

		} catch (GameException exception) {
			sessionInteractor.showErrorMessage(exception.getMessage());
			return askUserName();
		}

	}

	/**
	 * Checks if the userName is in the ranking or not
	 * 
	 * @param userName is the given userName to check if it's valid
	 * @throws GameException if the userName is already in the ranking
	 */
	private void checkUsernameIsNotInTheRanking(String userName) throws GameException {
		List<GameRankingEntry> rankings = this.ranking.getAllEntries();
		if (rankings != null) {
			for (GameRankingEntry entry : rankings) {
				if (entry.getUserName().equals(userName))
					throw new GameException(ERROR_MESSAGE_THE_USERNAME_HAS_ALREADY_BEING_ENTERED);
			}
		}
	}

	/**
	 * It executes an option depending of which one the user selects
	 * 
	 * @param userName is the given userName of the player, in case he wants to get
	 *                 his entries
	 */
	private void executeOption(String userName) {
		argumentChecksUsername(userName);

		boolean wantToExit = false;

		while (!wantToExit) {
			try {
				int option = askOption();

				switch (option) {
				case EXIT_OPTION:
					wantToExit = true;
					sessionInteractor.showGoodBye();
					break;
				case PLAY_OPTION:
					play(userName);
					break;
				case SHOW_MY_RESULTS_OPTION:
					sessionInteractor.showPersonalRanking(ranking.getEntriesForUsername(userName));
					break;
				case SHOW_ALL_RESULTS_OPTION:
					sessionInteractor.showRanking(ranking.getAllEntries());
					break;
				case EXPORT_RESULTS_OPTION:
					exportResults();
					break;
				case IMPORT_RESULTS_OPTION:
					importResults();
					break;
				}
			} catch (UserInteractionException e) {
				sessionInteractor.showErrorMessage(e.getMessage());
			}
		}
	}

	/**
	 * Imports the results from a file
	 * 
	 * @throws UserInteractionException if the file wasn't found
	 */
	private void importResults() throws UserInteractionException {
		String fileName = askFileName();
		ranking.importEntries(fileName);

	}

	/**
	 * Exports the results from a file
	 */
	private void exportResults() {
		String fileName = askFileName();
		ranking.exportEntries(fileName);
	}

	/**
	 * Asks for the fileName when exporting or importing until it's correct
	 * 
	 * @return a valid fileName
	 */
	private String askFileName() {
		String fileName = "";
		try {
			fileName = sessionInteractor.askFileName();
			return fileName;
		} catch (GameException exception) {
			sessionInteractor.showErrorMessage(exception.getMessage());
			return askUserName();
		}
	}

	/**
	 * It asks for an option until it's correct. If not the error is shown and
	 * logged, and keeps asking
	 * 
	 * @return the option selected
	 */
	private int askOption() {
		int option;
		while (true) {
			try {
				option = sessionInteractor.askNextOption();
				return option;

			} catch (UserInteractionException exception) {
				sessionInteractor.showErrorMessage(exception.getMessage());
				logger.log(exception);
			}
		}
	}

	/**
	 * Ask the game level, creates a game according to the level selected, it sets
	 * the gameInteractor, sessionInteractor and logger of the game, the user play
	 * the game and save entry in case it won
	 * 
	 * @param userName is the userName of the user, in case it saves the entry
	 */
	private void play(String userName) {
		argumentChecksUsername(userName);

		GameLevel level = askGameLevel();
		Game game;
		while (true) {
			try {
				game = createGameAccordingToLevel(level);
				game.setGameInteractor(gameInteractor);
				game.setSessionInteractor(sessionInteractor);
				game.setLogger(logger);
				game.play();
				saveEntry(userName, level, game);
				break;
			} catch (GameException e) {
				sessionInteractor.showErrorMessage(ERROR_MESSAGE_ERROR_CREATING_GAME + e.getMessage());
			}
		}

	}

	/**
	 * Asks the game level until it's correct. If not the error is shown and logged,
	 * and keeps asking
	 * 
	 * @return the game level
	 */
	private GameLevel askGameLevel() {
		while (true) {
			try {
				GameLevel level = sessionInteractor.askGameLevel();
				return level;

			} catch (UserInteractionException exception) {
				sessionInteractor.showErrorMessage(exception.getMessage());
				logger.log(exception);
			}
		}
	}

	/**
	 * Checks if the given userName is null, empty or blank
	 * 
	 * @param userName is the given userName to check
	 * @throws IllegalArgumentException if the userName is null, empty or blank
	 */
	private void argumentChecksUsername(String userName) {
		ArgumentChecks.notNull(userName, ERROR_MESSAGE_THE_USERNAME_CANNOT_BE_NULL);
		ArgumentChecks.notEmpty(userName, ERROR_MESSAGE_THE_USER_NAME_CANNOT_BE_EMPTY);
		ArgumentChecks.notBlank(userName, ERROR_MESSAGE_THE_USER_NAME_CANNOT_BE_BLANK);
	}

	/**
	 * It asks if the user wants to save the entry of the game, and saves it if it
	 * say yes and it has won
	 * 
	 * @param userName is the userName of the player
	 * @param level    is the level of the game played
	 * @param game     is the game played
	 * @throws IllegalArgumentException if the level or the game are null
	 */
	private void saveEntry(String userName, GameLevel level, Game game) {
		argumentChecksUsername(userName);
		ArgumentChecks.notNull(level, ERROR_MESSAGE_THE_LEVEL_CANNOT_BE_NULL);
		ArgumentChecks.notNull(game, ERROR_MESSAGE_THE_GAME_CANNOT_BE_NULL);

		long duration = game.getDuration();
		boolean hasWon = game.hasWon();

		if (hasWon) {
			while (true) {
				try {
					if (sessionInteractor.doYouWantToRegisterYourScore()) {
						GameRankingEntry entry = new GameRankingEntry(userName, level, duration, hasWon);
						ranking.append(entry);
					}
					break;
				} catch (UserInteractionException exception) {
					sessionInteractor.showErrorMessage(exception.getMessage());
					logger.log(exception);
				}
			}
		}
	}

	/**
	 * It creates a game according to the given game level
	 * 
	 * @param level is the given game level
	 * @return the game created according to the given game level
	 * @throws GameException
	 * @throws IllegalArgumentException if the level is null
	 */
	private Game createGameAccordingToLevel(GameLevel level) throws GameException {
		ArgumentChecks.notNull(level, ERROR_MESSAGE_THE_LEVEL_CANNOT_BE_NULL);
		if (level == GameLevel.EASY)
			return createEasyGame();
		else if (level == GameLevel.MEDIUM)
			return createMediumGame();
		else
			return createHardGame();
	}

	/**
	 * It creates an easy game (9 by 9 with 12% of mines)
	 * 
	 * @return the game with that parameters
	 * @throws GameException if the game wans't created
	 */
	private Game createEasyGame() throws GameException {
		Board board = new Board(9, 9, 12 /* % */);
		return new Game(board);
	}

	/**
	 * It creates a medium game (16 by 16 with 15% of mines)
	 * 
	 * @return the game with that parameters
	 * @throws GameException if the game wans't created
	 */
	private Game createMediumGame() throws GameException {
		Board board = new Board(16, 16, 15 /* % */);
		return new Game(board);
	}

	/**
	 * It creates a hard game (30 by 16 with 20% of mines)
	 * 
	 * @return the game with that parameters
	 * @throws GameException if the game wans't created
	 */
	private Game createHardGame() throws GameException {
		Board board = new Board(30, 16, 20 /* % */);
		return new Game(board);
	}

}
