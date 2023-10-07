package uo.mp.lab01.game2048.ui;

import uo.mp.lab01.game2048.model.Game2048;

/**
 * This class represents the core of the graphical interface of our game2048. It really does nothing
 * more than routing the different actions that the user does against the GUI over the game logic
 * (where the game is actually implemented). Our graphical interface allows to start the game,
 * process the movements the user makes and ask if he/she wants to continue playing.
 * <p>
 * 2048 is a game created by Gabriele Cirulli. The objective is to combine numbers (powers of 2) to
 * get the number 2048 and then win the play. To move the numbers on the board you choose the
 * movement direction (up, down, left and right) with the keyboard. Then the numbers move in the
 * chosen direction and one of this things may happen: - If the next-in-the-direction-to-move cell's
 * value is free the value changes to that cell - If the current value is equal to the
 * next-in-the-direction-to-move cell's value then both values add up in the next cell - If the
 * current value is different from the movement is blocked
 * 
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class GameApp {

  private final static String GAME_TITLE_MESSAGE = "2048 GAME\n";
  private final static String TRY_AGAIN_MESSAGE = "Do you want another try?";
  private final static String GAME_OVER_MESSAGE = "GAME OVER\n";
  private final static String CHOOSE_MOVEMENT_MESSAGE = "Choose movement [r R]/[l L]/[u U]/[d D]: ";
  private final static String NO_VALID_MOVEMENT_MESSAGE = "No valid movement!\n";
  private final static char POSITIVE_ANSWER = 'y';
  private final static char NEGATIVE_ANSWER = 'n';

  private final Game2048 game = new Game2048();

  /**
   * This method starts the 2048 game and as long as the user wants to continue playing rounds, it
   * keeps the game active. If the user selects that he/she does not want to continue playing a game
   * then the game is terminated.
   */
  public void init() {
    do {
      playRound();
    } while (doesUserWantsAnotherRound());
  }

  /**
   * This method uses the standard output (command line) to ask the user if they want to play
   * another round. It then waits for the user to enter the answer on the same command line and if
   * the answer is yes then returns true. Otherwise, it returns false.
   * 
   * @return true if the user wants to play another round. False otherwise.
   */
  private boolean doesUserWantsAnotherRound() {
    Output.printPositiveNegativeQuestion(TRY_AGAIN_MESSAGE, POSITIVE_ANSWER, NEGATIVE_ANSWER);
    return Input.waitAndReadCharFromKeyboard() == POSITIVE_ANSWER;
  }

  /**
   * A round is a complete game. That is, from the moment it starts until it is won or lost. Within
   * this method, the logic of each of the rolls is controlled, processing the movements that the
   * user types on the keyboard and that are transferred to methods of the logic of the game.
   */
  private void playRound() {
    Output.printMessage(GAME_TITLE_MESSAGE);

    do {
      // Do a game iteration and print the board.
      game.next();
      Output.printBoard(game.getBoard());

      // Ask for the movement to execute to the user.
      Output.printMessage(CHOOSE_MOVEMENT_MESSAGE);
      char userMovement = Input.waitAndReadCharFromKeyboard();

      // Process the movement.
      processMovement(userMovement);

    } while (!game.isGameFinished());

    Output.printMessage(GAME_OVER_MESSAGE);
  }

  /**
   * This method processes the movement given as a parameter. If the given movement is not a valid
   * movement then the user is warned of this event by a message and no action is taken.
   */
  private void processMovement(char userMovement) {
    switch (userMovement) {
      case 'r' | 'R' -> game.compactRight();
      case 'l' | 'L' -> game.compactLeft();
      case 'u' | 'U' -> game.compactUp();
      case 'd' | 'D' -> game.compactDown();
      default -> Output.printMessage(NO_VALID_MOVEMENT_MESSAGE);
    }
  }
}
