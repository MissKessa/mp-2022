package uo.mp.minesweeper.ranking.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uo.mp.minesweeper.ranking.GameRankingEntry;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.check.ArgumentChecks;
import uo.mp.minesweeper.util.exceptions.GameException;
import uo.mp.minesweeper.util.log.SimpleLogger;

/**
 * It's the parser for the GameRankingEntries from a list of lines. If an
 * IllegalLineFormatException occurs, the line is ignored and the error is
 * logged
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class RankingParser {

	/**
	 * It's the error message shown if the string cannot be converted to an integer
	 */
	private static final String ERROR_MESSAGE_NOT_A_NUMBER = "%s cannot be converted to an integer";

	/**
	 * It's the error message shown if the level is invalid
	 */
	private static final String ERROR_MESSAGE_INVALID_LEVEL = "The level is incorrect: %s";

	/**
	 * It's the error message shown if the string is empty
	 */
	private static final String ERROR_MESSAGE_THE_STRING_CANNOT_BE_EMPTY = "The %s cannot be empty: %s";

	/**
	 * It's the error message shown if the string is blank
	 */
	private static final String ERROR_MESSAGE_THE_STRING_CANNOT_BE_BLANK = "The %s cannot be blank: %s";

	/**
	 * It's the error message shown if the string is null
	 */
	private static final String ERROR_MESSAGE_THE_STRING_CANNOT_BE_NULL = "The %s cannot be null: %s";

	/**
	 * It's the time format used to concatenate the date and the hour, and to create
	 * a Date object
	 */
	private static final String TIME_FORMAT = "dd/MM/yy HH:mm:ss";

	/**
	 * It's the error message shown if the userName is not in lowercase
	 */
	private static final String ERROR_MESSAGE_THE_USERNAME_MUST_BE_IN_LOWER_CASE = "The username must be in lower case: %s";

	/**
	 * It's the error message shown if the win field is not VALUE_HAS_WON_FALSE or
	 * VALUE_HAS_WON_TRUE
	 */
	private static final String ERROR_MESSAGE_INVALID_HAS_WON_FIELD = "The hasWon field must be %s or %s: %s";

	/**
	 * It's the string representing the false value for the hasWon field
	 */
	public static final String VALUE_HAS_WON_FALSE = "lost";
	/**
	 * It's the string representing the true value for the hasWon field
	 */
	public static final String VALUE_HAS_WON_TRUE = "won";

	/**
	 * It's the error message shown if the line has a different number of fields
	 * from LENGTH
	 */
	private static final String ERROR_MESSAGE_WRONG_LENGTH = "The line hasn't have length %s, it has length:%s";

	/**
	 * It's the error message shown if the duration is negative number or 0
	 */
	private static final String ERROR_MESSAGE_THE_DURATION_MUST_BE_NON_NEGATIVE = "The duration field must be non-negative and not 0";

	/**
	 * It's the separator used in the lines for the fields
	 */
	private static final String SEPARATOR = ";";

	/**
	 * It's the error message shown if the list of lines is null
	 */
	public static final String ERROR_MESSAGE_THE_LIST_OF_LINES_CANNOT_BE_NULL = "The list of lines cannot be null";

	/**
	 * It's the error message shown if the logger is null
	 */
	private static final String ERROR_MESSAGE_THE_LOGGER_CANNOT_BE_NULL = "The logger cannot be null";

	/**
	 * It's the position for the userName field in the line
	 */
	private final static int USERNAME_POS = 0;
	/**
	 * It's the position for the date field in the line
	 */
	private final static int DATE_POS = 1;
	/**
	 * It's the position for the hour field in the line
	 */
	private final static int HOUR_POS = 2;
	/**
	 * It's the position for the level field in the line
	 */
	private final static int LEVEL_POS = 3;
	/**
	 * It's the position for the win field in the line
	 */
	private final static int WIN_POS = 4;
	/**
	 * It's the position for the duration field in the line
	 */
	private final static int DURATION_POS = 5;
	/**
	 * It's the number of fields that the lines must have
	 */
	private final static int LENGTH = 6;

	private int lineNumber = 1;
	private SimpleLogger logger;

	/**
	 * It's the main constructor of the parser. It sets the logger to the given one,
	 * so all the application can use the same logger
	 * 
	 * @param logger is the given logger
	 * @throws IllegalArgumentException if the logger is null
	 */
	public RankingParser(SimpleLogger logger) {
		ArgumentChecks.notNull(logger, ERROR_MESSAGE_THE_LOGGER_CANNOT_BE_NULL);
		this.logger = logger;
	}

	/**
	 * It transforms a list of lines that has this format:
	 * userName;date;hour;level;win;duration
	 * <p>
	 * into a list of GameRankingEntry objects
	 * 
	 * @param lines is the list of lines
	 * @return a list of GameRankingEntry objects corresponding to the given lines
	 * @throws IllegalArgumentException if the list of lines is null
	 */
	public List<GameRankingEntry> parse(List<String> lines) {
		ArgumentChecks.notNull(lines, ERROR_MESSAGE_THE_LIST_OF_LINES_CANNOT_BE_NULL);
		List<GameRankingEntry> entries = new ArrayList<>();
		for (String line : lines) {
			try {
				GameRankingEntry entry = parseEntry(line);
				entries.add(entry);

			} catch (IllegalLineFormatException exception) {
				logger.log(exception);
			}
			this.lineNumber++;
		}
		return entries;
	}

	/**
	 * It transforms a line into a GameRankingEntry object
	 * 
	 * @param line is the given line
	 * @return the GameRankingEntry object corresponding to that line
	 * @throws IllegalLineFormatException when line is null, empty, blank, doesn't
	 *                                    have the correct Length, when any of the
	 *                                    parts is invalid
	 */
	private GameRankingEntry parseEntry(String line) throws IllegalLineFormatException {
		checkString("line", line);

		String[] parts = line.split(SEPARATOR);
		if (parts.length != LENGTH) {
			throw new IllegalLineFormatException(lineNumber,
					String.format(ERROR_MESSAGE_WRONG_LENGTH, LENGTH, parts.length, line));
		}
		String username = parts[USERNAME_POS];
		checkUsername(username);

		String dateString = parts[DATE_POS];
		String hourString = parts[HOUR_POS];
		String fullDateString = dateString + " " + hourString;
		Date date = parseDate(fullDateString);

		String levelString = parts[LEVEL_POS];
		GameLevel level = parseLevel(levelString);

		String win = parts[WIN_POS];
		boolean hasWon = checkWin(win);

		String durationString = parts[DURATION_POS];
		int duration = checkDuration(durationString);

		return new GameRankingEntry(username, level, duration, hasWon, date);
	}

	/**
	 * Checks if the hasWon field is valid or not
	 * 
	 * @param win is the value for the hasWon field
	 * @return the boolean conversion of the string win to the hasWon field
	 * @throws IllegalLineFormatException when the win is null, blank, empty, or
	 *                                    when it's not one of the 2 options for
	 *                                    that field
	 */
	private boolean checkWin(String win) throws IllegalLineFormatException {
		checkString("hasWon field", win);
		if (win.equals(VALUE_HAS_WON_TRUE)) {
			return true;
		} else if (win.equals(VALUE_HAS_WON_FALSE)) {
			return false;
		} else {
			throw new IllegalLineFormatException(lineNumber,
					String.format(ERROR_MESSAGE_INVALID_HAS_WON_FIELD, VALUE_HAS_WON_TRUE, VALUE_HAS_WON_FALSE, win));
		}

	}

	/**
	 * Checks if the userName field is valid or not
	 * 
	 * @param username is the userName to be checked
	 * @throws IllegalLineFormatException if the userName is null, blank, empty, or
	 *                                    it's not in lowercase
	 */
	private void checkUsername(String username) throws IllegalLineFormatException {
		checkString("username", username);
		checkUsernameIsInLowerCase(username);
	}

	/**
	 * Checks if the duration field is valid or not
	 * 
	 * @param durationString is the duration to be checked
	 * @return the duration as a valid number
	 * @throws IllegalLineFormatException if the duration is null, blank, empty, or
	 *                                    it's not a number, or it's a negative one
	 */
	private int checkDuration(String durationString) throws IllegalLineFormatException {
		checkString("duration", durationString);
		int duration = toInteger(durationString);
		if (duration <= GameRankingEntry.MINIMUM_DURATION)
			throw new IllegalLineFormatException(lineNumber,
					String.format(ERROR_MESSAGE_THE_DURATION_MUST_BE_NON_NEGATIVE, duration));
		return duration;
	}

	/**
	 * Checks if the userName is in lower case.
	 * 
	 * @param userName is the given userName to check if it's valid
	 * @throws GameException if the userName is not in lowerCase
	 */
	private void checkUsernameIsInLowerCase(String userName) throws IllegalLineFormatException {
		if (!userName.equals(userName.toLowerCase()))
			throw new IllegalLineFormatException(lineNumber,
					String.format(ERROR_MESSAGE_THE_USERNAME_MUST_BE_IN_LOWER_CASE, userName));
	}

	/**
	 * Checks if the date field is valid
	 * 
	 * @param completeDateString is the concatenation of the date and hour separated
	 *                           by a blank
	 * @return a Date Object corresponding to the given date
	 * @throws IllegalLineFormatException if the completeDateString is null, blank,
	 *                                    empty or doesn't have the correct format
	 */
	private Date parseDate(String completeDateString) throws IllegalLineFormatException {
		checkString("full date", completeDateString);
		Date date;
		try {
			date = new SimpleDateFormat(TIME_FORMAT).parse(completeDateString);
		} catch (ParseException e) {
			throw new IllegalLineFormatException(lineNumber, e.getMessage());
		}
		return date;
	}

	/**
	 * Checks is the word is valid (not null, not blank and not empty)
	 * 
	 * @param typeOfword is a string with the name of the word to use it in the
	 *                   error message
	 * @param word       is the string to be checked
	 * @throws IllegalLineFormatException when the word is invalid
	 */
	private void checkString(String typeOfword, String word) throws IllegalLineFormatException {
		if (word == null) {
			throw new IllegalLineFormatException(lineNumber,
					String.format(ERROR_MESSAGE_THE_STRING_CANNOT_BE_NULL, typeOfword, word));
		}
		if (word.isBlank()) {
			throw new IllegalLineFormatException(lineNumber,
					String.format(ERROR_MESSAGE_THE_STRING_CANNOT_BE_BLANK, typeOfword, word));
		}
		if (word.isEmpty()) {
			throw new IllegalLineFormatException(lineNumber,
					String.format(ERROR_MESSAGE_THE_STRING_CANNOT_BE_EMPTY, typeOfword, word));
		}
	}

	/**
	 * Checks if the level field is valid
	 * 
	 * @param level is the level to be checked
	 * @return a GameLevel object corresponding to the given level
	 * @throws IllegalLineFormatException if the level is null, blank, empty or not
	 *                                    a level option
	 */
	private GameLevel parseLevel(String level) throws IllegalLineFormatException {
		checkString("level", level);

		if (level.equals(GameLevel.EASY.toString()))
			return GameLevel.EASY;

		else if (level.equals(GameLevel.MEDIUM.toString()))
			return GameLevel.MEDIUM;

		else if (level.equals(GameLevel.HARD.toString()))
			return GameLevel.HARD;

		else
			throw new IllegalLineFormatException(lineNumber, String.format(ERROR_MESSAGE_INVALID_LEVEL, level));

	}

	/**
	 * COnverts a string into an integer
	 * 
	 * @param string is the string to be converted
	 * @return the corresponding integer
	 * @throws IllegalLineFormatException if the string is not a number
	 */
	private int toInteger(String string) throws IllegalLineFormatException {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException exception) {
			throw new IllegalLineFormatException(this.lineNumber, String.format(ERROR_MESSAGE_NOT_A_NUMBER, string));
		}
	}
}
