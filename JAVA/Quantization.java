import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class Quantization {
	private static int sizeOfArray;
	private static int[] sequence;
	private static int[][] cache = new int[102][12];

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				for (int i = 0; i < 102; i++) Arrays.fill(cache[i], -1);
				String[] input = reader.readLine().split(" ");
				sizeOfArray = Integer.parseInt(input[0]);
				int numbers = Integer.parseInt(input[1]);
				sequence = new int[sizeOfArray];
				input = reader.readLine().split(" ");
				for (int i = 0; i < sizeOfArray; i++) sequence[i] = Integer.parseInt(input[i]);
				Arrays.sort(sequence);
				writer.append(String.valueOf(quantization(0, numbers)));writer.append("\n");
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int quantization(int left, int numbers) {
		if (left == sizeOfArray) return 0;
		if (numbers == 0) return 987654321;
		int ret = cache[left][numbers];
		if (ret != -1) return ret;
		ret = 987654321;
		for (int i = 1; i + left <= sizeOfArray; i++) {
			ret = Math.min(ret, quantization(i + left, numbers - 1) + minSauaredError(left, i + left));
		}
		cache[left][numbers] = ret;
		return ret;
	}
	
	public static int minSauaredError(int left, int right) {
		int ret = 0;
		int sum = 0;
		for (int i = left; i < right; i++) sum += sequence[i];
		int average = Math.round((float)sum / (float)(right - left));
		for (int i = left; i < right; i++) {
			ret +=  Math.pow(sequence[i] - average, 2);
		}
		
		return ret;
	}
	
}
