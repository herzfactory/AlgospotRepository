import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Endians {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());

			while(cases-- > 0) {
				long endian = Long.parseLong(reader.readLine());
				writer.append(String.valueOf(convertEndians(endian)) + "\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long convertEndians(long endian) {
		byte[] buf = new byte[4];
		long ret = 0;
		for (int i = 0; i < 4; i++) buf[i] = (byte)((endian >>> (i * 8)) & 0xFF);
		for(int i = 0; i < 4; i++) ret += (long)(buf[3-i] & 0xFF) << (i * 8);
		return ret;
	}
}
