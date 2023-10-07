package uo.mp.minesweeper.console;

import java.util.Collections;
import java.util.List;

import uo.mp.minesweeper.ranking.GameRankingEntry;
import uo.mp.minesweeper.ranking.comparator.GameRankingEntryComparator;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.session.SessionInteractor;
import uo.mp.minesweeper.util.check.ArgumentChecks;
import uo.mp.minesweeper.util.console.Console;
import uo.mp.minesweeper.util.exceptions.GameException;
import uo.mp.minesweeper.util.exceptions.UserInteractionException;

/**
 * The ConsoleSessionInteractor class implements all methods of the
 * SessionInteractor interface to work with the Java console. In text mode, all
 * methods that request certain information from the user are preceded by the
 * relevant question
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class ConsoleSessionInteractor implements SessionInteractor {
	/**
	 * It's the message shown if a fatal error occurs
	 */
	private static final String MESSAGE_FATAL_ERROR = "A FATAL error has occurr, the execution will stop. Please contact the service provider. Error: %s";
	/**
	 * It's the message shown if a recoverable error occurs
	 */
	private static final String MESSAGE_RECOVERABLE_ERROR = "A recoverable error has occurr, please try again. %s";
	/**
	 * It's the error message shown if the filename is blank
	 */
	private static final String ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_BLANK = "The FileName cannot be blank";
	/**
	 * It's the error message shown if the filename is empty
	 */
	private static final String ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_EMPTY = "The FileName cannot be empty";
	/**
	 * It's the message to ask the filename
	 */
	private static final String ASK_FILENAME = "FileName?";
	/**
	 * It's the error message shown if the error message for an exception is empty
	 */
	private static final String ERROR_MESSAGE_THE_ERROR_MESSAGE_CANNOT_BE_EMPTY = "The error message cannot be empty";
	/**
	 * It's the error message shown if the error message for an exception is blank
	 */
	private static final String ERROR_MESSAGE_THE_ERROR_MESSAGE_CANNOT_BE_BLANK = "The error message cannot be blank";
	/**
	 * It's the error message shown if the error message for an exception is null
	 */
	private static final String ERROR_MESSAGE_THE_ERROR_MESSAGE_CANNOT_BE_NULL = "The error message cannot be null";
	/**
	 * It's the message shown as goodbye message
	 */
	private static final String GOODBYE_MESSAGE = "Thanks for your session. Bye, bye!";
	/**
	 * It's the format of the header when all the rankings are displayed
	 */
	private static final String SHOW_ALL_RANKINGS_FORMAT = ".User\t.Date\t.Hour\t.Level\t.Res\t.Time";
	/**
	 * It's the format of the header when the personal rankings are displayed
	 */
	private static final String SHOW_PERSONAL_RANKING_FORMAT = ".Date\t.Hour\t.Level\t.Res\t.Time";
	/**
	 * It's the error message shown when the list of rankings has null elements
	 */
	private static final String ERROR_MESSAGE_THE_LIST_OF_RANKINGS_CANNOT_HAVE_NULL_ELEMENTS = "The list of rankings cannot have null elements";
	/**
	 * It's the message shown when there are no rankings yet
	 */
	private static final String THERE_ARE_NO_RANKINGS_YET = "There are no rankings yet";
	/**
	 * It's the error message shown when answer requested is not a character
	 */
	private static final String ERROR_MESSAGE_THE_ANSWER_MUST_BE_A_CHARACTER = "The answer must be a character";
	/**
	 * It's the message shown to ask if the user wants to save their score
	 */
	private static final String ASK_TO_SAVE_SCORE = "Do you want to store your score? (y)es, (n)o";
	/**
	 * It's the error message shown when the option requested is not one of the
	 * available
	 */
	private static final String ERROR_MESSAGE_THE_OPTION_MUST_BE_ONE_OF_THE_AVAILABLE = "The option must be one of the available";
	/**
	 * It's the error message shown when the option requested is not a number
	 */
	private static final String ERROR_MESSAGE_THE_OPTION_MUST_BE_A_NUMBER = "The option must be a number";
	/**
	 * It's the message shown to ask for the option in the menu
	 */
	private static final String ASK_OPTION = "Option?";
	/**
	 * It's the error message shown when the userName requested is not in lowerCase
	 */
	private static final String ERROR_MESSAGE_THE_USERNAME_MUST_BE_IN_LOWERCASE = "The username must be in lowercase";
	/**
	 * It's the error message shown when the userName requested is blank
	 */
	private static final String ERROR_MESSAGE_THE_USERNAME_CANNOT_BE_BLANK = "The userName cannot be blank";
	/**
	 * It's the error message shown when the userName requested is empty
	 */
	private static final String ERROR_MESSAGE_THE_USERNAME_CANNOT_BE_EMPTY = "The userName cannot be empty";
	/**
	 * It's the message shown to ask for the userName
	 */
	private static final String ASK_USERNAME = "Player name?";
	/**
	 * It's the error message shown when the level is not a character
	 */
	private static final String ERROR_MESSAGE_THE_LEVEL_MUST_BE_A_CHARACTER = "The level must be a character";
	/**
	 * It's the message shown to ask for the level
	 */
	private static final String ASK_LEVEL = "Level? (e)asy, (m)edium, (h)igh";
	/**
	 * It's the character representing the GameLevel.EASY
	 */
	public final static char EASY_LEVEL = 'e';
	/**
	 * It's the character representing the GameLevel.MEDIUM
	 */
	public final static char MEDIUM_LEVEL = 'm';
	/**
	 * It's the character representing the GameLevel.HARD
	 */
	public final static char HARD_LEVEL = 'h';
	/**
	 * It's the error message shown when the level is not one of the options
	 */
	private static final String ERROR_MESSAGE_INVALID_LEVEL = String.format("The level must be '%s', '%s' or '%s'",
			EASY_LEVEL, MEDIUM_LEVEL, HARD_LEVEL);

	/**
	 * It's the character representing if the user wants to save their entry
	 */
	public final static char SAVE_ENTRY_CHARACTER = 'y';

	/**
	 * It's the character representing if the user doesn't want to save their entry
	 */
	public final static char DONT_SAVE_ENTRY_CHARACTER = 'n';

	/**
	 * It's the error message shown when the answer that represents is you want to
	 * save is not valid
	 */
	private static final String ERROR_MESSAGE_INVALID_ANSWER_WHEN_SAVING = String
			.format("The answer must be '%s' or '%s'", SAVE_ENTRY_CHARACTER, DONT_SAVE_ENTRY_CHARACTER);

	@Override
	public GameLevel askGameLevel() throws UserInteractionException {
		char option;
		try {
			option = Character.toLowerCase(Console.readChar(ASK_LEVEL));
		} catch (ClassCastException exception) {
			throw new UserInteractionException(ERROR_MESSAGE_THE_LEVEL_MUST_BE_A_CHARACTER);
		}
		switch (option) {
		case EASY_LEVEL:
			return GameLevel.EASY;
		case MEDIUM_LEVEL:
			return GameLevel.MEDIUM;
		case HARD_LEVEL:
			return GameLevel.HARD;
		default:
			throw new UserInteractionException(ERROR_MESSAGE_INVALID_LEVEL);
		}
	}

	@Override
	public String askUserName() throws GameException {
		String userName;
		try {
			userName = Console.readString(ASK_USERNAME);
			ArgumentChecks.notEmpty(userName, ERROR_MESSAGE_THE_USERNAME_CANNOT_BE_EMPTY);
			ArgumentChecks.notBlank(userName, ERROR_MESSAGE_THE_USERNAME_CANNOT_BE_BLANK);
			checkUsernameIsInLowerCase(userName);
		} catch (IllegalArgumentException exception) {
			throw new GameException(exception.getMessage());
		}
		return userName;
	}

	/**
	 * Checks if the userName is in lower case.
	 * 
	 * @param userName is the given userName to check if it's valid
	 * @throws GameException if the userName is not in lowerCase
	 */
	private void checkUsernameIsInLowerCase(String userName) throws GameException {
		if (!userName.equals(userName.toLowerCase()))
			throw new GameException(ERROR_MESSAGE_THE_USERNAME_MUST_BE_IN_LOWERCASE);
	}

	@Override
	public int askNextOption() throws UserInteractionException {
		showOptions();
		int option;
		try {
			option = Console.readInt(ASK_OPTION);
		} catch (NumberFormatException exception) {
			throw new UserInteractionException(ERROR_MESSAGE_THE_OPTION_MUST_BE_A_NUMBER);
		}
		if (!(option >= EXIT_OPTION && option <= IMPORT_RESULTS_OPTION))
			throw new UserInteractionException(ERROR_MESSAGE_THE_OPTION_MUST_BE_ONE_OF_THE_AVAILABLE);
		return option;
	}

	/**
	 * It prints the menu of options
	 */
	private void showOptions() {
		System.out.println("Available options:");
		System.out.println(String.format("\t%s- Play a game", PLAY_OPTION));
		System.out.println(String.format("\t%s- Show my results", SHOW_MY_RESULTS_OPTION));
		System.out.println(String.format("\t%s- Show all results", SHOW_ALL_RESULTS_OPTION));
		System.out.println(String.format("\t%s- Export results", EXPORT_RESULTS_OPTION));
		System.out.println(String.format("\t%s- Import results", IMPORT_RESULTS_OPTION));
		System.out.println(String.format("\t%s- Exit", EXIT_OPTION));
	}

	@Override
	public boolean doYouWantToRegisterYourScore() throws UserInteractionException {
		char option;

		try {
			option = Character.toLowerCase(Console.readChar(ASK_TO_SAVE_SCORE));
		} catch (ClassCastException exception) {
			throw new UserInteractionException(ERROR_MESSAGE_THE_ANSWER_MUST_BE_A_CHARACTER);
		}
		switch (option) {
		case SAVE_ENTRY_CHARACTER:
			return true;
		case DONT_SAVE_ENTRY_CHARACTER:
			return false;
		default:
			throw new UserInteractionException(ERROR_MESSAGE_INVALID_ANSWER_WHEN_SAVING);
		}
	}

	@Override
	public void showPersonalRanking(List<GameRankingEntry> ranking) {
		if (ranking == null || ranking.isEmpty()) {
			System.out.println(THERE_ARE_NO_RANKINGS_YET);
		} else {
			ArgumentChecks.noNullElements(ranking, ERROR_MESSAGE_THE_LIST_OF_RANKINGS_CANNOT_HAVE_NULL_ELEMENTS);
			Collections.sort(ranking, new GameRankingEntryComparator());

			System.out.println(SHOW_PERSONAL_RANKING_FORMAT);

			for (GameRankingEntry entry : ranking) {
				System.out.println(entry);
			}
		}

	}

	@Override
	public void showRanking(List<GameRankingEntry> ranking) {
		if (ranking == null || ranking.isEmpty()) {
			System.out.println(THERE_ARE_NO_RANKINGS_YET);
		} else {
			ArgumentChecks.noNullElements(ranking, ERROR_MESSAGE_THE_LIST_OF_RANKINGS_CANNOT_HAVE_NULL_ELEMENTS);
			Collections.sort(ranking, new GameRankingEntryComparator());

			System.out.println(SHOW_ALL_RANKINGS_FORMAT);
			for (GameRankingEntry entry : ranking) {
				System.out.print(String.format(" %s\t", entry.getUserName()));
				System.out.println(entry);
			}
		}
	}

	@Override
	public void showGoodBye() {
		System.out.println(GOODBYE_MESSAGE);
	}

	@Override
	public void showErrorMessage(String message) {
		checkErrorMessage(message);
		System.out.println(String.format(MESSAGE_RECOVERABLE_ERROR, message));

	}

	@Override
	public void showFatalErrorMessage(String message) {
		checkErrorMessage(message);
		System.out.println(String.format(MESSAGE_FATAL_ERROR, message));

	}

	/**
	 * Checks if the message is null, blank or empty
	 * 
	 * @param message is the message to check
	 * @throws IllegalArgumentException if the message is null, empty or blank
	 */
	private void checkErrorMessage(String message) {
		ArgumentChecks.notNull(message, ERROR_MESSAGE_THE_ERROR_MESSAGE_CANNOT_BE_NULL);
		ArgumentChecks.notBlank(message, ERROR_MESSAGE_THE_ERROR_MESSAGE_CANNOT_BE_BLANK);
		ArgumentChecks.notEmpty(message, ERROR_MESSAGE_THE_ERROR_MESSAGE_CANNOT_BE_EMPTY);
	}

	@Override
	public String askFileName() throws GameException {
		String fileName;
		try {
			fileName = Console.readString(ASK_FILENAME);
			ArgumentChecks.notEmpty(fileName, ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_EMPTY);
			ArgumentChecks.notBlank(fileName, ERROR_MESSAGE_THE_FILE_NAME_CANNOT_BE_BLANK);
		} catch (IllegalArgumentException exception) {
			throw new GameException(exception.getMessage());
		}
		return fileName;
	}

}
