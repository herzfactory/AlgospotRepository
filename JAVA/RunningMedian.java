import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;


public class RunningMedian {
	
	public static class LCR {
		private int a, b;
		private long seed;
		public LCR(int a, int b) {
			this.a = a; this.b = b; seed = 1983;
		}
		public long next() {
			long ret = seed;
			seed = (seed * a + b) % 20090711;
			return ret;
		}
	}

	private static PriorityQueue<Long> minHeap, maxHeap;
	private static LCR lcr;
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				String[] input = reader.readLine().split(" ");
				int n = Integer.parseInt(input[0]);
				lcr = new LCR(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
				long ret = 0; minHeap = new PriorityQueue<Long>(n);
				maxHeap = new PriorityQueue<Long>(n, new Comparator<Long>() {
					@Override
					public int compare(Long o1, Long o2) {
						// TODO Auto-generated method stub
						return (int)(o2.longValue() - o1.longValue());
					}
				});
				
				for (int i = 0; i < n; i++) {
					ret = (ret + getRunningMedian(lcr.next())) % 20090711;
				}
				writer.append(String.valueOf(ret));
				writer.newLine();
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static long getRunningMedian(long value) {
		if (maxHeap.size() == minHeap.size()) maxHeap.add(value);
		else minHeap.add(value);
		if (!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
			long a = maxHeap.poll(); long b = minHeap.poll();
			maxHeap.add(b); minHeap.add(a);
		}
		return maxHeap.peek();
	}
}
