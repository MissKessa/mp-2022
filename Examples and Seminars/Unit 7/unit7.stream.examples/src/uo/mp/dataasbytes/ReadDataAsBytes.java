package uo.mp.dataasbytes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ReadDataAsBytes {
	private int i;
	private double d;
	private byte b;
	private char c;
	
	public static void main(String[] args) throws IOException {
		new ReadDataAsBytes().run();
	}

	private void run() throws IOException {
		InputStream in = new FileInputStream( "data.dat" );
		try {
			i = readInteger( in );
			d = readDouble( in );
			b = readByte( in );
			c = readChar( in );
		}
		finally {
			in.close();
		}
		
		assert( i == 12345 );
		assert( d == 1234.456 );
		assert( b == -127 );
		assert( c == 'Z' );

	}

	private char readChar(InputStream in) throws IOException {
		char res = 0;
		res = (char)(in.read() << 8);
		res |= (char)(in.read());
		return res;
	}

	private byte readByte(InputStream in) throws IOException {
		return (byte)in.read();
	}

	private double readDouble(InputStream in) throws IOException {
		long res = 0;
		res = (long)(in.read() << 56);
		res |= (long)(in.read() << 48);
		res |= (long)(in.read() << 40);
		res |= (long)(in.read() << 32);
		res |= (long)(in.read() << 24);
		res |= (long)(in.read() << 16);
		res |= (long)(in.read() << 8);
		res |= (long)(in.read());
		return Double.longBitsToDouble( res );
	}

	private int readInteger(InputStream in) throws IOException {
		int res = 0;
		res = (int)(in.read() << 24);
		res |= (int)(in.read() << 16);
		res |= (int)(in.read() << 8);
		res |= (int)(in.read());
		return res;
	}

}
