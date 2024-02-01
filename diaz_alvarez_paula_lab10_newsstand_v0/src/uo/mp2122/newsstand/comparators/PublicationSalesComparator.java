package uo.mp2122.newsstand.comparators;

import java.util.Comparator;

import uo.mp2122.newsstand.domain.Publication;

public class PublicationSalesComparator implements Comparator<Publication> {

	@Override
	public int compare(Publication publication1, Publication publication2) {
		return publication1.getSales() - publication2.getSales();
	}

}
