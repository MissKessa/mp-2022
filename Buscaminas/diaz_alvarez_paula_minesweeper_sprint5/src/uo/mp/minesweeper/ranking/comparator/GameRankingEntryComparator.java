package uo.mp.minesweeper.ranking.comparator;

import java.util.Comparator;

import uo.mp.minesweeper.ranking.GameRankingEntry;

/**
 * It's the comparator uses to sort the GameRankingEntries:
 * <p>
 * 1º the levels are compare (They are sorted in this order: HARD > MEDIUM >
 * EASY)
 * <p>
 * 2º If the level is equal, the durations are compare (it goes first the
 * entries with less duration)
 * <p>
 * 3º If the level and duration is equal, the dates are compare (it goes first
 * the oldest entry)
 */
public class GameRankingEntryComparator implements Comparator<GameRankingEntry> {

	@Override
	public int compare(GameRankingEntry g1, GameRankingEntry g2) {
		final int compareToLevel = g1.getLevel().compareTo(g2.getLevel());
		final int compareToDuration = ((int) (g1.getDuration()) - (int) (g2.getDuration()));
		final int compareToDate = g1.getDate().compareTo(g2.getDate());

		if (compareToLevel == 0) {
			if (compareToDuration == 0) {
				return compareToDate;
			}
			return compareToDuration;
		}
		return compareToLevel;
	}

}
