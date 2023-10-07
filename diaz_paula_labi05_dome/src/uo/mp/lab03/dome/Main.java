package uo.mp.lab03.dome;

import uo.mp.lab03.dome.model.Cd;
import uo.mp.lab03.dome.model.Dvd;
import uo.mp.lab03.dome.service.MediaLibrary;

public class Main {

	public static void main(String[] args) {

		// Create a media library.
		final MediaLibrary myMediaLibrary = new MediaLibrary();
		final Dvd theGoodfatherDvd = new Dvd("The Godfather", 10, "F. F. Koppola", 256);
		final Cd alchemyCd = new Cd("Alchemy", 10, "Dire Straits", 12, 75);
		alchemyCd.setInStock(true);

		// Add those created items to the library.
		myMediaLibrary.add(theGoodfatherDvd);
		myMediaLibrary.add(alchemyCd);

		// Print the data from the library.
		System.out.println("--- Welcome to myMediaLibrary ---");
		System.out.println(myMediaLibrary);
		System.out.printf("Number of items in stock: %d\n", myMediaLibrary.getNumberOfItemInStock());
		System.out.printf("Items:\n%s", myMediaLibrary.getItemsAsString());
	}

}
