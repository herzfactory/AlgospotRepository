import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class TrianglePath {
	
	private static int[][] board;
	private static int[][] cache;
	private static int size;
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				size = Integer.parseInt(reader.readLine());
				board = new int[size][size]; cache = new int[size][size];
				for (int i = 0; i < size; i++) { Arrays.fill(cache[i], -1); Arrays.fill(board[i], -1);}
				for (int i = 0; i < size; i++) {
					String[] input = reader.readLine().split(" ");
					for (int j = 0; j < input.length; j++) board[i][j] = Integer.parseInt(input[j]);
				}
				writer.append(String.valueOf(getBestPath(0, 0)));
				writer.append("\n");
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getBestPath(int x, int y) {
		if (board[x][y] == -1) return 0;
		int ret = cache[x][y];
		if (ret != -1) return ret;
		if (x == size - 1) return board[x][y];
		ret = board[x][y] + Math.max(getBestPath(x + 1, y), getBestPath(x + 1, y + 1));
		cache[x][y] = ret;
		return ret;
	}

}
