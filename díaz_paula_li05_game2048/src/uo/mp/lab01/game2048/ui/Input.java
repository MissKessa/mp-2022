package uo.mp.lab01.game2048.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class abstracts the data input functionalities in our program. It represents the keyboard
 * and allows us to wait for the user to press a key and know which key it is.
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class Input {

  /**
   * When this method is invoked it blocks the execution of the program and waits until some input
   * is received from the standard input. In the current implementation the standard input is the
   * keyboard. So this method blocks the execution of the program until the user presses a key on
   * the keyboard. Then the value of that key is processed as a character and the value is returned.
   * If, from the invocation until the value of the pressed key is read, any exception occurs then
   * an error message is printed on the standard output and the program terminates with code 0.
   * 
   * @return the corresponding value, as a character, of the keyboard key pressed by the user.
   */
  static char waitAndReadCharFromKeyboard() {
    InputStreamReader streamReader = new InputStreamReader(System.in);
    BufferedReader bufferReader = new BufferedReader(streamReader);
    char character = 0;
    try {
      character = (char) bufferReader.read();
    } catch (IOException e) {
      System.out.println("An error has happen with the data input!");
      System.exit(0);
    }
    return character;
  }

}
