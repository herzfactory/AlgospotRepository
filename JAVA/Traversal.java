import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class Traversal {
	
	private static ArrayList<Integer> postOrder;
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				int n = Integer.parseInt(reader.readLine());
				ArrayList<Integer> pre = new ArrayList<Integer>(), in = new ArrayList<Integer>();
				postOrder = new ArrayList<Integer>();
				String[] pre_input = reader.readLine().split(" "), in_input = reader.readLine().split(" ");
				for (int i = 0; i < n; i++) {
					pre.add(Integer.parseInt(pre_input[i])); in.add(Integer.parseInt(in_input[i]));
				}
				printPostOrder(pre, in);
				for (int i = 0; i < postOrder.size(); i++) {
					if (i != 0) writer.append(" ");
					writer.append(String.valueOf(postOrder.get(i)));
				}
				writer.newLine();
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void printPostOrder(List<Integer> pre, List<Integer> in) {
		if (pre.size() == 0) return ;
		int rootValue = pre.get(0);
		int rootIndex = in.indexOf(rootValue);
		printPostOrder(pre.subList(1, rootIndex + 1), in.subList(0, rootIndex));
		printPostOrder(pre.subList(rootIndex + 1, pre.size()), in.subList(rootIndex + 1,  in.size()));
		postOrder.add(rootValue);
	}
}
