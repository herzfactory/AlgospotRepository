import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Encrypt {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			
			int cases = Integer.parseInt(reader.readLine());
			while (cases-- > 0) {
				char[] words = reader.readLine().toCharArray();
				StringBuffer stringBuffer = new StringBuffer();
				for (int i = 0; i < words.length; i+=2) stringBuffer.append(words[i]);
				for (int i = 1; i < words.length; i+=2) stringBuffer.append(words[i]);
				writer.append(stringBuffer.toString() + "\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
