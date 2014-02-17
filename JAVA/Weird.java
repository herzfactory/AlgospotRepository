import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;


public class Weird {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				int number = Integer.parseInt(reader.readLine());
				Vector<Integer> setOfDivisors = new Vector<Integer>();
				for (int i = 1; i * i <= number; i++) {
					if (number % i == 0) {
						setOfDivisors.add(i);
						if (i != number / i) setOfDivisors.add(number/i);
					}
				}
				setOfDivisors.remove((Object)number);
				quickSortOrderByAsce(0, setOfDivisors.size() - 1, setOfDivisors);
				int sumOfDivisor = 0;
				for (int i = 0; i < setOfDivisors.size(); i++) sumOfDivisor += setOfDivisors.get(i); 
				if (sumOfDivisor > number && !checkWeirdNumber(setOfDivisors, number)) writer.append("weird");
				else writer.append("not weird");
				writer.append("\n");
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkWeirdNumber(Vector<Integer> array, int number) {
		int sumOfDivisor = 0;
		for (int i = 0; i < array.size(); i++) {
			sumOfDivisor += array.get(i);
			if (sumOfDivisor == number) return true;
			else if (sumOfDivisor < number) return checkPseudoPerfectNumber(array, number, sumOfDivisor, i);
			sumOfDivisor -= array.get(i);
		}
		return false;
	}
	
	public static boolean checkPseudoPerfectNumber(Vector<Integer> array, int number, int sumOfDivisor, int index) {
		for (int i = index + 1; i < array.size(); i++) {
			sumOfDivisor += array.get(i);
			if (sumOfDivisor == number) return true;
			else if (sumOfDivisor < number) return checkPseudoPerfectNumber(array, number, sumOfDivisor, i);
			sumOfDivisor -= array.get(i);
		}
		return false;
	}
	
	public static void quickSortOrderByAsce(int left, int right, Vector<Integer> array) {
		int h_left = left;
		int h_right = right;
		int pivot = array.get(left);
		while (left < right) {
			while (array.get(right) < pivot && left < right) right--;
			if (left != right) array.set(left, array.get(right)); 
			while (array.get(left) > pivot && left < right) left++;
			if (left != right) {
				array.set(right, array.get(left)); right--;
			}
		}
		array.set(left, pivot);
		pivot = left;
		left = h_left;
		right = h_right;
		if (left < pivot) quickSortOrderByAsce(left, pivot - 1, array);
		if (pivot < right) quickSortOrderByAsce(pivot + 1, right, array);
	}

}
