package uo.mp.dataasbytes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class WriteDataAsBytes {
	private int i = 12345;
	private double d = 1234.456;
	private byte b = -127;
	private char c = 'Z';
	
	public static void main(String[] args) throws IOException {
		new WriteDataAsBytes().run();
	}

	private void run() throws IOException {
		OutputStream out = new FileOutputStream( "data.dat");
		try {
			out.write( toByteArray( i ) );
			out.write( toByteArray( d ) );
			out.write( toByteArray( b ) );
			out.write( toByteArray( c ) );
		}
		finally {
			out.close();
		}
	}

	private byte[] toByteArray(double d) {
		long value = Double.doubleToLongBits( d );
		return new byte[] {
			(byte) (value >> 56),
			(byte) (value >> 48),
			(byte) (value >> 40),
			(byte) (value >> 32),
			(byte) (value >> 24),
			(byte) (value >> 16),
			(byte) (value >> 8),
			(byte) value
		};
	}

	private static byte[] toByteArray(int value) {
		return new byte[] {
				(byte) (value >> 24),
				(byte) (value >> 16),
				(byte) (value >> 8),
				(byte) value
			};
	}

	private static byte[] toByteArray(char value) {
		return new byte[] {
				(byte) (value >> 8),
				(byte) value
			};
	}

	private static byte[] toByteArray(byte value) {
		return new byte[] {
				(byte) value
			};
	}

}
