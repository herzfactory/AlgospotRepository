import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.Vector;


public class Fence {
	
	private static Vector<Integer> height;

	public static void main (String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				int numOfFence = Integer.parseInt(reader.readLine());
				height = new Vector<Integer>(numOfFence);
				String[] input = reader.readLine().split(" ");
				for (int i = 0; i < numOfFence; i++) height.add(Integer.parseInt(input[i]));
				writer.append(String.valueOf(maxRectSize())); writer.append("\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int maxRectSize() {
		Stack<Integer> remaining = new Stack<Integer>();
		height.add(0);
		int ret = 0;
		for (int i = 0; i < height.size(); i++) {
			while (!remaining.empty() && height.get(remaining.peek()) >= height.get(i)) {
				int j = remaining.pop();
				int width = -1;
				if (remaining.empty()) width = i;
				else width = i - remaining.peek() - 1;
				ret = Math.max(ret, height.get(j) * width);
			}
			remaining.push(i);
		}
		return ret;
	}
}
