package uo.mp2122.newsstand.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import uo.mp.util.check.ArgumentChecks;
import uo.mp2122.newsstand.domain.Order;
import uo.mp2122.newsstand.domain.Publication;
import uo.mp2122.newsstand.exception.NewsstandException;
import uo.mp2122.newsstand.service.parsers.PublicationParser;
import uo.mp2122.newsstand.service.serializers.OrderSerializer;
import uo.mp2122.newsstand.service.serializers.PublicationSerializer;
import uo.mp2122.util.file.FileUtil;
import uo.mp2122.util.file.ZipFileUtil;
import uo.mp2122.util.log.Logger;

public class Newsstand {

	private List<Publication> publications = new LinkedList<>();
	private List<Order> orders = new LinkedList<>();

	/**
	 * Loads all the products contained in the file into publications. None
	 * publication can be repeated regarding its name. If a publication is with a
	 * repeated name is already registered it will be ignored and a message sent to
	 * log.
	 * 
	 * @param inFileName, the name of the file
	 * @throws NewsstandException, if the file name is invalid
	 * @throws IOException
	 */
	public void loadFile(String inFileName) throws NewsstandException, FileNotFoundException {
		ArgumentChecks.isTrue(inFileName != null, "The file name cannot be null");
		List<String> lines = new FileUtil().readLines(inFileName);
		List<Publication> fileProducts = new PublicationParser().parse(lines);
		addPublications(fileProducts);
	}

	/**
	 * Add all the publications contained on the list passed as argument. None
	 * publication can be repeated regarding its name. If a publication is with a
	 * repeated name is already registered it will be ignored and a message sent to
	 * log.
	 * 
	 * @param newProducts, the list with the new publications
	 * @throws NewsstandException
	 */
	private void addPublications(List<Publication> newProducts) {
		ArgumentChecks.isTrue(newProducts != null);
		for (Publication p : newProducts) {
			try {
				addPublication(p);
			} catch (NewsstandException e) {
				Logger.log(e);
			}
		}
	}

	/**
	 * Add the publication if it is name is not already registered
	 * 
	 * @param p, the product to be added
	 * @throws NewsstandException if the product's name is repeated
	 */
	public void addPublication(Publication p) throws NewsstandException {
		if (p == null) {
			throw new NewsstandException("The publication cannot be null");
		}
		if (publications.contains(p))
			throw new NewsstandException("The publication is already on the list");
		publications.add(p);

//		ArgumentChecks.isTrue(p != null);
//		if (searchByName(p.getName()) == -1) {
//			publications.add(p);
//		} else {
//			throw new NewsstandException("The publication "+p.getName() +"is already on the list");
//		}

	}

	/**
	 * Removes the product whose indicated by its name
	 * 
	 * @param name of the publication to be removed
	 * @throws NewsstandException
	 * @throws NewsstandExceptionif the product does not exist
	 */
	public void removePublication(String name) throws NewsstandException {
		ArgumentChecks.isTrue(name != null && !name.isBlank());
		int pos = searchByName(name);
		if (pos == -1)
			throw new NewsstandException("The publication is not on the list");
		publications.remove(pos);
	}

	private int searchByName(String name) throws NewsstandException {
		if (name == null)
			throw new NewsstandException("The name of the publication is null");
		for (int i = 0; i < publications.size(); i++) {
			Publication p = publications.get(i);
			if ((p.getName().equals(name)) /* compare both names */ )
				return i;
		}
		return -1;
	}

	/**
	 * @return a list which a copy of the internal list of publications
	 */
	public List<Publication> getPublications() {
		// Returns a list, copy of our own list to not break the encapsulation
		// A copy constructor needed in our lists
		List<Publication> copy = new ArrayList<Publication>();
		for (Publication publication : this.publications)
			copy.add(publication);
		return copy;
	}

	/**
	 * Generates a list of orders. One for every product with stock under limits
	 */
	public void createOrders() {
		for (Publication publication : this.publications) {
			if (publication.getStock() < publication.getSales())
				if (publication.generateOrders() != null)
					orders.add(publication.generateOrders());
		}
	}

	/**
	 * @return a list which a copy of the internal list of orders
	 */
	public List<Order> getOrders() {
		// Returns a list, copy of our own list to not break the encapsulation
		// A copy constructor needed in our lists
		return new ArrayList<>(orders);
	}

	/**
	 * Save all orders to a file with the given format
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws NewsstandException    in case - the file name is invalid
	 */
	public void saveOrdersToFile(String fileName) throws FileNotFoundException {
		OrderSerializer serializer = new OrderSerializer();
		List<String> lines = serializer.serialize(orders);
		new FileUtil().writeLines(fileName, lines);
	}

	/**
	 * Imports all the publications from the zip file and removes all the previous
	 * ones.
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public void importPublicationsFromZipFile(String fileName) throws FileNotFoundException {
		ArgumentChecks.isTrue(fileName != null, "The file name cannot be null");
		List<String> lines = new ZipFileUtil().readLines(fileName);
		List<Publication> fileProducts = new PublicationParser().parse(lines);
		addPublications(fileProducts);
	}

	/**
	 * Saves all the publications to a zip file. The file produced can be read by
	 * the method @see importPublicationsFromZipFile
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public void exportPublicationsToZipFile(String fileName) throws FileNotFoundException {
		PublicationSerializer serializer = new PublicationSerializer();
		List<String> lines = serializer.serialize(publications);
		new ZipFileUtil().writeLines(fileName, lines);
	}

}
