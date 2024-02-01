package uo.mp.buffered;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderDemo {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader("lorem-ipsum.txt"));
		try {
			String line;
			while ( (line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} finally {
			reader.close();
		}
	}

}
