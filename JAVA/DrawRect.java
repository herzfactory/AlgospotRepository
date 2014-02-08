import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class DrawRect {
	
	private static final int SIZE = 3;

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				int[] x = new int[SIZE], y = new int[SIZE];
				for (int i = 0; i < SIZE; i++) {
					String[] input = reader.readLine().split(" ");
					x[i] = Integer.parseInt(input[0]);
					y[i] = Integer.parseInt(input[1]);
				}
				writer.append(String.valueOf(searchingPoint(x)) + " " + String.valueOf(searchingPoint(y) + "\n"));
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int searchingPoint(int[] arr) {
		int ret = 0;
		if (arr[0] == arr[1]) ret = arr[2];
		else if (arr[0] == arr[2]) ret = arr[1];
		else if (arr[1] == arr[2]) ret = arr[0];
		return ret;
	}

}
