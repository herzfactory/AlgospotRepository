import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class JumpGame {
	
	private static int[][] cache, board;
	private static int size;

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				size = Integer.parseInt(reader.readLine());
				board = new int[size][size];
				cache = new int[size][size];
				for (int i = 0; i < size; i++) {
					String[] input = reader.readLine().split(" ");
					for (int j = 0; j < size; j++) board[i][j] = Integer.parseInt(input[j]);
					Arrays.fill(cache[i], -1);
				}
				if (jump(0, 0) != 0) writer.append("YES\n");
				else writer.append("NO\n");
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int jump(int height, int width) {
		int ret = 0;
		if (height >= size || width >= size) return 0;
		if (cache[height][width] != -1) return cache[height][width];
		if (height == size - 1 && width == size - 1) return 1;
		ret += jump(height + board[height][width], width);
		ret += jump(height, width + board[height][width]);
		cache[height][width] = ret;
		return ret;
	}

}
