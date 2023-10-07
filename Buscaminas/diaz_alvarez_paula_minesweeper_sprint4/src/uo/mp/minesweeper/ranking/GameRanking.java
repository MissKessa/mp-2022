package uo.mp.minesweeper.ranking;

import java.util.ArrayList;
import java.util.List;

import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * This class shall store a list of GameRankingEntry objects representing
 * completed games and provide methods to query this list.
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class GameRanking {
	private List<GameRankingEntry> entries;

	/**
	 * This constructor initializes the entries to an empty list
	 */
	public GameRanking() {
		this.entries = new ArrayList<>();
	}

	/**
	 * Adds the GameRankingEntry object to the end of the list of gameRankingEntries
	 * if the user has won
	 * 
	 * @param gameRankingEntry is the given object to add
	 */
	public void append(GameRankingEntry gameRankingEntry) {
		ArgumentChecks.notNull(gameRankingEntry, "The gameRankingEntry cannot be null");
		if (gameRankingEntry.hasWon())
			this.entries.add(gameRankingEntry);
	}

	/**
	 * 
	 * @return a copy of the list of gameRankingEntries.
	 */
	public List<GameRankingEntry> getAllEntries() {
		if (this.entries == null) {
			return null;
		}
		List<GameRankingEntry> copy = new ArrayList<>(this.entries.size());
		for (GameRankingEntry entry : this.entries) {
			copy.add(entry);
		}
		return copy;
	}

	/**
	 * @param userName is the given userName
	 * @return a list containing only those gameRankingEntries whose user matches
	 *         the userName received as parameter.
	 */
	public List<GameRankingEntry> getEntriesForUsername(String userName) {
		ArgumentChecks.notNull(userName, "The userName cannot be null");
		ArgumentChecks.notBlank(userName, "The userName cannot be blank");
		ArgumentChecks.notEmpty(userName, "The userName cannot be empty");

		List<GameRankingEntry> entriesOfTheUser = new ArrayList<>();
		if (this.entries == null) {
			return null;
		}
		for (GameRankingEntry entry : this.entries) {
			if (entry.getUserName().equals(userName)) {
				entriesOfTheUser.add(entry);
			}
		}
		return entriesOfTheUser;
	}
}
