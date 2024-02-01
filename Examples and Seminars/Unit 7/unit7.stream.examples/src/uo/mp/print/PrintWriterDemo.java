package uo.mp.print;

import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterDemo {

	public static void main(String[] args) throws IOException {
		
		PrintWriter writer = new PrintWriter( System.out );
		for(int i = 0; i <= 10; i++) {
			writer.println( "2 * " + i + " = " + i*2 );
		}
		writer.close();
		
	}

}
