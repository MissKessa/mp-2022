package uo.mp.text;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * In this case we are using a FileReader. In that way we get chars encoded
 * using an codification scheme (in this case the default codification)
 */
public class ReadTextWithFileReader {

	public static void main(String[] args) {
		try {
			
			Reader in = new FileReader("sample.txt");
			try {
				int ch;
				while ((ch = in.read()) != -1) {
					System.out.print((char) ch);
				}
			} finally {
				in.close();
			}
			
		} catch (FileNotFoundException nfe) {
			System.out.println("The file does not exist");
		} catch (IOException ex) {
			System.err.println("System error rerading the file");
		}
	}
}
