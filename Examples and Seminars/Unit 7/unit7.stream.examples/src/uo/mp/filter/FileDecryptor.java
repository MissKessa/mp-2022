package uo.mp.filter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import uo.mp.filter.encrypt.DecryptionInputStream;

public class FileDecryptor {

	public static void main(String[] args) throws IOException {
		String fileName = "lorem-ipsum.txt.crypt";
		
		InputStream input = 
			new DecryptionInputStream(
				new BufferedInputStream(
					new FileInputStream( fileName )
				)
			);

		OutputStream output = 
			new BufferedOutputStream(
				new FileOutputStream( fileName + ".decrypt")
			);

		int aByte = 0;
		while ( (aByte = input.read()) != -1) {
			output.write(aByte);
		}
		
		input.close();
		output.close();
	}

}
