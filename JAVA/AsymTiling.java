import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class AsymTiling {
	
	private static int[] cache = new int[101];
	private static final int MOD = 1000000007;

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			Arrays.fill(cache, -1);
			cache[0] = 1; cache[1] = 1; cache[2] = 2; cache[3] = 3;
			
			while (cases-- > 0) {
				int number = Integer.parseInt(reader.readLine());
				writer.append(String.valueOf(asymTiling(number)));
				writer.append("\n");
			}
			writer.flush();
			writer.close(); reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int asymTiling(int number) {
		if (number % 2 == 1) return fibonacciSequence(number) - fibonacciSequence(number/2);
		int ret = fibonacciSequence(number);
		ret = (ret - fibonacciSequence(number / 2) + MOD) % MOD;
		ret = (ret - fibonacciSequence(number / 2 - 1) + MOD) % MOD;
		return ret;
	}
	
	public static int fibonacciSequence(int number) {
		int ret = cache[number];
		if (ret != -1) return ret;
		ret = fibonacciSequence(number - 1) + fibonacciSequence(number - 2);
		if (ret > MOD) ret %= MOD;
		cache[number] = ret;
		return ret;
	}

}