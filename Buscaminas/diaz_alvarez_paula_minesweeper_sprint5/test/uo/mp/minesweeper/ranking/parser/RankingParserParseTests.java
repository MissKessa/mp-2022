package uo.mp.minesweeper.ranking.parser;

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
import uo.mp.minesweeper.util.log.FileLogger;

public class RankingParserParseTests {
	RankingParser parser = new RankingParser(new FileLogger(FileLogger.LOG_FILE));
	List<String> lines;
	List<GameRankingEntry> entries;

	@BeforeEach
	public void setUp() {
		lines = new ArrayList<>();
		lines.add("a;09/05/23;12:38:39;EASY;won;20");
		lines.add("b;12/01/01;9:29:10;MEDIUM;lost;10");
		lines.add("c;24/12/04;0:00:00;HARD;won;1000");

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
	 * GIVEN: a list of valid lines
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithValidLines() {
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: an empty list of lines
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: a empty list of GameRankingEntries is returned
	 */
	@Test
	void emptyList() {
		lines = new ArrayList<>();
		entries = new ArrayList<>();
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: an null list of lines
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: an IllegalArgumentException is thrown
	 */
	@Test
	void nullList() {
		try {
			parser.parse(null);
		} catch (IllegalArgumentException e) {
			assertEquals(RankingParser.ERROR_MESSAGE_THE_LIST_OF_LINES_CANNOT_BE_NULL, e.getMessage());
		}
	}

	/**
	 * GIVEN: a list of lines with some empty ones
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithSomeEmptyLines() {
		lines.add("");
		lines.add("");
		lines.add("");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some null ones
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithSomeNullLines() {
		lines.add(null);
		lines.add(null);
		lines.add(null);
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some blank ones
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithSomeBlankLines() {
		lines.add("     ");
		lines.add(" ");
		lines.add("  ");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with more fields
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithMoreFields() {
		lines.add("a;09/05/23;12:38:39;EASY;won;20; ");
		lines.add("a;09/05/23; ;12:38:39;EASY;won;20; ");
		lines.add(" ;a;09/05/23; ;12:38:39;EASY;won;20; ");
		lines.add("a;09/05/23;12:38:39;EASY;won;20; 12");
		lines.add("a;09/05/23; 12 ;12:38:39;EASY;won;20; 12");
		lines.add(" 12 ;a;09/05/23; 12 ;12:38:39;EASY;won;20; 12");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with less fields
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithLessFields() {
		lines.add("a;12:38:39;EASY;won;20");
		lines.add("12:38:39;EASY;won;20");
		lines.add("a;12:38:39;EASY;won");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with empty win field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithEmptyWinField() {
		lines.add("a;09/05/23;12:38:39;EASY;;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with blank win field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithBlankWinField() {
		lines.add("a;09/05/23;12:38:39;EASY;  ;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with incorrect win field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithIncorrectWinField() {
		lines.add("a;09/05/23;12:38:39;EASY;aaa;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with empty username field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithEmptyUsernameField() {
		lines.add(";09/05/23;12:38:39;EASY;won;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with blank username field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithBlankUsernameField() {
		lines.add("    ;09/05/23;12:38:39;EASY;won;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with username in uppercase field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithUsernameFieldInUppercase() {
		lines.add("AA;09/05/23;12:38:39;EASY;won;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with empty date field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithEmptyDateField() {
		lines.add("a;;12:38:39;EASY;won;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with blank date field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithBlankDateField() {
		lines.add("a;    ;12:38:39;EASY;won;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with incorrect date format field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithDateFieldInIncorrectFormat() {
		lines.add("a;09-05-23;12:38:39;EASY;won;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with empty hour field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithEmptyHourField() {
		lines.add("a;09/05/23;;EASY;won;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with blank hour field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithBlankHourField() {
		lines.add("a;09/05/23;   ;EASY;won;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with incorrect hour format field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithHourFieldInIncorrectFormat() {
		lines.add("a;09/05/23;12-38-39;EASY;won;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with empty level field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithEmptyLevelField() {
		lines.add("a;09/05/23;12:38:39;;won;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with blank level field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithBlankLevelField() {
		lines.add("a;09/05/23;12:38:39;     ;won;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with incorrect level field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithIncorrectLevelField() {
		lines.add("a;09/05/23;12:38:39;aa;won;20");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with empty duration field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithEmptyDurationField() {
		lines.add("a;09/05/23;12:38:39;EASY;won;");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with blank duration field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithBlankDurationField() {
		lines.add("a;09/05/23;12:38:39;EASY;won;  ");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with a non number duration field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithNotANumberDurationField() {
		lines.add("a;09/05/23;12:38:39;EASY;won;aaa");
		assertEquals(entries, parser.parse(lines));
	}

	/**
	 * GIVEN: a list of lines with some lines with a negative duration field
	 * <p>
	 * WHEN: parsing those lines
	 * <p>
	 * THEN: all the correct lines are returned as a list of GameRankingEntries
	 */
	@Test
	void listWithLineWithNegativeNumberDurationField() {
		lines.add("a;09/05/23;12:38:39;EASY;won;-20");
		assertEquals(entries, parser.parse(lines));
	}

}
