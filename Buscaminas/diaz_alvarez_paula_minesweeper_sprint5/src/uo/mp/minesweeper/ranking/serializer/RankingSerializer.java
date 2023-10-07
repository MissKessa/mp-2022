package uo.mp.minesweeper.ranking.serializer;

import java.util.LinkedList;
import java.util.List;

import uo.mp.minesweeper.ranking.GameRankingEntry;
import uo.mp.minesweeper.util.check.ArgumentChecks;

/**
 * It's the serializer for the GameRankingEntries. It transforms a list of
 * GameRankingEntries into a list of lines representing them.
 * 
 * @author Paula Díaz Álvarez
 *
 */
public class RankingSerializer {
	/**
	 * It's the error message thrown if the list of entries is null
	 */
	public static final String ERROR_MESSAGE_NULL_ENTRY = "The list of entries cannot have null elements";
	/**
	 * It's the error message thrown if the list of entries has null elements
	 */
	public static final String ERROR_MESSAGE_THE_LIST_OF_ENTRIES_CANNOT_BE_NULL = "The list of entries cannot be null";

	/**
	 * It converts a list of entries in a list of lines
	 * 
	 * @param entries is the list of GameRankingEntries
	 * @return the list of lines representing them
	 * @throws IllegalArgumentException if the list of entries is null or has null
	 *                                  elements
	 */
	public static List<String> serialize(List<GameRankingEntry> entries) {
		ArgumentChecks.notNull(entries, ERROR_MESSAGE_THE_LIST_OF_ENTRIES_CANNOT_BE_NULL);
		List<String> result = new LinkedList<>();
		for (GameRankingEntry entry : entries) {
			ArgumentChecks.notNull(entry, ERROR_MESSAGE_NULL_ENTRY);
			result.add(entry.serialize());
		}
		return result;
	}
}
