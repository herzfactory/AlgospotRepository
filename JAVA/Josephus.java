import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class Josephus {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				String[] input = reader.readLine().split(" ");
				int n = Integer.parseInt(input[0]), k = Integer.parseInt(input[1]);
				ArrayList<Integer> man = new ArrayList<Integer>();
				for (int i = 1; i <= n; i++) man.add(i);
				int index = 0;
				while (man.size() > 2) {
					man.remove(index);
					if (index == man.size()) index = 0;
					for (int i = 0; i < k - 1; i++) {
						index++;
						if (index == man.size()) index = 0;
					}				
				}
				writer.append(String.valueOf(man.get(0)) + " " + man.get(1) + "\n");
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
