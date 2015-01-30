/**
 * Assignment to teach dynamic programming using 3 simple example problems:
 * 1. Fibonacci numbers
 * 2. Longest common subsequence
 * 3. Edit distance
 *
 * @author Julia Ting (julia.ting@gatech.edu)
 */
public class DynamicProgrammingAssignment {
	public static int num_calls = 0; //DO NOT TOUCH

	/**
	 * Calculates the nth fibonacci number: fib(n) = fib(n-1) + fib(n-2).
	 * Remember that fib(0) = 0 and fib(1) = 1.
	 *
	 * This should NOT be done recursively - instead, use a 1 dimensional
	 * array so that intermediate fibonacci values are not re-calculated.
	 *
	 * The running time of this function should be O(n).
	 *
	 * @param n A number
	 * @return The nth fibonacci number
	 */
	public static int fib(int n) {
		num_calls++; //DO NOT TOUCH

		int[] arr = new int[n + 1];
		arr[0] = 0;
		if (n + 1 > 1) {
    		arr[1] = 1;
    		for (int i = 2; i < arr.length; i++) {
    		    arr[i] = arr[i - 1] + arr[i - 2];
    		}
		}
		return arr[n];
	}

	/**
	 * Calculates the length of the longest common subsequence between a and b.
	 *
	 * @param a
	 * @param b
	 * @return The length of the longest common subsequence between a and b
	 */
	public static int lcs(String a, String b) {
		num_calls++; //DO NOT TOUCH

		int m = a.length();
		int n = b.length();
		int[][] chart = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
		    for (int j = 1; j <= n; j++) {
		        if (a.charAt(i - 1) == b.charAt(j - 1)) {
		            chart[i][j] = chart[i - 1][j - 1] + 1;
		        } else {
		            chart[i][j] = Math.max(chart[i][j - 1], chart[i - 1][j]);
		        }
		    }
		}
		return chart[m][n];
	}

	/**
	 * Calculates the edit distance between two strings.
	 *
	 * @param a A string
	 * @param b A string
	 * @return The edit distance between a and b
	 */
	public static int edit(String a, String b) {
		num_calls++; //DO NOT TOUCH

		int m = a.length();
		int n = b.length();
		int[][] chart = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
		    chart[i][0] = i;
		}
		for (int j = 0; j <= n; j++) {
		    chart[0][j] = j;
		}
 		for (int i = 0; i < m; i++) {
		    for (int j = 0; j < n; j++) {
		        if (a.charAt(i) == b.charAt(j)) {
		            chart[i + 1][j + 1] = chart[i][j];
		        } else {
		            int tempMin = Math.min(chart[i][j] + 1, chart[i][j + 1] + 1);
		            chart[i + 1][j + 1] = Math.min(tempMin,
		                    chart[i + 1][j] + 1);
		        }
		    }
		}
		return chart[m][n];
	}
}

