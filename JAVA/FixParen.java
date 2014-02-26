import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class FixParen {
	
	private static char[][] PRIORITY = new char[4][2];
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				String[] input = reader.readLine().split(" ");
				char[] parenthesis = input[0].toCharArray();
				char[] priority = input[1].toCharArray();
				initPriority(priority);
				writer.append(matchingParenthesis(parenthesis));
				writer.append("\n");
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void initPriority(char[] priority) {
		for (int i = 0; i < priority.length; i++) {
			if (priority[i] == '(') {PRIORITY[i][0] = priority[i]; PRIORITY[i][1] = ')';}
			else if (priority[i] == '{') {PRIORITY[i][0] = priority[i]; PRIORITY[i][1] = '}';}
			else if (priority[i] == '[') {PRIORITY[i][0] = priority[i]; PRIORITY[i][1] = ']';}
			else if (priority[i] == '<') {PRIORITY[i][0] = priority[i]; PRIORITY[i][1] = '>';}
		}
	}
	
	public static String matchingParenthesis(char[] parenthesis) {
		int size = parenthesis.length;
		Stack<Integer> indexStack = new Stack<Integer>();
		for (int i = 0; i < size; i++) {
			if (parenthesis[i] == '(' || parenthesis[i] == '{' || parenthesis[i] == '[' || parenthesis[i] == '<') indexStack.push(i);
			else {
				int i_left = indexStack.pop();
				char left = parenthesis[i_left];
				int v_left = getPriority(left), v_right = getPriority(parenthesis[i]);
				if (v_left < v_right) parenthesis[i] = PRIORITY[v_left][1];
				else parenthesis[i_left] = PRIORITY[v_right][0];
			}
		}
		
		return String.valueOf(parenthesis);
	}
	public static int getPriority(char parenthesis) {
		int value = 0, priority_size = PRIORITY.length;
		for (; value < priority_size; value++) {
			if (PRIORITY[value][0] == parenthesis || PRIORITY[value][1] == parenthesis) break; 
		}
		return value;
	}
}
