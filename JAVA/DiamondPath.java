import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class DiamondPath {
	
	private static final int MAX_HEIGHT = 200;
	private static final int MAX_WIDTH = 100;
	private static int height;
	private static int width;
	private static int[][] cache = new int[MAX_HEIGHT][MAX_WIDTH];

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				for (int i = 0; i < MAX_HEIGHT; i++) Arrays.fill(cache[i], -1);
				width = Integer.parseInt(reader.readLine());
				height = 2 * width - 1;
				int[][] diamond = new int[height][width];
				for (int i = 0; i < height; i++) {
					String[] input = reader.readLine().split(" ");
					for (int j = 0; j < input.length; j++) diamond[i][j] = Integer.parseInt(input[j]);
				}
				int best = getBestPath(diamond, 0, 0);
				writer.append(String.valueOf(best));
				writer.append("\n");
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getBestPath(int[][] diamond, int i_height, int i_width) {
		if (i_height == height || i_width < 0 || i_width == width) return 0;
		int ret = cache[i_height][i_width];
		if (ret != -1) return ret; else ret = 0;
		if (i_height < height / 2) {
			ret = diamond[i_height][i_width] + Math.max(getBestPath(diamond, i_height + 1, i_width), getBestPath(diamond, i_height + 1, i_width + 1));
		}
		else {
			ret = diamond[i_height][i_width] + Math.max(getBestPath(diamond, i_height + 1, i_width - 1), getBestPath(diamond, i_height + 1, i_width));
		}
		cache[i_height][i_width] = ret;
		return ret;
	}
}