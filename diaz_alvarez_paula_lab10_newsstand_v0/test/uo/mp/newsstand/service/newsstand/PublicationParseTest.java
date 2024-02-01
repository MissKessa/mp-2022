package uo.mp.newsstand.service.newsstand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import uo.mp2122.newsstand.domain.Newspaper;
import uo.mp2122.newsstand.domain.Publication;
import uo.mp2122.newsstand.service.parsers.PublicationParser;

public class PublicationParseTest {
	private String line1 = "newspaper	La Nueva España	14	30";
	private String line2 = "newspaper	La Nueva Asturias	3	3";

	private Newspaper news1 = new Newspaper("La Nueva España", 14, 30);
	private Newspaper news2 = new Newspaper("La Nueva Asturias", 3, 3);

	private PublicationParser pp = new PublicationParser();

	/**
	 * GIVEN: a list of 2 valid lines
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned
	 */
	@Test
	void parseValidLines() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one is repeated
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (no repeated publications)
	 */
	@Test
	void parseRepeatedLine() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add(line1);

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one is null)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the null one)
	 */
	@Test
	void parseNullLine() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add(null);

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one is empty)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the empty one)
	 */
	@Test
	void parseEmptyLine() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one is blank)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the blank one)
	 */
	@Test
	void parseBlankLine() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("     ");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one has an incorrect type)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the incorrect one)
	 */
	@Test
	void parseIncorrectType() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("aaaa	La Nueva	14	30");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one has less fields)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the incorrect one)
	 */
	@Test
	void parseWithLessFields() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("newspaper	La Nueva	30");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one has more fields)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the incorrect one)
	 */
	@Test
	void parseWithMoreFields() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("newspaper	La Nueva	4	30	12");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one has an empty name)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the incorrect one)
	 */
	@Test
	void parseEmptyName() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("newspaper		4	30");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one has a blank name)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the incorrect one)
	 */
	@Test
	void parseBlankName() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("newspaper	    	4	30	12");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one has name greater than the maximum amount of
	 * characters)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the incorrect one)
	 */
	@Test
	void parseNameWithGreaterLengthThanMaximum() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add(
				"newspaper	La Nuevaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa	4	30	12");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one has a stock that is not a number)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the incorrect one)
	 */
	@Test
	void parseStockThatIsNotANumber() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("newspaper	La Nueva	a	30");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one has a stock that is less than the minimum)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the incorrect one)
	 */
	@Test
	void parseStockLessThanMinimum() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("newspaper	La Nueva	-1	30");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one has a stock that is greater than the maximum)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the incorrect one)
	 */
	@Test
	void parseStockGreaterThanMaximum() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("newspaper	La Nueva	1000000	30");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one has a number of sales that is not a number)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the incorrect one)
	 */
	@Test
	void parseSalesThatIsNotANumber() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("newspaper	La Nueva	10	a");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one has a number of sales that is less than the
	 * minimum)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the incorrect one)
	 */
	@Test
	void parseSalesLessThanMinimum() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("newspaper	La Nueva	10	-1");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

	/**
	 * GIVEN: a list of 3 lines (one has a number of sales that is greater than the
	 * maximum)
	 * <p>
	 * WHEN: calling parse()
	 * <p>
	 * THEN: a list with the 2 publications is returned (without the incorrect one)
	 */
	@Test
	void parseSalesGreaterThanMaximum() {
		List<String> lines = new ArrayList<>();
		lines.add(line1);
		lines.add(line2);
		lines.add("newspaper	La Nueva	10	10000000");

		List<Publication> publications = new ArrayList<>();
		publications.add(news1);
		publications.add(news2);
		assertEquals(publications, pp.parse(lines));
	}

}
