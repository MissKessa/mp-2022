package uo.mp.filter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import uo.mp.filter.encrypt.EncryptionOutputStream;

public class FileEncryptor {

	public static void main(String[] args) throws IOException {
		String fileName = "lorem-ipsum.txt";
		
		InputStream input = 
				new BufferedInputStream(
					new FileInputStream( fileName )
				);

		OutputStream output = 
				new EncryptionOutputStream(
					new BufferedOutputStream(
						new FileOutputStream( fileName + ".crypt")
					)
				);

		int aByte = 0;
		while ( (aByte = input.read()) != -1) {
			output.write(aByte);
		}
		
		input.close();
		output.close();
	}

}
