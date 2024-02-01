package uo.mp.exception;
import java.util.Arrays;
import java.util.List;

/**
 * This is a fake file reader. 
 * We use it instead of a real FileReader to not add extra complexities.
 * When we use this code Streams are not covered yet. 
 * 
 * @author alb
 */
public class FileReader {

	public FileReader(String fName) { // throws FileNotFoundException
		if ( ! "data.dat".equals( fName )) {
			throw new RuntimeException("The file does not exist");
		}
	}

	public List<String> readLines() { // throws IOException
		return Arrays.asList( new String[]{
				"01/12/2015;Initial balance;1000.00",
				"12/12/2015;Expenses in the supermarket;-100.00",
				"13/12/2015;Salary;3000.00",
				"13/12/2015;Credit card clearing;-1000.00"
			});
	}

	public void close() { // throws IOException {
		// TODO Auto-generated method stub
	}

}
