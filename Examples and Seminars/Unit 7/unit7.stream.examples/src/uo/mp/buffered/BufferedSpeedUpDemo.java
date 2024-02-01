package uo.mp.buffered;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BufferedSpeedUpDemo {

	public static void main(String[] args) throws IOException {
		
		long start = System.currentTimeMillis();
		copyFile();
		long finish = System.currentTimeMillis();
		
		System.out.println( "It has taken " + (finish - start) );
	}

	private static void copyFile() throws FileNotFoundException, IOException {
		InputStream in = new BufferedInputStream( new FileInputStream( "P2075270.jpg" ) );
		OutputStream out = new BufferedOutputStream( new FileOutputStream( "P2075270.copy.jpg" ));
//		InputStream in = new FileInputStream( "P2075270.jpg" );
//		OutputStream out = new FileOutputStream( "P2075270.copy.jpg" );
		
		int byteValue;
		while( (byteValue = in.read()) != -1 ) {
			out.write(byteValue);
		}
		in.close();
		out.close();
	}

}
