import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;


public class Convert {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine()), index = 0;

			while (index++ < cases) {
				StringBuffer stringBuffer = new StringBuffer();
				DecimalFormat format = new DecimalFormat("0.0000");
				String convertMeasurement = "";
				String[] input = reader.readLine().split(" ");
				double value = Double.parseDouble(input[0]);
				stringBuffer.append(index + " ");
				
				switch (input[1]) {
				case "kg" :
					value = value * 2.2046;
					convertMeasurement = " lb\n";
					break;
				case "l" :
					value = value * 0.2642;
					convertMeasurement = " g\n";
					break;
				case "lb" :
					value = value * 0.4536;
					convertMeasurement = " kg\n";
					break;
				case "g" :
					value = value * 3.7854;
					convertMeasurement = " l\n";
					break;
				}
				value = Math.round(value * 10000) / 10000.0d;
				stringBuffer.append(String.valueOf(format.format(value)));
				stringBuffer.append(convertMeasurement);
				writer.append(stringBuffer.toString());
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
