import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;


public class Xhaeneung {
	
	private static final String[] NUMBER = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());

			while (cases-- > 0) {
				String[] input = reader.readLine().split(" ");
				int answer = calculator(getNumber(input[0]), getNumber(input[2]), input[1]);
				if (answer == getNumber(input[4])) writer.append("Yes\n");
				else writer.append("No\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean compareStringNumber(String strNumber_1, String strNumber_2) {
		char[] array1 = strNumber_1.toCharArray();
		char[] array2 = strNumber_2.toCharArray();
		Arrays.sort(array1); Arrays.sort(array2);
		if (String.copyValueOf(array1).equals(String.copyValueOf(array2))) return true;
		else return false;
	}
	public static int wrongNumber(String strNumber) {
		for (int i = 0; i < NUMBER.length; i++) 
			if (strNumber.length() == NUMBER[i].length() && compareStringNumber(strNumber, NUMBER[i])) return i;
		return -1;
	}

	public static int getNumber(String strNumber) {
		for (int i = 0; i < NUMBER.length; i++) {
			if (strNumber.equals(NUMBER[i])) return i;
		}
		return wrongNumber(strNumber);
	}
	public static int calculator(int a, int b, String operation) {
		switch (operation) {
		case "+": return a+b; case "-": return a-b; case "*": return a*b;
		}
		return -1;
	}
}
