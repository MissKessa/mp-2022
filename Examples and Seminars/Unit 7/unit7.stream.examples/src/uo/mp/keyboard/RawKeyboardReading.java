package uo.mp.keyboard;

import java.io.IOException;

public class RawKeyboardReading {

	public static void main(String[] args) throws IOException {
		
		while( true ) {
			int i = System.in.read();
			
			System.out.println( (char)i + " " + i);
		}
	}

}
