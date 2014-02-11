import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Mispell {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			int index = 0;
			while (index++ < cases) {
				String[] input = reader.readLine().split(" ");
				int position = Integer.parseInt(input[0]);
				StringBuffer stringBuffer = new StringBuffer();
				for (int i = 0; i < position - 1; i++) stringBuffer.append(input[1].charAt(i));
				for (int i = position; i < input[1].length(); i++) stringBuffer.append(input[1].charAt(i));
				writer.append(index + " " + stringBuffer.toString()+"\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
