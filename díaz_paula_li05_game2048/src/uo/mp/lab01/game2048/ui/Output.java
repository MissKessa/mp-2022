package uo.mp.lab01.game2048.ui;

/**
 * The output class represents the communication from the computer to the user. That is, the output
 * of messages. The standard output, usually the command line, is used for this purpose. This class,
 * instead of defining a generic method to send messages to the user, defines a method for each
 * possible message that can be sent.
 *
 * @author Programming Methodology 2023 Teaching Staff
 * @version 2023
 */
public class Output {

  /**
   * This method prints the given board. The board must be a 2-dimensional array of integers.
   *
   * @param board to be printed.
   */
   static void printBoard(int[][] board) { //change to take into account the digits
		  for (int i=0; i<board.length;i++) {
			  for (int j=0; j<board[i].length; j++) {
				  System.out.print(board[i][j]+" | ");
			  }
			  System.out.println();
		  }
	   }

  /**
   * This method prints to standard output a message given as a parameter. This method does not add
   * a line break after printing the message. Therefore, if it is desired to add a new line after
   * printing the message, the message must have the new line special character included.
   *
   * @param message to be printed. If you desire a new line to be printed after the message then the
   *                new line special character needs to be included at the end of the message.
   */
  static void printMessage(String message) {
    System.out.print(message);
  }

  /**
   * This method prints to standard output a question whose answer is expected to be either positive
   * or negative. The question to be printed is given as a parameter and the text of the positive
   * options can be parameterized by the arguments. The format in which the question will be
   * displayed is the following: "%s %c/%c: " where we have the first argument the question, the
   * second the positive answer and the third the negative answer.
   *
   * @param question is the question to be printed out.
   * @param positive is the text (char) that you expect the user to answer the question positive.
   * @param negative is the text (char) that you expect the user to answer the question negative.
   */
  static void printPositiveNegativeQuestion(String question, char positive, char negative) {
    System.out.printf("%s %c/%c: ", question, positive, negative);
  }
  
  /*public static void main(String[] args) {
	  int[][] a={{1,2,3},{5,6,7},{8,9,10}};
	  printBoard(a);
  }*/

}
