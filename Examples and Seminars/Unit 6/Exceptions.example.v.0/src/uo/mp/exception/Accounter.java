package uo.mp.exception;

import java.io.FileNotFoundException;
import java.util.List;

public class Accounter {

	private List<String> lines;

	public Accounter(String fileName) {
		lines = readLinesOnFile(fileName);
	}

	public double processFile() {
		double res = 0.0;
		for (String l : lines) {
			res += processLine( l );
		}
		return res;
	}

	/**
	 * Returns a list of String, one for each line read from the file
	 * 
	 * @param fName,
	 *            the file name to be read
	 * @return
	 * @throws FileNotFoundException
	 */
	private List<String> readLinesOnFile(String fName) {
		FileReader f = new FileReader(fName);
		List<String> lines = f.readLines();
		f.close();
		return lines;
	}

	/**
	 * Returns the double contained in the third field of the line Each line has
	 * this format: <date>;<description>;<amount> Being the ';' the field
	 * separator
	 * 
	 * @param line
	 * @return the <amount> value converted to double
	 * @throws LineFormatException 
	 */
	private double processLine(String line) {
		String[] parts = line.split( ";" );

		return Double.parseDouble(parts[2]);
	}

}
