import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class BoardCover {

	private static int[][][] coverType = {{{0, 0}, {1, 0}, {0, 1}} ,
		{{0, 0}, {0, 1}, {1, 1}},
		{{0, 0},{1, 0},{1, 1}},
		{{0, 0}, {1, 0}, {1, -1}}};
	private static final int TYPE_SIZE = 4;
	private static final int BLOCK_SIZE = 3;
	private static final int MODE_SET = 1;
	private static final int MODE_REMOVE = 0;

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());

			while (cases-- > 0) {
				String[] input = reader.readLine().split(" ");f
				int height = Integer.parseInt(input[0]), width = Integer.parseInt(input[1]), countWhiteBlock = 0;
				char[][] board = new char[height][width];
				for (int i = 0; i < height; i++) {
					input = reader.readLine().split("");
					for (int j = 0; j < width; j++) {
						board[i][j] = input[j+1].charAt(0);
						if (board[i][j] != '#') countWhiteBlock++;
					}
				}
				if (countWhiteBlock % 3 != 0) writer.append(String.valueOf(0) + "\n");
				else writer.append(String.valueOf(coverBoard(board, 0)) + "\n");
			}
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int coverBoard(char[][] board, int curHeight) {
		int ret = 0, height = board.length, width = board[0].length, curWidth = 0;

		loops :
			for (; curHeight < height; curHeight++) {
				for (curWidth = 0 ; curWidth < width; curWidth++) {
					if (board[curHeight][curWidth] != '#') break loops; 
				}
			}
		if (curHeight == height) return 1;

		for (int type = 0; type < TYPE_SIZE; type++) {
			if (setBoard(board, curHeight, curWidth, type, MODE_SET)) {
				ret += coverBoard(board, curHeight);
				setBoard(board, curHeight, curWidth, type, MODE_REMOVE);
			}
		}

		return ret;
	}

	public static boolean setBoard(char[][] board, int curHeight, int curWidth, int type, int mode) {
		boolean successChange = true;
		int height = board.length, width = board[0].length;
		if (mode == 1) {
			for (int block = 0; block < BLOCK_SIZE; block++) {
				int nextHeight = curHeight + coverType[type][block][0];
				int nextWidth = curWidth + coverType[type][block][1];
				if (nextHeight < 0 || nextHeight >= height || nextWidth < 0 || nextWidth >= width || board[nextHeight][nextWidth] == '#') return !successChange;
			}
		}
		for (int block = 0; block < BLOCK_SIZE; block++) {
			int nextHeight = curHeight + coverType[type][block][0];
			int nextWidth = curWidth + coverType[type][block][1];
			if (mode == 1) board[nextHeight][nextWidth] = '#';
			else board[nextHeight][nextWidth] = '.';
		}

		return successChange;
	}

}

