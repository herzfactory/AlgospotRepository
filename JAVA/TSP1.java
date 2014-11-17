import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class TSP1 {
	
	private static int numOfCities;
	private static double[][] distances;
	private static boolean[] visitCities;

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				numOfCities = Integer.parseInt(reader.readLine());
				distances = new double[numOfCities][numOfCities];
				visitCities = new boolean[numOfCities];
				
				for (int i = 0; i < numOfCities; i++) {
					String[] input = reader.readLine().split("  ");
					for (int j = 0; j < numOfCities; j++) {
						distances[i][j] = Double.parseDouble(input[j]);
					}
				}
				String ret = String.format("%.10f", travelingCities(0, -1, 0));
				writer.append(ret);
				writer.newLine();
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static double travelingCities(double totalDistance, int city, int countOfVisited) {	
		double ret = Double.MAX_VALUE;
		
		if (countOfVisited == numOfCities) return totalDistance;
		
		for (int i = 0; i < numOfCities; i++) {
			if (visitCities[i]) continue;
			visitCities[i] = true;
			if (city != -1) totalDistance += distances[city][i];
			ret = Math.min(ret, travelingCities(totalDistance, i, countOfVisited + 1));
			if (city != -1) totalDistance -= distances[city][i];
			visitCities[i] = false;
		}
		return ret;
	}
}
