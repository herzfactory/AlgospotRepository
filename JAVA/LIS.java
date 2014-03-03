import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class LIS {

	private static final int MAX_ELEMENT_SIZE = 500;
	private static int[] cache = new int[MAX_ELEMENT_SIZE];

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());

			while (cases-- > 0) {
				int size = Integer.parseInt(reader.readLine());
				int[] sequence = new int[size];
				String[] input = reader.readLine().split(" ");
				for (int i = 0; i < size; i++) sequence[i] = Integer.parseInt(input[i]);
				Arrays.fill(cache, -1);
				int longest = 0;
				for (int i = 0; i < size; i++) longest = Math.max(longest, getLongestIncreasingSequence(sequence, i, size));
				writer.append(String.valueOf(longest));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getLongestIncreasingSequence(int[] sequence, int index, int size) {
		int ret = cache[index];
		if (ret != -1) return ret; else ret = 1;
		for (int i = index + 1; i < size; i++) {
			if (sequence[index] < sequence[i]) ret = Math.max(ret, getLongestIncreasingSequence(sequence, i, size) + 1);
		}
		cache[index] = ret;
		return ret;
	}
}

