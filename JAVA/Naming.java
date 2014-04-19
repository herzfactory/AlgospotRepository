import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;


public class Naming {
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			
			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append(reader.readLine()); strBuffer.append(reader.readLine());
			Vector<Integer> pi = getPrefixSuffix(strBuffer.toString());
			for (int i = pi.size() - 1; i >= 0; i--) {
				if (i != pi.size() - 1) writer.append(" ");
				writer.append(String.valueOf(pi.get(i)));
			}
			writer.append("\n");
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Vector<Integer> getPrefixSuffix(String string) {
		Vector<Integer> ret = new Vector<Integer>();
		Vector<Integer> pi = getPartialMatch(string);
		int k = pi.size();
		while (k > 0) {
			ret.add(k);
			k = pi.get(k - 1);
		}
		return ret;
	}
	
	public static Vector<Integer> getPartialMatch(String string) {
		int length = string.length(), begin = 1, matched = 0;
		Vector<Integer> pi = new Vector<Integer>(length);
		for (int i = 0; i < length; i++) pi.add(0);
		while (begin + matched < length) {
			if (string.charAt(begin + matched) == string.charAt(matched)) {
				matched++;
				pi.set(begin + matched - 1, matched);
			}
			else {
				if (matched == 0) begin++;
				else {
					begin += matched - pi.get(matched - 1);
					matched = pi.get(matched - 1);
				}
			}
		}
		return pi;
	}
}
