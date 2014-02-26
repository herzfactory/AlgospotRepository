import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class NQueen {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				int n = Integer.parseInt(reader.readLine());
				int[] columnBoard = new int[n + 1];
				int casesOfNqueen = getNQueenCases(0, n, columnBoard);
				writer.append(String.valueOf(casesOfNqueen) + "\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getNQueenCases(int col, int n, int[] columnBoard) {
		int ret = 0;
		if (n == 1) return 1;
		if (isPossible(col, columnBoard)) {
			if (col == n) return 1;
			else {
				for (int row = 1; row <= n; row++) {
					columnBoard[col + 1] = row;
					ret += getNQueenCases(col + 1, n, columnBoard);
				}
			}
		}
		return ret;
	}
	
	public static boolean isPossible(int col, int[] columnBoard) {
		boolean isPossible = true;
		int k = 1;
		while (k < col && isPossible) {
			if (columnBoard[col] == columnBoard[k] || Math.abs(columnBoard[col] - columnBoard[k]) == Math.abs(col - k)) isPossible = false;
			k++;
		}
		return isPossible;
	}
}
