package uo.mp.minesweeper.game;

/**
 * It provides all commands that can be generated from the game to send or
 * request information to the user
 * 
 * @author Paula Díaz Álvarez
 * @version 2023
 */
public interface GameInteractor {
	/**
	 * Prompts the user for a command (s, f, u) and row and column coordinates. The
	 * method has to ensure that both the command character and the coordinates
	 * returned are valid values (within the allowed ranges).
	 * 
	 * @param rows    is the maximum row that the user can enter. It must be non
	 *                negative
	 * @param columns is the maximum column that the user can enter. It must be non
	 *                negative
	 * @return a GameMove object containing the information provided by the user
	 * @throws IllegalArgumentException if any of the parameters is invalid
	 */
	GameMove askMove(int rows, int columns);

	/**
	 * Displays the state of the game to the user. This includes board status, time
	 * spent, and flags remaining to be placed.
	 * 
	 * @param elapsedTime is the time spent. It must be non negative
	 * @param board       is a reference to the board object whose information is to
	 *                    be displayed. It must be not null
	 * @throws IllegalArgumentException if any of the parameters is invalid
	 */
	void showGame(long elapsedTime, Board board);

	/**
	 * Informs the user that the game is over.
	 */
	void showGameFinished();

	/**
	 * Informs the user that he has won the game. It shows the time spent.
	 * 
	 * @param elapsedTime is the time spent. It must be non negative
	 * @throws IllegalArgumentException if the parameters is invalid
	 */
	void showCongratulations(long elapsedTime);

	/**
	 * Informs the user that he has stepped on a mine block.
	 */
	void showBooommm();

	/**
	 * Informs the user that the game is ready to start.
	 */
	void showReadyToStart();
}
