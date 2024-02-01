package uo.mp.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BasicInputStreamReadingDemoWithException {

	public static void main(String[] args) {
		new BasicInputStreamReadingDemoWithException().run();
	}

	private void run() {
		try {
			InputStream in = new FileInputStream("data.dat");
			try {
				int b;
				while ((b = in.read()) != -1) {
					System.out.println( (byte) b);
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
