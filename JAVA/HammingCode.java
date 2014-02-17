import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class HammingCode {
	private static final int BIT_SIZE = 7;
	private static final int SYNDROME_SIZE = 3;

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());

			while (cases-- > 0) {
				String[] input = reader.readLine().split("");
				int[] encodedMessage = new int[BIT_SIZE];
				for (int i = 1; i < input.length; i++) encodedMessage[i - 1] = Integer.parseInt(input[i]);
				int syndrome = checkError(encodedMessage);
				if (syndrome != 0) encodedMessage[syndrome - 1] = (encodedMessage[syndrome - 1] + 1) % 2;
				StringBuffer strBuffer = new StringBuffer();
				strBuffer.append(encodedMessage[2]);strBuffer.append(encodedMessage[4]);
				strBuffer.append(encodedMessage[5]);strBuffer.append(encodedMessage[6]);
				strBuffer.append("\n");
				writer.append(strBuffer.toString());
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int checkError(int[] bits) {
		int[] syndrome = new int[SYNDROME_SIZE];
		syndrome[0] = bits[3] ^ bits[4] ^ bits[5] ^ bits[6];
		syndrome[1] = bits[1] ^ bits[2] ^ bits[5] ^ bits[6];
		syndrome[2] = bits[0] ^ bits[2] ^ bits[4] ^ bits[6];
		return syndrome[0] * 4 + syndrome[1] * 2 + syndrome[2] * 1;
	}
}
