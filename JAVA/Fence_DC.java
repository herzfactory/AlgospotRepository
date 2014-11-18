import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Fence {
	
	private static int numOfBoard;
	private static int[] boardHeight;

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				numOfBoard = Integer.parseInt(reader.readLine());
				boardHeight = new int[numOfBoard];
				String[] input = reader.readLine().split(" ");
				for (int i = 0; i < numOfBoard; i++) boardHeight[i] = Integer.parseInt(input[i]);
				writer.append(String.valueOf(cropFence(0, numOfBoard - 1)));
				writer.newLine();
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int cropFence(int left, int right) {
		int ret = 0;
		if (left == right) return boardHeight[left];
		int middle = (left + right) / 2;
		int leftCropFence = cropFence(left, middle);
		int rightCropFence = cropFence(middle + 1, right);
		ret = Math.max(leftCropFence, rightCropFence);
		int n_left = middle, n_right = middle + 1;
		int height = Math.min(boardHeight[n_left], boardHeight[n_right]);
		ret = Math.max(2 * height, ret);
		
		while (n_left > left || n_right < right) {
			if (n_right < right && (n_left == left || boardHeight[n_left-1] < boardHeight[n_right + 1])) {
				n_right++;
				height = Math.min(height, boardHeight[n_right]);
			}
			else {
				n_left--;
				height = Math.min(height, boardHeight[n_left]);
			}
			ret = Math.max(ret, (n_right - n_left + 1) * height);
		}
		
		return ret;
	}

}
