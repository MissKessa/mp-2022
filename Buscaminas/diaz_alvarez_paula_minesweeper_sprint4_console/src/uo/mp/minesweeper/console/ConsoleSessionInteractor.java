package uo.mp.minesweeper.console;

import java.util.List;

import uo.mp.minesweeper.ranking.GameRankingEntry;
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
	 * It's the character representing if the user wants to save their entry
	 */
	public final static char SAVE_ENTRY_CHARACTER = 'y';

	/**
	 * It's the character representing if the user doesn't want to save their entry
	 */
	public final static char DONT_SAVE_ENTRY_CHARACTER = 'n';

	@Override
	public GameLevel askGameLevel() throws UserInteractionException {
		char option;
//		try {
		try {
			option = Character.toLowerCase(Console.readChar("Level? (e)asy, (m)edium, (h)igh"));
		} catch (ClassCastException exception) {
			throw new UserInteractionException("The level must be a character");
		}
		switch (option) {
		case EASY_LEVEL:
			return GameLevel.EASY;
		case MEDIUM_LEVEL:
			return GameLevel.MEDIUM;
		case HARD_LEVEL:
			return GameLevel.HARD;
		default:
			throw new UserInteractionException(
					String.format("The level must be '%s', '%s' or '%s'", EASY_LEVEL, MEDIUM_LEVEL, HARD_LEVEL));
		}
//		} catch (UserInteractionException exception) {
//			System.out.println(exception.getMessage());
//			return askGameLevel();
//		}
	}

	@Override
	public String askUserName() throws GameException {
		String userName;
//		try {
		try {
			userName = Console.readString("Player name?");
			// ArgumentChecks.notNull(userName, "The userName cannot be null");
			ArgumentChecks.notEmpty(userName, "The userName cannot be empty");
			ArgumentChecks.notBlank(userName, "The userName cannot be blank");
			checkUsernameIsInLowerCase(userName);
		} catch (IllegalArgumentException exception) {
			throw new GameException(exception.getMessage());
		}
//		} catch (UserInteractionException exception) {
//			System.out.println(exception.getMessage());
//			userName = askUserName();
//		}
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
			throw new GameException("The username must be in lowercase");
	}

	@Override
	public int askNextOption() throws UserInteractionException {
		showOptions();
		int option;
//		try {
		try {
			option = Console.readInt("Option?");
		} catch (NumberFormatException exception) {
			throw new UserInteractionException("The option must be a number");
		}
		if (!(option >= EXIT_OPTION && option <= SHOW_ALL_RESULTS_OPTION))
			throw new UserInteractionException("The option must be one of the availables");
//		} catch (UserInteractionException exception) {
//			System.out.println(exception.getMessage());
//			option = askNextOption();
//		}
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
		System.out.println(String.format("\t%s- Exit", EXIT_OPTION));
	}

	@Override
	public boolean doYouWantToRegisterYourScore() throws UserInteractionException {
		char option;

		try {
			option = Character.toLowerCase(Console.readChar("Do you want to store your score? (y)es, (n)o"));
		} catch (ClassCastException exception) {
			throw new UserInteractionException("The answer must be a character");
		}
		switch (option) {
		case SAVE_ENTRY_CHARACTER:
			return true;
		case DONT_SAVE_ENTRY_CHARACTER:
			return false;
		default:
			throw new UserInteractionException(
					String.format("The answer must be '%s' or '%s'", SAVE_ENTRY_CHARACTER, DONT_SAVE_ENTRY_CHARACTER));
		}

	}

	@Override
	public void showPersonalRanking(List<GameRankingEntry> ranking) {
		if (ranking == null || ranking.isEmpty()) {
			System.out.println("There are no rankings yet");
		} else {
			ArgumentChecks.noNullElements(ranking, "The list of rankings cannot have null elements");

			System.out.println(".Date\t.Hour\t.Level\t.Res\t.Time");

			for (GameRankingEntry entry : ranking) {
				System.out.println(entry);
			}
		}

	}

	@Override
	public void showRanking(List<GameRankingEntry> ranking) {
		if (ranking == null || ranking.isEmpty()) {
			System.out.println("There are no rankings yet");
		} else {
			ArgumentChecks.noNullElements(ranking, "The list of rankings cannot have null elements");

			System.out.println(".User\t.Date\t.Hour\t.Level\t.Res\t.Time");
			for (GameRankingEntry entry : ranking) {
				System.out.print(String.format(" %s\t", entry.getUserName()));
				System.out.println(entry);
			}
		}

	}

	@Override
	public void showGoodBye() {
		System.out.println("Thanks for your session. Bye, bye!");

	}

	@Override
	public void showErrorMessage(String message) {
		ArgumentChecks.notNull(message, "The error message cannot be null");
		ArgumentChecks.notBlank(message, "The error message cannot be blank");
		ArgumentChecks.notEmpty(message, "The error message cannot be empty");

		System.out.println(String.format("A recoverable error has occurr, please try again. %s", message));

	}

	@Override
	public void showFatalErrorMessage(String message) {
		ArgumentChecks.notNull(message, "The error message cannot be null");
		ArgumentChecks.notBlank(message, "The error message cannot be blank");
		ArgumentChecks.notEmpty(message, "The error message cannot be empty");

		System.out.println(String.format(
				"A FATAL error has occurr, the execution will stop. Please contact the service provider. Error: %s",
				message));

	}

}
