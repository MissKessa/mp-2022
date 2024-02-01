package uo.mp.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BasicInputStreamReadingDemo {

	public static void main(String[] args) throws IOException {
		new BasicInputStreamReadingDemo().run();
	}

	private void run() throws IOException {
		
		InputStream in = new FileInputStream("data.dat");
		try {
			int b;
			while ( (b = in.read()) != -1 ) {
				System.out.println( b );
			}
		} finally {
			in.close();
		}
	}

}
