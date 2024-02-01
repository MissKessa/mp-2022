package uo.mp2122.newsstand.comparators;

import java.util.Comparator;

import uo.mp2122.newsstand.domain.Publication;

public class PublicationCompoundComparator implements Comparator<Publication> {

	@Override
	public int compare(Publication publication1, Publication publication2) {
		final int compareToNames = publication1.getName().compareTo(publication2.getName());
		final int compareToSales = publication1.getSales() - publication2.getSales();

		if (compareToNames == 0) {
			return compareToSales;
		}
		return compareToNames;
		// return compareToName==0? compareToSales:compareToNames;
	}

}
