package uo.mp.minesweeper.ranking.serializer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uo.mp.minesweeper.ranking.GameRankingEntry;
import uo.mp.minesweeper.session.GameLevel;

public class RankingSerializerSerializeTests {
	List<String> lines;
	List<GameRankingEntry> entries;

	@BeforeEach
	public void setUp() {
		lines = new ArrayList<>();
		lines.add("a;09/05/23;12:38:39;EASY;won;20");
		lines.add("b;12/01/01;09:29:10;MEDIUM;lost;10");
		lines.add("c;24/12/04;00:00:00;HARD;won;1000");

		entries = new ArrayList<>();
		try {
			entries.add(new GameRankingEntry("a", GameLevel.EASY, 20, true,
					new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("09/05/23 12:38:39")));

			entries.add(new GameRankingEntry("b", GameLevel.MEDIUM, 10, false,
					new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("12/01/01 9:29:10")));

			entries.add(new GameRankingEntry("c", GameLevel.HARD, 1000, true,
					new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("24/12/04 0:00:00")));
		} catch (ParseException e) {
			fail("No exception should be thrown");
		}
	}

	/**
	 * GIVEN: a list of valid entries
	 * <p>
	 * WHEN: serializing those entries
	 * <p>
	 * THEN: all the entries are returned as a list of lines
	 */
	@Test
	void serializeCorrectList() {
		assertEquals(lines, RankingSerializer.serialize(entries));
	}

	/**
	 * GIVEN: a empty list of entries
	 * <p>
	 * WHEN: serializing those entries
	 * <p>
	 * THEN: an empty list of lines is returned
	 */
	@Test
	void serializeEmptyList() {
		lines = new ArrayList<>();
		entries = new ArrayList<>();
		assertEquals(lines, RankingSerializer.serialize(entries));
	}

	/**
	 * GIVEN: a list of entries with some null ones
	 * <p>
	 * WHEN: serializing those entries
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void serializeListWithSomeNull() {
		lines.add(null);
		try {
			RankingSerializer.serialize(entries);
		} catch (IllegalArgumentException e) {
			assertEquals(RankingSerializer.ERROR_MESSAGE_NULL_ENTRY, e.getMessage());
		}
	}

	/**
	 * GIVEN: a list of entries with some null ones
	 * <p>
	 * WHEN: serializing those entries
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void serializeNullList() {
		try {
			RankingSerializer.serialize(null);
		} catch (IllegalArgumentException e) {
			assertEquals(RankingSerializer.ERROR_MESSAGE_THE_LIST_OF_ENTRIES_CANNOT_BE_NULL, e.getMessage());
		}
	}

}
