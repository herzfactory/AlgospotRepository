import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class WildCard {
	
	private static final int MAX_LENGTH = 101;
	private static String wildCard;
	private static String fileName;
	private static int numOfFiles;
	private static int[][] cache;
	
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				wildCard = reader.readLine();
				numOfFiles = Integer.parseInt(reader.readLine());
				List<String> answer = new ArrayList<String>(numOfFiles);
				for (int i = 0; i < numOfFiles; i++) {
					cache = new int[MAX_LENGTH][MAX_LENGTH];
					for (int j = 0; j < MAX_LENGTH; j++) Arrays.fill(cache[j], -1);
					fileName = reader.readLine();
					if (match(0, 0) == 1) answer.add(fileName);
				}
				Collections.sort(answer);
				for (int i = 0; i < answer.size(); i++) writer.append(answer.get(i) + "\n");
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int match(int w_index, int f_index) {
		int ret = cache[w_index][f_index];
		if (ret != -1) return ret;
		
		if (w_index < wildCard.length() && f_index < fileName.length()
				&& (wildCard.charAt(w_index) == '?'
				|| wildCard.charAt(w_index) == fileName.charAt(f_index)))
			return ret = cache[w_index][f_index] = match(w_index + 1, f_index + 1);
		
		if (w_index == wildCard.length())
			return ret = cache[w_index][f_index] = booleanToInteger(f_index == fileName.length());
		
		if (wildCard.charAt(w_index) == '*') {
			if (integerToBoolean(match(w_index + 1, f_index))
					|| (f_index < fileName.length() && integerToBoolean(match(w_index, f_index + 1))))
				return ret = cache[w_index][f_index] = 1;
		}
		
		return ret = cache[w_index][f_index] = 0;
	}
	
	public static boolean integerToBoolean(int value) {
		return value != 0;
	}
	
	public static int booleanToInteger(boolean value) {
		return (value) ? 1 : 0;
 	}

}
