package uo.mp2122.newsstand.ui;

import uo.mp2122.newsstand.domain.Magazine;
import uo.mp2122.newsstand.domain.Newspaper;
import uo.mp2122.newsstand.domain.Publication;
import uo.mp2122.newsstand.exception.NewsstandException;
import uo.mp2122.newsstand.ui.console.Console;

/**
 * Asks the user all the data for a new publication.
 * 
 */
public class PublicationForm {
	private final static int MAXIMUM_LENGTH_STRINGS = 25;
	private final static int MINIMUM_NUMBER = 50;
	private final static int MAXIMUM_NUMBER = 50;

	/**
	 * @return The new Publication object created. It will be be of any of the child
	 *         types of Publication.
	 * @throws NewsstandException
	 */
	public Publication askForPublication() throws NewsstandException {
		String type = Console.readString("Type of publication? (n | m)");
		type = type.toLowerCase();
		switch (type) {
		case "n":
			return askForNewsPaper();
		case "m":
			return askForMagazine();
		default:
			return null;
		}

	}

	private Publication askForMagazine() throws NewsstandException {
		String name = Console.readString("name?");
		int stock = Console.readInteger("stock?");
		int sales = Console.readInteger("sales?");
		int regularity = Console.readInteger("frequency?");
		if (name.isBlank()) {
			throw new NewsstandException(String.format("wrong format, has a blank name for the magazine"));
		}
		if (name.isEmpty()) {
			throw new NewsstandException(String.format("wrong format, has a blank name for the magazine"));
		}
		if (name.length() > MAXIMUM_LENGTH_STRINGS) {
			throw new NewsstandException(
					String.format("wrong format, the name has more than %s characters for the magazine"));
		}
		if (stock < MINIMUM_NUMBER || stock > MAXIMUM_NUMBER) {
			throw new NewsstandException(String.format("wrong format, the stock for the magazine must be in [%s,%s]",
					MINIMUM_NUMBER, MAXIMUM_NUMBER));
		}
		if (sales < MINIMUM_NUMBER || sales > MAXIMUM_NUMBER) {
			throw new NewsstandException(String.format("wrong format, the sales for the magazine must be in [%s,%s]",
					MINIMUM_NUMBER, MAXIMUM_NUMBER));
		}
		if (regularity < MINIMUM_NUMBER || regularity > MAXIMUM_NUMBER) {
			throw new NewsstandException(
					String.format("wrong format, the regularity for the magazine must be in [%s,%s]", MINIMUM_NUMBER,
							MAXIMUM_NUMBER));
		}

		return new Magazine(name, stock, sales, regularity);
	}

	private Publication askForNewsPaper() throws NewsstandException {
		String name = Console.readString("name?");
		int stock = Console.readInteger("stock?");
		int sales = Console.readInteger("sales?");

		if (name.isBlank()) {
			throw new NewsstandException(String.format("wrong format, has a blank name for the newspaper"));
		}
		if (name.isEmpty()) {
			throw new NewsstandException(String.format("wrong format, has a blank name for the newspaper"));
		}
		if (name.length() > MAXIMUM_LENGTH_STRINGS) {
			throw new NewsstandException(
					String.format("wrong format, the name has more than %s characters for the newspaper"));
		}
		if (stock < MINIMUM_NUMBER || stock > MAXIMUM_NUMBER) {
			throw new NewsstandException(String.format("wrong format, the stock for the newspaper must be in [%s,%s]",
					MINIMUM_NUMBER, MAXIMUM_NUMBER));
		}
		if (sales < MINIMUM_NUMBER || sales > MAXIMUM_NUMBER) {
			throw new NewsstandException(String.format("wrong format, the sales for the newspaper must be in [%s,%s]",
					MINIMUM_NUMBER, MAXIMUM_NUMBER));
		}
		return new Newspaper(name, stock, sales);
	}

}
