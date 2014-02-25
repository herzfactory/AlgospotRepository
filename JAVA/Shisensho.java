import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Shisensho {

	private static final int VALID_PATH_SEGMENTS = 3;
	private static final int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

	private static int height = 0;
	private static int width = 0;
	private static char[][] board;
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());

			while (cases-- > 0) {
				String[] input = reader.readLine().split(" ");
				height = Integer.parseInt(input[0]);
				width = Integer.parseInt(input[1]);
				board = new char[height][width];
				for (int i = 0; i < height; i++) board[i] = reader.readLine().toCharArray();
				int countOfValidPath = get_countOfValidPath();
				writer.append(String.valueOf(countOfValidPath)); writer.append("\n");
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int get_countOfValidPath() {
		int ret = 0;
		for (int curHeight = 0; curHeight < height; curHeight++) {
			for (int curWidth = 0; curWidth < width; curWidth++) {
				if (board[curHeight][curWidth] != '.' && board[curHeight][curWidth] != '*') {
					char tile = board[curHeight][curWidth];
					board[curHeight][curWidth] = '*';
					char[][] tempBoard = new char[height][width];
					for (int i = 0; i < height; i++) {
						for (int j = 0; j < width; j++) tempBoard[i][j] = board[i][j];
					}
					for (int directionType = 0; directionType < direction.length; directionType++) {
						int nextHeight = curHeight + direction[directionType][0];
						int nextWidth = curWidth + direction[directionType][1];
						ret += searchingValidPath(tempBoard, tile, nextHeight, nextWidth, 1, directionType);
					}
				}
			}
		}
		return ret;
	}
	public static int searchingValidPath(char[][] board, char tile, int curHeight, int curWidth, int segments, int dType) {
		int ret = 0;
		if (curHeight <0 || curHeight == height || curWidth < 0 || curWidth == width) return 0;
		if (segments > VALID_PATH_SEGMENTS) return 0;
		else if (board[curHeight][curWidth] == tile) { 
				board[curHeight][curWidth] = '*';
				return 1;
		}
		else if (board[curHeight][curWidth] == '.') {
			board[curHeight][curWidth] = '*'; 
			for (int directionType = 0; directionType < direction.length; directionType++) {
				int nextHeight = curHeight + direction[directionType][0];
				int nextWidth = curWidth + direction[directionType][1];
				int nextSegments = segments;
				if (directionType != dType) nextSegments++;
				ret += searchingValidPath(board, tile, nextHeight, nextWidth, nextSegments, directionType);
			}
			board[curHeight][curWidth] = '.';
		}
		return ret;
	}
}
