import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;


public class Bracket2 {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				char[] brackets = reader.readLine().toCharArray();
				if (isWellMatched(brackets)) writer.append("YES\n");
				else writer.append("NO\n");
			}
			writer.flush();
			writer.close(); reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static boolean isWellMatched(char[] brackets) {
		boolean isWellMatched = false;
		Stack<Character> braketsBasket = new Stack<Character>();
		for (int i = 0; i < brackets.length; i++) {
			switch (brackets[i]) {
			case '(' : case '[' : case '{' : braketsBasket.push(brackets[i]); break;
			case ')' : if (!braketsBasket.isEmpty() && braketsBasket.peek() == '(') braketsBasket.pop(); else return isWellMatched; break;
			case ']' : if (!braketsBasket.isEmpty() && braketsBasket.peek() == '[') braketsBasket.pop(); else return isWellMatched; break;
			case '}' : if (!braketsBasket.isEmpty() && braketsBasket.peek() == '{') braketsBasket.pop(); else return isWellMatched;	break;
			}
		}
		if (braketsBasket.isEmpty()) isWellMatched = true;
		return isWellMatched;
	}

}
