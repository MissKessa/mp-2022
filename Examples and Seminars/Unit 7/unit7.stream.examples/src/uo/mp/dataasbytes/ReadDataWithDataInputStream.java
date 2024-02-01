package uo.mp.dataasbytes;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class ReadDataWithDataInputStream {
	private int i;
	private double d;
	private byte b;
	private char c;
	
	public static void main(String[] args) throws IOException {
		new ReadDataWithDataInputStream().run();
	}

	private void run() throws IOException {
		String file = "data.dat";
		DataInputStream in = new DataInputStream( new FileInputStream( file ));
		
		try {
			i = in.readInt();
			d = in.readDouble();
			b = in.readByte();
			c = in.readChar();
		}
		finally {
			in.close();
		}
		
		assert( i == 12345 );
		assert( d == 1234.456 );
		assert( b == -127 );
		assert( c == 'Z' );
	}
}
