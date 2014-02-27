import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class ClockSync {
	private static final int MAX_PUSH = 9999;
	private static final int PUSH_SIZE = 4;
	private static final int CLOCK_SIZE = 16;
	private static final int SWITCH_SIZE = 10;
	private static final int[][] linkedWithClock = { 
		{3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0
		{0, 0, 0, 3, 0, 0, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0}, // 1
		{0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 3}, // 2
		{3, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0}, // 3
		{0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 3, 0, 3, 0, 0, 0}, // 4
		{3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3}, // 5
		{0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3}, // 6
		{0, 0, 0, 0, 3, 3, 0, 3, 0, 0, 0, 0, 0, 0, 3, 3}, // 7
		{0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 8
		{0, 0, 0, 3, 3, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0}, // 5
	};

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				int[] clocks = new int[CLOCK_SIZE];
				String[] input = reader.readLine().split(" ");
				for (int i = 0; i < CLOCK_SIZE; i++) clocks[i] = Integer.parseInt(input[i]);
				int push = synchronizingClocks(clocks, 0);
				if (push >= MAX_PUSH) writer.append(String.valueOf(-1));
				else writer.append(String.valueOf(push));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int synchronizingClocks(int[] clocks, int switchNumber) {
		if (switchNumber == SWITCH_SIZE) return isOnTime(clocks) ? 0 : MAX_PUSH;
		int ret = MAX_PUSH;
		for (int push = 0; push < PUSH_SIZE; push++) {
			ret = Math.min(ret, push + synchronizingClocks(clocks, switchNumber + 1));
			pushSwitch(clocks, switchNumber);
		}
		return ret;
	}
	
	public static void pushSwitch(int[] clocks, int switchNumber) {
		for (int clock = 0; clock < clocks.length; clock++) {
			clocks[clock] = clocks[clock] + linkedWithClock[switchNumber][clock];
			if (clocks[clock] == 15) clocks[clock] = 3;
		}
	}
	
	public static boolean isOnTime(int[] clocks) {
		for (int i = 0; i < CLOCK_SIZE; i++) {
			if (clocks[i] != 12) return false;
		}
		return true;
	}

}
