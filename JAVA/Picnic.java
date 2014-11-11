import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Picnic {
	
	private static boolean[] takenStudent;
	private static boolean[][] areFriends;
	private static int numOfStudent;
	private static int numOfPair;

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				String[] input = reader.readLine().split(" ");
				numOfStudent = Integer.parseInt(input[0]);
				numOfPair = Integer.parseInt(input[1]);
				takenStudent = new boolean[numOfStudent];
				areFriends = new boolean[numOfStudent][numOfStudent];
				input = reader.readLine().split(" ");
				for (int i = 0, index = 0; i < numOfPair; i++, index+=2) {
					int x = Integer.parseInt(input[index]), y = Integer.parseInt(input[index + 1]);
					areFriends[x][y] = areFriends[y][x] = true;
				}
				writer.append(String.valueOf(countPairing()));
				writer.newLine();
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int countPairing() {
		int firstFree = -1; int ret = 0;
		for (int i = 0; i < numOfStudent; i++) {
			if (!takenStudent[i]) {
				firstFree = i; break;
			}
		}
		if (firstFree == -1) return 1;
		for (int pairWith = firstFree + 1; pairWith < numOfStudent; pairWith++) {
			if (!takenStudent[pairWith] && areFriends[firstFree][pairWith]) {
				takenStudent[firstFree] = takenStudent[pairWith] = true;
				ret += countPairing();
				takenStudent[firstFree] = takenStudent[pairWith] = false;
			}
		}
		return ret;
	}

}
