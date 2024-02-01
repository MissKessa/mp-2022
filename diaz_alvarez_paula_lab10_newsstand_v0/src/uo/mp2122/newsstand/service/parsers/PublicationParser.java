package uo.mp2122.newsstand.service.parsers;

import java.util.ArrayList;
import java.util.List;

import uo.mp2122.newsstand.domain.Magazine;
import uo.mp2122.newsstand.domain.Newspaper;
import uo.mp2122.newsstand.domain.Publication;
import uo.mp2122.newsstand.exception.NewsstandException;
import uo.mp2122.util.log.Logger;

/**
 * This class helps to map a file to a collection of publications. In the file
 * each line has to be a different publication and the following format is
 * mandatory: [0] publication type [1] publication name [2] stock [3] sales [[4]
 * regularity]. The regularity only applies for magazines.
 * 
 * @author Programming Methodology Teching Staff.
 * @version 2023
 */
public class PublicationParser {
	private final static String NEWSPAPER_TYPE_NAME = "newspaper";
	private final static String MAGAZINE_TYPE_NAME = "magazine";

	private final static int PUBLICATION_TYPE_POS = 0;
	private final static int PUBLICATION_NAME_POS = 1;
	private final static int PUBLICATION_STOCK_POS = 2;
	private final static int PUBLICATION_SALES_POS = 3;
	private final static int PUBLICATION_FREQUENCY_POS = 4;

	private final static int MAXIMUM_LENGTH_STRINGS = 25;
	private final static int MINIMUM_NUMBER = 0;
	private final static int MAXIMUM_NUMBER = 50;

	private int lineNumber = 1;

	/**
	 * Transforms a list of Strings in a list of instances of Publication. Any of
	 * the following are invalid lines in the input file: - blank lines, - wrong
	 * number of fields in a line, and - incorrect data format in numeric fields.
	 * Invalid lines will not produce a Publication instance but will throw an
	 * InvalidLineFormatException instead. As a result of processing this exception,
	 * a message will be written to a log (use Log)
	 * 
	 * @param lines non-null list of strings, probably empty One by each publication
	 *              type_of_publication \t name_of_publication \t sales \t stock \t
	 *              frequency
	 * 
	 * @return a list of publications
	 */
	public List<Publication> parse(List<String> lines) {
		List<Publication> publications = new ArrayList<>();
		for (String line : lines) {
			try {
				Publication publication = parsePublication(line);
				if (publications.contains(publication)) {
					throw new NewsstandException(String.format("%s the line is empty", line));
				}
				publications.add(publication);

			} catch (IllegalLineFormatException | NewsstandException exception) {
				Logger.log(exception);
			}
			this.lineNumber++;
		}
		return publications;
	}

	private Publication parsePublication(String line) throws IllegalLineFormatException {
		if (line == null) {
			throw new IllegalLineFormatException(lineNumber, String.format("%s the line cannot be null", line));
		}
		if (line.isBlank()) {
			throw new IllegalLineFormatException(lineNumber, String.format("%s the line cannot be blank", line));
		}
		if (line.isEmpty()) {
			throw new IllegalLineFormatException(lineNumber, String.format("%s the line is empty", line));
		}

		String[] parts = line.split("\t");
		String type = parts[PUBLICATION_TYPE_POS];

		return switch (type) {
		case NEWSPAPER_TYPE_NAME -> parseNewspaper(parts);
		case MAGAZINE_TYPE_NAME -> parseMagazine(parts);
		default -> throw new IllegalLineFormatException(lineNumber, String.format("Type [%s] incorrect", type));
		};
	}

	private Newspaper parseNewspaper(String[] parts) throws IllegalLineFormatException {
		if (parts.length != 4) {
			throw new IllegalLineFormatException(lineNumber,
					String.format("%s the line has not the correct number of fields", parts.toString()));
		}
		String name = parts[PUBLICATION_NAME_POS];
		if (name.isBlank()) {
			throw new IllegalLineFormatException(this.lineNumber,
					String.format("wrong format, has a blank name for the newspaper"));
		}
		if (name.isEmpty()) {
			throw new IllegalLineFormatException(this.lineNumber,
					String.format("wrong format, has a blank name for the newspaper"));
		}
		if (name.length() > MAXIMUM_LENGTH_STRINGS) {
			throw new IllegalLineFormatException(this.lineNumber,
					String.format("wrong format, the name has more than %s characters for the newspaper"));
		}
		int stock = toInteger(parts[PUBLICATION_STOCK_POS]);
		if (stock < MINIMUM_NUMBER || stock > MAXIMUM_NUMBER) {
			throw new IllegalLineFormatException(this.lineNumber, String.format(
					"wrong format, the stock for the newspaper must be in [%s,%s]", MINIMUM_NUMBER, MAXIMUM_NUMBER));
		}
		int sales = toInteger(parts[PUBLICATION_SALES_POS]);
		if (sales < MINIMUM_NUMBER || sales > MAXIMUM_NUMBER) {
			throw new IllegalLineFormatException(this.lineNumber, String.format(
					"wrong format, the sales for the newspaper must be in [%s,%s]", MINIMUM_NUMBER, MAXIMUM_NUMBER));
		}
		return new Newspaper(name, stock, sales);
	}

	private Magazine parseMagazine(String[] parts) throws IllegalLineFormatException {
		if (parts.length != PUBLICATION_FREQUENCY_POS + 1) {
			throw new IllegalLineFormatException(lineNumber,
					String.format("The maganize must contain exactly %s fields but has %s",
							PUBLICATION_FREQUENCY_POS + 1, parts.length));
		}

		String name = parts[PUBLICATION_NAME_POS];
		if (name.isBlank()) {
			throw new IllegalLineFormatException(this.lineNumber,
					String.format("wrong format, has a blank name for the magazine"));
		}
		if (name.isEmpty()) {
			throw new IllegalLineFormatException(this.lineNumber,
					String.format("wrong format, has a blank name for the magazine"));
		}
		if (name.length() > MAXIMUM_LENGTH_STRINGS) {
			throw new IllegalLineFormatException(this.lineNumber,
					String.format("wrong format, the name has more than %s characters for the magazine"));
		}
		int stock = toInteger(parts[PUBLICATION_STOCK_POS]);
		if (stock < MINIMUM_NUMBER || stock > MAXIMUM_NUMBER) {
			throw new IllegalLineFormatException(this.lineNumber, String.format(
					"wrong format, the stock for the magazine must be in [%s,%s]", MINIMUM_NUMBER, MAXIMUM_NUMBER));
		}
		int sales = toInteger(parts[PUBLICATION_SALES_POS]);
		if (sales < MINIMUM_NUMBER || sales > MAXIMUM_NUMBER) {
			throw new IllegalLineFormatException(this.lineNumber, String.format(
					"wrong format, the sales for the magazine must be in [%s,%s]", MINIMUM_NUMBER, MAXIMUM_NUMBER));
		}
		int frequency = toInteger(parts[PUBLICATION_FREQUENCY_POS]);
		if (frequency < MINIMUM_NUMBER || frequency > MAXIMUM_NUMBER) {
			throw new IllegalLineFormatException(this.lineNumber, String.format(
					"wrong format, the frequency for the magazine must be in [%s,%s]", MINIMUM_NUMBER, MAXIMUM_NUMBER));
		}
		return new Magazine(name, stock, sales, frequency);
	}

	private int toInteger(String string) throws IllegalLineFormatException {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException exception) {
			throw new IllegalLineFormatException(this.lineNumber,
					String.format("%s cannot be converted to an integer", string));
		}
	}

}
