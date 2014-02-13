import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class HotSummer {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				int targetedPowerUsage = Integer.parseInt(reader.readLine());
				String[] powerUsage = reader.readLine().split(" ");
				for (int i = 0; i < powerUsage.length; i++) targetedPowerUsage -= Integer.parseInt(powerUsage[i]);
				if (targetedPowerUsage >= 0) writer.append("YES\n");
				else writer.append("NO\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
