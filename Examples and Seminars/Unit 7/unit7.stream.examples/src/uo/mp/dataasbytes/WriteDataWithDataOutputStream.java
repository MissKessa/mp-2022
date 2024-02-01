package uo.mp.dataasbytes;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class WriteDataWithDataOutputStream {
	private int i = 12345;
	private double d = 1234.456;
	private byte b = -127;
	private char c = 'Z';
	
	public static void main(String[] args) throws IOException {
		new WriteDataWithDataOutputStream().run();
	}

	private void run() throws IOException {
		DataOutputStream out = new DataOutputStream( 
				new FileOutputStream( "data.dat" )
			);
		try {
			out.writeInt( i );
			out.writeDouble( d );
			out.writeByte( b );
			out.writeChar( c );
		}
		finally {
			out.close();
		}
	}

}
