import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class Tiling2 {
	
	private static int[] cache = new int[101];
	private static final int MOD = 1000000007;
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			Arrays.fill(cache, -1);
			cache[1] = 1; cache[2] = 2; cache[3] = 3;
			
			while (cases-- > 0) {
				int number = Integer.parseInt(reader.readLine());
				writer.append(String.valueOf(fibonacciSequence(number)));
				writer.append("\n");
			}
			writer.flush();
			writer.close(); reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
