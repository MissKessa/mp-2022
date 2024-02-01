package uo.mp.exception.util;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Console {
	
	private static PrintStream out = System.out;
	private static InputStream in = System.in;
	
	private static Scanner keyboard = new Scanner( in );

	public static void setOut(PrintStream out) {
		Console.out = out;
	}

	public static void setIn(InputStream in) {
		Console.in = in;
		keyboard = new Scanner( Console.in );
	}

	public static String readString(String msg) {
		out.println( msg + ": ");
		return keyboard.next();
	}

	public static double readIntegerFromKeyboard(String msg) {
		out.println( msg + ": ");
		return keyboard.nextInt();
	}

	public static void show(String msg) {
		out.println( msg );
	}

}
