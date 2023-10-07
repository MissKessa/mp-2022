package uo.mp.minesweeper.ranking;

import java.util.Date;

import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * This class stores the score of a single game.
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class GameRankingEntry {
	private String userName;
	private GameLevel level;
	private long duration;
	private boolean hasWon;
	private Date date;

	/**
	 * It creates a GameRankingEntry with the given userName, level, duration, if it
	 * has won and date when the constructor is called.
	 * 
	 * @param userName is the given userName. It cannot be null, blank or empty
	 * @param level    is the given level.
	 * @param duration is the given duration. It cannot be negative or 0
	 * @param hasWon   is signal if has won
	 */
	public GameRankingEntry(String userName, GameLevel level, long duration, boolean hasWon) {
		ArgumentChecks.notNull(userName, "The userName cannot be null");
		ArgumentChecks.notBlank(userName, "The userName cannot be blank");
		ArgumentChecks.notEmpty(userName, "The userName cannot be empty");
		ArgumentChecks.notNull(level, "The level cannot be null");

		this.userName = userName;
		this.level = level;
		this.duration = duration;
		this.hasWon = hasWon;
		this.date = new Date();
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
		return String.format("%s\t%s\t %s\t %s\t %s",
				date.getDate() + "-" + (date.getMonth() + 1) + "-" + (date.getYear() - 100),
				date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds(), level.toString(), hasWon,
				duration);
	}
}
