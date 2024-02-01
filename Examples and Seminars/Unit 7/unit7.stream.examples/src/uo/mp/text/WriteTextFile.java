package uo.mp.text;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriteTextFile {

	public static void main(String[] args) {
		writeFile();
	}

	private static void writeFile() {
		try {
			Writer file = new FileWriter( "table.txt" );
			try {
				for (int i = 0; i <= 10; i++) {
					file.write(
							"2 ^ " + i + " = " 
							+ (int) Math.pow(2, i)
							+ ".\r\n"
					);
				}
			} finally {
				file.close();
			}

		} catch (IOException e) {
			System.err.println("Cannot write in the file.");

		}
	}

}
