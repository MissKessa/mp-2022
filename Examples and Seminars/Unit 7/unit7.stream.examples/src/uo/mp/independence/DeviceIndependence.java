package uo.mp.independence;

import java.io.CharArrayReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class DeviceIndependence {

	public static void main(String[] args) throws IOException {
		
		Reader reader = new FileReader( "lorem-ipsum.txt" );
		showInConsole(reader);
		
		reader = new StringReader("This is a string stream in memory.");
		showInConsole(reader);

		char[] array = "These are chars in an array in memory.".toCharArray();
		reader = new CharArrayReader( array );
		showInConsole(reader);
	}

	private static void showInConsole(Reader reader) throws IOException {
		int character = 0;
		while ((character = reader.read()) != -1) {
			System.out.print((char) character);
		}
		reader.close();
	}

}
