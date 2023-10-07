package uo.mp.lab01.game2048.util;

public class ForTesting {
	// Constants to used in the tests and getSum()
	public static int[][] FULL_2 = { { 2, 4, 8 }, { 4, 8, 16 }, { 8, 16, 32 } };

	public static int[][] SEMIFULL11_2 = { { 0, 2, 0 }, { 0, 4, 0 }, { 0, 8, 0 } };
	public static int[][] SEMIFULL12_2 = { { 2, 0, 0 }, { 4, 0, 0 }, { 8, 0, 0 } };
	public static int[][] SEMIFULL13_2 = { { 0, 0, 2 }, { 0, 0, 4 }, { 0, 0, 8 } };
	public static int[][] SEMIFULL1_RIGHTCOMPACTED_2 = { { 0, 0, 2 }, { 0, 0, 4 }, { 0, 0, 8 } };
	public static int[][] SEMIFULL1_LEFTCOMPACTED_2 = { { 2, 0, 0 }, { 4, 0, 0 }, { 8, 0, 0 } };

	public static int[][] SEMIFULL21_2 = { { 0, 2, 4 }, { 0, 4, 8 }, { 0, 8, 16 } };
	public static int[][] SEMIFULL22_2 = { { 2, 4, 0 }, { 4, 8, 0 }, { 8, 16, 0 } };
	public static int[][] SEMIFULL23_2 = { { 2, 0, 4 }, { 4, 0, 8 }, { 8, 0, 16 } };
	public static int[][] SEMIFULL2_RIGHTCOMPACTED_2 = { { 0, 2, 4 }, { 0, 4, 8 }, { 0, 8, 16 } };
	public static int[][] SEMIFULL2_LEFTCOMPACTED_2 = { { 2, 4, 0 }, { 4, 8, 0 }, { 8, 16, 0 } };

	public static int[][] SEMIFULL31_2 = { { 0, 0, 0 }, { 0, 0, 0 }, { 2, 4, 8 } };
	public static int[][] SEMIFULL32_2 = { { 0, 0, 0 }, { 2, 4, 8 }, { 0, 0, 0 } };
	public static int[][] SEMIFULL33_2 = { { 2, 4, 8 }, { 0, 0, 0 }, { 0, 0, 0 } };
	public static int[][] SEMIFULL3_UPCOMPACTED_2 = { { 2, 4, 8 }, { 0, 0, 0 }, { 0, 0, 0 } };
	public static int[][] SEMIFULL3_DOWNCOMPACTED_2 = { { 0, 0, 0 }, { 0, 0, 0 }, { 2, 4, 8 } };

	public static int[][] SEMIFULL41_2 = { { 0, 0, 0 }, { 2, 4, 8 }, { 4, 8, 16 }, };
	public static int[][] SEMIFULL42_2 = { { 2, 4, 8 }, { 0, 0, 0 }, { 4, 8, 16 } };
	public static int[][] SEMIFULL43_2 = { { 2, 4, 8 }, { 4, 8, 16 }, { 0, 0, 0 } };
	public static int[][] SEMIFULL4_UPCOMPACTED_2 = { { 2, 4, 8 }, { 4, 8, 16 }, { 0, 0, 0 } };
	public static int[][] SEMIFULL4_DOWNCOMPACTED_2 = { { 0, 0, 0 }, { 2, 4, 8 }, { 4, 8, 16 } };
	////////
	///////
	public static int[][] FULL = { { 2, 2, 2 }, { 2, 2, 2 }, { 2, 2, 2 } };
	public static int[][] FULL_RIGHTMOVED = { { 0, 2, 4 }, { 0, 2, 4 }, { 0, 2, 4 } };
	public static int[][] FULL_LEFTMOVED = { { 4, 2, 0 }, { 4, 2, 0 }, { 4, 2, 0 } };
	public static int[][] FULL_UPMOVED = { { 4, 4, 4 }, { 2, 2, 2 }, { 0, 0, 0 } };
	public static int[][] FULL_DOWNMOVED = { { 0, 0, 0 }, { 2, 2, 2 }, { 4, 4, 4 } };

	public static int[][] SEMIFULL11 = { { 2, 0, 0 }, { 2, 0, 0 }, { 2, 0, 0 } };
	public static int[][] SEMIFULL12 = { { 0, 2, 0 }, { 0, 2, 0 }, { 0, 2, 0 } };
	public static int[][] SEMIFULL13 = { { 0, 0, 2 }, { 0, 0, 2 }, { 0, 0, 2 } };

	public static int[][] SEMIFULL1_RIGHTCOMPACTED = { { 0, 0, 2 }, { 0, 0, 2 }, { 0, 0, 2 } };
	public static int[][] SEMIFULL1_RIGHTMOVED = { { 0, 0, 2 }, { 0, 0, 2 }, { 0, 0, 2 } };
	public static int[][] SEMIFULL1_LEFTCOMPACTED = { { 2, 0, 0 }, { 2, 0, 0 }, { 2, 0, 0 } };
	public static int[][] SEMIFULL1_LEFTMOVED = { { 2, 0, 0 }, { 2, 0, 0 }, { 2, 0, 0 } };

	public static int[][] SEMIFULL21 = { { 2, 2, 0 }, { 2, 2, 0 }, { 2, 2, 0 } };
	public static int[][] SEMIFULL22 = { { 0, 2, 2 }, { 0, 2, 2 }, { 0, 2, 2 } };
	public static int[][] SEMIFULL23 = { { 2, 0, 2 }, { 2, 0, 2 }, { 2, 0, 2 } };

	public static int[][] SEMIFULL2_RIGHTCOMPACTED = { { 0, 2, 2 }, { 0, 2, 2 }, { 0, 2, 2 } };
	public static int[][] SEMIFULL2_RIGHTMOVED = { { 0, 0, 4 }, { 0, 0, 4 }, { 0, 0, 4 } };
	public static int[][] SEMIFULL2_LEFTCOMPACTED = { { 2, 2, 0 }, { 2, 2, 0 }, { 2, 2, 0 } };
	public static int[][] SEMIFULL2_LEFTMOVED = { { 4, 0, 0 }, { 4, 0, 0 }, { 4, 0, 0 } };

	public static int[][] SEMIFULL31 = { { 2, 2, 2 }, { 0, 0, 0 }, { 0, 0, 0 } };
	public static int[][] SEMIFULL32 = { { 0, 0, 0 }, { 2, 2, 2 }, { 0, 0, 0 } };
	public static int[][] SEMIFULL33 = { { 0, 0, 0 }, { 0, 0, 0 }, { 2, 2, 2 } };

	public static int[][] SEMIFULL3_UPCOMPACTED = { { 2, 2, 2 }, { 0, 0, 0 }, { 0, 0, 0 } };
	public static int[][] SEMIFULL3_UPMOVED = { { 2, 2, 2 }, { 0, 0, 0 }, { 0, 0, 0 } };
	public static int[][] SEMIFULL3_DOWNCOMPACTED = { { 0, 0, 0 }, { 0, 0, 0 }, { 2, 2, 2 } };
	public static int[][] SEMIFULL3_DOWNMOVED = { { 0, 0, 0 }, { 0, 0, 0 }, { 2, 2, 2 } };

	public static int[][] SEMIFULL41 = { { 2, 2, 2 }, { 2, 2, 2 }, { 0, 0, 0 } };
	public static int[][] SEMIFULL42 = { { 0, 0, 0 }, { 2, 2, 2 }, { 2, 2, 2 } };
	public static int[][] SEMIFULL43 = { { 2, 2, 2 }, { 0, 0, 0 }, { 2, 2, 2 } };

	public static int[][] SEMIFULL4_UPCOMPACTED = { { 2, 2, 2 }, { 2, 2, 2 }, { 0, 0, 0 } };
	public static int[][] SEMIFULL4_UPMOVED = { { 4, 4, 4 }, { 0, 0, 0 }, { 0, 0, 0 } };
	public static int[][] SEMIFULL4_DOWNCOMPACTED = { { 0, 0, 0 }, { 2, 2, 2 }, { 2, 2, 2 } };
	public static int[][] SEMIFULL4_DOWNMOVED = { { 0, 0, 0 }, { 0, 0, 0 }, { 4, 4, 4 } };

	/**
	 * Sums all the elements of the given matrix
	 * 
	 * @param matrix: It's the given matrix with the numbers
	 */
	public static int getSum(int[][] matrix) {
		int cont = 0;
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				cont = cont + matrix[i][j];
		return cont;
	}

}
