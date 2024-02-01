package uo.mp.keyboard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadingStringsFromKeyboard {

	public static void main(String[] args) throws IOException {
	
		BufferedReader keyboard = 
				new BufferedReader(
						new InputStreamReader( System.in )
				);

		String line = "";
		do {
			line = keyboard.readLine();
			System.out.println( line );
				
		} while ( ! line.equals("quit") );
		
		System.out.println( "Finished" );
	}

}
