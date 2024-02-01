package uo.mp.text;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * By using a filter we can simplify the task of byte to character conversion.
 * In this case we are using and InputStreamReader to filter (convert) the bytes
 * got with the binary FileInputStream. In that way we get chars encoded using 
 * an codification scheme indicated by the second parameter of the 
 * InputStreamReader
 * 
 */
public class ReadTextWithInputStreamConvertedToReader {

	public static void main(String[] args) throws IOException {
		
		Reader in = new InputStreamReader( 
						new FileInputStream( "sample.txt" )
					);
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
