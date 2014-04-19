import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;


public class Palindromize {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				String word = reader.readLine();
				String reverse = new StringBuilder(word).reverse().toString();
				int matched = palindromize(word, reverse);
				writer.append(String.valueOf(word.length() * 2 - matched));
				writer.newLine();
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int palindromize(String word, String reverse) {
		int w_length = word.length(), r_length = reverse.length();
		int begin = 0, matched = 0;
		Vector<Integer> pi = getPartialMatch(reverse);
		
		while (begin < r_length) {
			if (matched < r_length && word.charAt(begin + matched) == reverse.charAt(matched)) {
				matched++;
				if (begin + matched == w_length) return matched; 
			}
			else {
				if (matched == 0) begin++;
				else {
					begin += matched - pi.get(matched - 1);
					matched = pi.get(matched - 1);
				}
			}
		}
		return 0;
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
