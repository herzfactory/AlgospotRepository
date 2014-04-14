import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class PI {
	private static final int MAX_SIZE = 10002;
	private static int[] sequence;
	private static int[] cache = new int[MAX_SIZE];

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				char[] input = reader.readLine().toCharArray();
				sequence = new int[input.length];
				for (int i = 0; i < input.length; i++) sequence[i] = Integer.parseInt(Character.toString(input[i]));
				Arrays.fill(cache, -1);
				writer.append(String.valueOf(memorize(0))); writer.append("\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int memorize(int left) {
		if (left == sequence.length) return 0;
		int ret = cache[left];
		if (ret != -1) return ret;
		ret = 987654321;
		for (int i = 3; i <= 5; i++) {
			if (left + i <= sequence.length) {
				ret = Math.min(ret, memorize(left + i) + levelOfDifficulty(left, left + i));
			}
		}
		cache[left] = ret;
		return ret;
	}
	
	public static int levelOfDifficulty(int left, int right) {
		int ret;
		if (levelOne(left, right)) ret = 1;
		else if (levelTwo(left, right)) ret = 2;
		else if (levelFour(left, right)) ret = 4;
		else if (levelFive(left, right)) ret = 5;
		else ret = 10;
		return ret;
	}
	
	private static boolean levelOne(int left, int right) {
		for (int i = left + 1; i < right; i++ ) {
			if (sequence[left] != sequence[i]) return false;
		}
		return true;
	}
	 
	private static boolean levelTwo(int left, int right) {
		int diff = sequence[left + 1] - sequence[left];
		if (Math.abs(diff) != 1) return false;
		for (int i = left; i < right - 1; i++) {
			if (sequence[i + 1] - sequence[i] != diff) return false;
		}
		return true;
	}

		
	private static boolean levelFour(int left, int right) {
		for (int i = left + 2; i < right; i+=2) {
			if (sequence[left] != sequence[i]) return false;
		}
		for (int i = left + 3; i < right; i+=2) {
			if (sequence[left+1] != sequence[i]) return false;
		}
		return true;
	}

	private static boolean levelFive(int left, int right) {
		int diff = sequence[left + 1] - sequence[left];
		for (int i = left; i < right - 1; i++) {
			if (sequence[i + 1] - sequence[i] != diff) return false;
		}
		return true;
	}

}