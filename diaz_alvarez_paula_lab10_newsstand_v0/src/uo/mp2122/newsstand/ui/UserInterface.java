package uo.mp2122.newsstand.ui;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import uo.mp2122.newsstand.comparators.PublicationCompoundComparator;
import uo.mp2122.newsstand.domain.Publication;
import uo.mp2122.newsstand.exception.NewsstandException;
import uo.mp2122.newsstand.service.Newsstand;
import uo.mp2122.newsstand.ui.console.Console;
import uo.mp2122.util.log.Logger;

/**
 * It is in charge of interacting with the user: - Shows the menu of options -
 * Process the option chosen by the user - For that it asks the user the
 * necessary data to fulfill the request - Shows the result of the request - In
 * case of error shows an explaining message
 * 
 * Note: This is the unique class allowed to show information to the user
 */
public class UserInterface {
	private static final int EXIT = 0;

	private Menu menu = new Menu();
	private Newsstand newsStand = new Newsstand();

	public void show() {
		int option = EXIT;
		do {
			menu.show();
			option = menu.readOption();
			try {
				processOption(option);
			} catch (RuntimeException e) {
				handleSystemError(e);
				return;
			} catch (Exception e) {
				handleUserError(e);
			}
		} while (option != EXIT);

	}

	private void handleUserError(Exception e) {
		String message = "There has ben a recovarable error:" + e.getMessage() + "please, try again";
		Console.println(message);
		Logger.log(e);

	}

	private void handleSystemError(RuntimeException e) {
		String message = "There has ben a unrecovarable error:" + e.getMessage()
				+ "please, contact with the suministrador? of the app";
		Console.println(message);
		Logger.log(e);

	}

	private void processOption(int option) throws RuntimeException, Exception {
		switch (option) {
		case EXIT:
			return;
		case 1:
			loadFile();
			break;
		case 2:
			showPublications();
			break;
		case 3:
			addPublication();
			break;
		case 4:
			removePublication();
			break;
		case 5:
			createOrders();
			break;
		case 6:
			saveOrdersToFile();
			break;
		case 7:
			importFromZip();
			break;
		case 8:
			exportToZip();
			break;
		}
	}

	private void loadFile() throws NewsstandException, FileNotFoundException {
		String fileName = Console.readString("File name?");
		if (fileName.length() < 5) {
			throw new NewsstandException("The file name must have length 5 or greater");
		}
		newsStand.loadFile(fileName);
	}

	private void addPublication() throws NewsstandException {
		Publication p = new PublicationForm().askForPublication();
		newsStand.addPublication(p);
	}

	private void removePublication() throws NewsstandException {
		String name = Console.readString("publication name?");
		newsStand.removePublication(name);
	}

	private void showPublications() {
		List<Publication> publications = newsStand.getPublications();
		Collections.sort(publications, new PublicationCompoundComparator());
		listPublications(publications);
	}

	private void createOrders() {
		newsStand.createOrders();
	}

	private void listPublications(List<Publication> publications) {
		Console.println("\nList of publications");
		Console.println("------------------");
		for (Publication p : publications) {
			System.out.println(p);
		}

		Console.println("------------------");
	}

	private void saveOrdersToFile() throws FileNotFoundException {
		String fileName = Console.readString("output file name?");
		newsStand.saveOrdersToFile(fileName);
	}

	private void importFromZip() throws FileNotFoundException {
		String fileName = Console.readString("input zip file name?");
		newsStand.importPublicationsFromZipFile(fileName);
	}

	private void exportToZip() throws FileNotFoundException {
		String fileName = Console.readString("output file name?");
		newsStand.exportPublicationsToZipFile(fileName);
	}

}
