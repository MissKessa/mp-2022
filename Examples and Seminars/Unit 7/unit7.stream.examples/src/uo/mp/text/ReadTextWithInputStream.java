package uo.mp.text;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * If we read a text file assuming that every byte is a character we get errors.
 * That is no so simple. Due to text codifications UTF-8, UTF-16, etc., we 
 * would need to process and analyze sequences of bytes following the 
 * codification scheme.  
 * 
 * The sample.txt file used in this example is codified in UTF-8. In that case
 * some characters such as ¿ á, é, í, ó, ú, ñ, Ñ are codified with more than 
 * one byte
 * 
 * @author alb
 *
 */
public class ReadTextWithInputStream {

	public static void main(String[] args) throws IOException {
		
		InputStream in = new FileInputStream( "sample.txt" );
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
