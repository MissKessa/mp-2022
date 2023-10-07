package uo.mp.lab01.game2048;

import uo.mp.lab01.game2048.ui.GameApp;

/**
 * Main entry and implementation point for the Game2048 program. The execution of the Game2048
 * program is initiated from this class.
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class Main {

  /**
   * Main method of starting execution for the {@code Game2048} program. This method delegates to
   * the run method of the {@code GameApp} class.
   * 
   * @param args are the arguments that are passed to the program execution command. No argument is
   *        expected but if passed will be ignored.
   */
  public static void main(String[] args) {
    new GameApp().init(); // Create a new GameApp instance and invoke the init method.
  }

}
