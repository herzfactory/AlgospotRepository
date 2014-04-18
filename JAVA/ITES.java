import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;


public class ITES {
	
	public static class LCG {
		private long seed;
		public LCG (){seed = 1983;}
		public long next() {
			long ret = seed;
			seed = (long) (((seed * 214013) + 2531011) % Math.pow(2, 32));
			return ret % 10000 + 1;
		}
	}
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				String[] input = reader.readLine().split(" ");
				int k = Integer.parseInt(input[0]), n = Integer.parseInt(input[1]);
				writer.append(String.valueOf(signalAnalysis(k, n))); writer.append("\n");
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int signalAnalysis(int k, int n) {
		int ret = 0, sum = 0;
		LCG rand = new LCG();
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < n; i++) {
			int nextSignal = (int) rand.next();
			sum += nextSignal; queue.add(nextSignal);
			while (sum > k) sum -= queue.poll();
			if (sum == k) ret++;
		}
		return ret;
	}
}
