
package uo.mp2122.newsstand.ui.console;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Offers utility methods for user-console interaction
 */
public class Console {

  private static PrintStream out = System.out;
  private static Scanner keyboard = new Scanner(System.in);

  public static String readString(String msg) {
    out.println(msg + ": ");
    keyboard.useDelimiter(System.lineSeparator());
    String res = keyboard.next();
    keyboard.reset();
    return res;
  }

  public static int readInteger(String msg) {
    out.println(msg + ": ");
    return keyboard.nextInt();
  }

  public static void println(String msg) {
    out.println(msg);
  }

  public static void printf(String fmt, Object... params) {
    out.printf(fmt, params);
  }

}
