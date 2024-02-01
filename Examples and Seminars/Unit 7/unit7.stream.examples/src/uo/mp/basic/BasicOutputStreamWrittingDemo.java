package uo.mp.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BasicOutputStreamWrittingDemo {

	public static void main(String[] args) throws IOException {
		new BasicOutputStreamWrittingDemo().run();
	}

	private void run() throws IOException {
		
		OutputStream out = new FileOutputStream("data2.dat");
		try {
			for(byte b = -128; b < 127; b++) {
				out.write(b);
			}
		} finally {
			out.close();
		}
	}

}
