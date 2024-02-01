package uo.mp.buffered;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterDemo {

	public static void main(String[] args) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter("table.txt"));
		try {
		
			for(int i = 0; i <= 10; i++) {
				String line = "2 * " + i + " = " + i*2; 
				
				writer.write( line );
				writer.newLine();
			}
		
		} finally {
			writer.close();
		}
	}

}
