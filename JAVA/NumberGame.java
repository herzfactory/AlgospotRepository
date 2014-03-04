import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class NumberGame {
	private static final int MAX_BOARD_SIZE = 50;
	private static final int EMPTY_VALUE = -99999;
	private static int[][] cache = new int[MAX_BOARD_SIZE][MAX_BOARD_SIZE];
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				for (int i = 0; i < MAX_BOARD_SIZE; i++) Arrays.fill(cache[i], EMPTY_VALUE);
				int length = Integer.parseInt(reader.readLine());
				int[] board = new int[length];
				String[] input = reader.readLine().split(" ");
				for (int i = 0; i < length; i++) board[i] = Integer.parseInt(input[i]);
				int difference = playGame(board, 0, length - 1);
				writer.append(String.valueOf(difference));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int playGame(int[] board, int left, int right) {
		if (left > right) return 0;
		int ret = cache[left][right];
		if (ret != EMPTY_VALUE) return ret;
		ret = Math.max(board[left] - playGame(board, left + 1, right), board[right] - playGame(board, left, right- 1 ));
		if (right - left + 1 >= 2) {
			ret = Math.max(ret, -playGame(board, left+2, right));
			ret = Math.max(ret, -playGame(board, left, right-2));
		}
		cache[left][right] = ret;
		return ret;
	}

}