package uo.mp.text;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * In this case we are using a FileReader. In that way we get chars 
 * encoded using an codification scheme (in this case the default codification)
 */
public class ReadTextWithFileReaderExceptions {

	public static void main(String[] args) throws IOException {
		
		Reader in = new FileReader( "sample.txt" );
		try {
			int ch;
			while( (ch = in.read()) != -1 ) {
				System.out.print( (char) ch );
			}
		} finally {
			in.close();
		}
	}
}
