package uo.mp.keyboard;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class ReadingFromKeyboard {

	public static void main(String[] args) throws IOException {
		
		Reader in = new InputStreamReader( System.in );
		
		while( true ) {
			int i = in.read();
			
			System.out.println( (char)i + " " + i);
		}
	}

}
