import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Lecture {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				char[] input = reader.readLine().toCharArray();
				int inputSize = input.length / 2;
				char[][] words = new char[inputSize][2];
				for (int i = 0, index = 0; index < inputSize; i+=2, index++) {
					words[index][0] = input[i]; words[index][1] = input[i+1];
				}
				quickSortOrderByAsce(words, 0, words.length - 1);
				for (int i = 0; i < words.length; i++) {
					writer.append(words[i][0]); writer.append(words[i][1]);
				}
				writer.append("\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void quickSortOrderByAsce(char[][] charArr, int left, int right) {
		int l_hold = left, r_hold = right;
		char[] pivot = {charArr[left][0], charArr[left][1]};
		while (left < right) {
			while ((charArr[right][0] > pivot[0] || (charArr[right][0] == pivot[0] && charArr[right][1] >= pivot[1]))&& left < right) right--;
			if (left != right && charArr[right][0] == pivot[0] && charArr[right][1] < pivot[1]){
				charArr[left][0] = charArr[right][0]; charArr[left][1] = charArr[right][1];
			}
			else if (left != right && charArr[right][0] < pivot[0]) {
				charArr[left][0] = charArr[right][0]; charArr[left][1] = charArr[right][1];
			}
			while ((charArr[left][0] < pivot[0] || (charArr[left][0] == pivot[0] && charArr[left][1] <= pivot[1]))&& left < right) left++;
			if (left != right && charArr[left][0] == pivot[0] && charArr[left][1] > pivot[1]) {
				charArr[right][0] = charArr[left][0]; charArr[right][1] = charArr[left][1];
				right--;
			}
			else if (left != right && charArr[left][0] > pivot[0]) {
				charArr[right][0] = charArr[left][0]; charArr[right][1] = charArr[left][1];
				right--;
			}
		}
		charArr[left][0] = pivot[0]; charArr[left][1] = pivot[1];
		int pivotP = left;
		left = l_hold; right = r_hold;
		if (left < pivotP) quickSortOrderByAsce(charArr, left, pivotP);
		if (right > pivotP) quickSortOrderByAsce(charArr, pivotP + 1, right);
	}
}
