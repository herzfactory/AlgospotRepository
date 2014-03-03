import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Coins {
	
	private static final int MAX_CASES = 1000000007;
	private static final int[][] cache = new int[5001][100];
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				String[] input = reader.readLine().split(" ");
				int money = Integer.parseInt(input[0]);
				int coin = Integer.parseInt(input[1]);
				int[] coins = new int[coin];
				input = reader.readLine().split(" ");
				for (int i = 0; i < coin; i++) coins[i] = Integer.parseInt(input[i]);
				for (int i = 0; i < 5001; i++) Arrays.fill(cache[i], -1);
				Arrays.sort(coins);
				int changes = coinsChange(money, coins, 0);
				writer.append(String.valueOf(changes));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int coinsChange(int money, int[] coins, int index) {
		if (money < 0) return 0; else if (money == 0) return 1;
		int ret = cache[money][index];
		if (ret != -1) return ret; else ret = 0;
		for (int i = index; i < coins.length; i++) {
			ret += coinsChange(money - coins[i], coins, i);
			if (ret > MAX_CASES) ret %= MAX_CASES;
		}
		cache[money][index] = ret;
		return ret;
	}
}
