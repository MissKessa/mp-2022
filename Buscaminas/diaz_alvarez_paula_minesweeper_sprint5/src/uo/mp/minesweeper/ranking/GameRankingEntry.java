package uo.mp.minesweeper.ranking;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import uo.mp.minesweeper.ranking.parser.RankingParser;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * This class stores the score of a single game with the userName, level,
 * duration, date and if ti has won.
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class GameRankingEntry implements Serializable {

	/**
	 * It's the minimum duration possible (not included)
	 */
	public static final int MINIMUM_DURATION = 0;
	/**
	 * It's the error message thrown if the userName is empty
	 */
	private static final String ERROR_MESSAGE_THE_USERNAME_CANNOT_BE_EMPTY = "The userName cannot be empty";
	/**
	 * It's the error message thrown if the userName is blank
	 */
	private static final String ERROR_MESSAGE_THE_USERNAME_CANNOT_BE_BLANK = "The userName cannot be blank";
	/**
	 * It's the error message thrown if the userName is null
	 */
	private static final String ERROR_MESSAGE_THE_USERNAME_CANNOT_BE_NULL = "The userName cannot be null";
	/**
	 * It's the error message thrown if the date is null
	 */
	private static final String ERROR_MESSAGE_THE_DATE_CANNOT_BE_NULL = "The date cannot be null";
	/**
	 * It's the error message thrown if the duration isn't greater than the minimum
	 */
	private static final String ERROR_MESSAGE_INVALID_DURATION = "The duration must be greater than "
			+ MINIMUM_DURATION;

	/**
	 * It's the error message thrown if the level is null
	 */
	private static final String ERROR_MESSAGE_THE_LEVEL_CANNOT_BE_NULL = "The level cannot be null";

	private static final long serialVersionUID = 1L;

	private String userName;
	private GameLevel level;
	private long duration;
	private boolean hasWon;
	private Date date;

	/**
	 * Main constructor. It creates a GameRankingEntry with the given userName,
	 * level, duration, if it has won and date when the constructor is called.
	 * 
	 * @param userName is the given userName. It cannot be null, blank or empty
	 * @param level    is the given level.
	 * @param duration is the given duration. It cannot be negative or 0
	 * @param hasWon   is signal if has won
	 * @throws IllegalArgumentException if any of the parameters is invalid
	 */
	public GameRankingEntry(String userName, GameLevel level, long duration, boolean hasWon) {
		checkUsername(userName);
		ArgumentChecks.notNull(level, ERROR_MESSAGE_THE_LEVEL_CANNOT_BE_NULL);
		ArgumentChecks.isTrue(duration >= 0, ERROR_MESSAGE_INVALID_DURATION);

		this.userName = userName;
		this.level = level;
		this.duration = duration;
		this.hasWon = hasWon;
		this.date = new Date();
	}

	/**
	 * It creates a GameRankingEntry with the given userName, level, duration, if it
	 * has won and date. This constructor is used for parsing
	 * 
	 * @param userName is the given userName. It cannot be null, blank or empty
	 * @param level    is the given level.
	 * @param duration is the given duration. It cannot be negative or 0
	 * @param hasWon   is signal if has won
	 * @param date     is the date when the entry was created
	 * @throws IllegalArgumentException if any of the parameters is invalid
	 */
	public GameRankingEntry(String userName, GameLevel level, long duration, boolean hasWon, Date date) {
		checkUsername(userName);
		ArgumentChecks.notNull(level, ERROR_MESSAGE_THE_LEVEL_CANNOT_BE_NULL);
		ArgumentChecks.notNull(date, ERROR_MESSAGE_THE_DATE_CANNOT_BE_NULL);
		ArgumentChecks.isTrue(duration >= MINIMUM_DURATION, ERROR_MESSAGE_INVALID_DURATION);

		this.userName = userName;
		this.level = level;
		this.duration = duration;
		this.hasWon = hasWon;
		this.date = date;
	}

	/**
	 * CHecks if the userName is null, blank or empty
	 * 
	 * @param userName is the userName to be checked
	 * @throws IllegalArgumentException if any of the parameters is invalid
	 */
	private void checkUsername(String userName) {
		ArgumentChecks.notNull(userName, ERROR_MESSAGE_THE_USERNAME_CANNOT_BE_NULL);
		ArgumentChecks.notBlank(userName, ERROR_MESSAGE_THE_USERNAME_CANNOT_BE_BLANK);
		ArgumentChecks.notEmpty(userName, ERROR_MESSAGE_THE_USERNAME_CANNOT_BE_EMPTY);
	}

	/**
	 * @return the value of userName.
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * @return the value of duration.
	 */
	public long getDuration() {
		return this.duration;
	}

	/**
	 * 
	 * @return the value of hasWon.
	 */
	public boolean hasWon() {
		return this.hasWon;
	}

	/**
	 * 
	 * @return the value of the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * 
	 * @return the value of the level
	 */
	public GameLevel getLevel() {
		return this.level;
	}

	/**
	 * @return a String representation of the GameRankingEntry object
	 */
	@SuppressWarnings("deprecation")
	@Override
	public String toString() {
		String win;
		if (hasWon) {
			win = "won";
		} else {
			win = "lost";
		}

		String dateString = date.getDate() + "-" + (date.getMonth() + 1) + "-" + (date.getYear() - 100);
		String timeString = date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();

		if (timeString.length() < 8) {
			return String.format("%s\t%s\t %s\t %s\t %s", dateString, timeString, level.toString(), win, duration);
		}
		return String.format("%s\t%s %s\t %s\t %s", dateString, timeString, level.toString(), win, duration);

	}

	/**
	 * It returns the string of this GameRankingEntry in the format of the
	 * parser/serializer
	 * 
	 * @return the string representing this GameRankingEntry
	 */
	public String serialize() {
		String result = getUserName();
		result += ";" + new SimpleDateFormat("dd/MM/yy").format(getDate());
		result += ";" + new SimpleDateFormat("HH:mm:ss").format(getDate());
		result += ";" + level.toString();
		if (hasWon) {
			result += ";" + RankingParser.VALUE_HAS_WON_TRUE;
		} else {
			result += ";" + RankingParser.VALUE_HAS_WON_FALSE;
		}
		result += ";" + getDuration();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameRankingEntry other = (GameRankingEntry) obj;
		return Objects.equals(date, other.date) && duration == other.duration && hasWon == other.hasWon
				&& level == other.level && Objects.equals(userName, other.userName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, duration, hasWon, level, userName);
	}
}
