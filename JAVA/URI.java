import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class URI {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			while (cases-- > 0) {
				String input = reader.readLine();
				StringBuffer strBuffer = new StringBuffer();
				for (int i = 0; i < input.length(); i++) {
					if (input.charAt(i) == '%') {
						strBuffer.append(decodingString(input.substring(i, i+3)));
						i+=2;
					}
					else strBuffer.append(input.charAt(i));
				}
				writer.append(strBuffer.toString()); writer.append("\n");
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
	
	public static String decodingString(String encodingString) {
		switch (encodingString) {
		case "%20" : return " ";
		case "%21" : return "!";
		case "%24" : return "$";
		case "%25" : return "%";
		case "%28" : return "(";
		case "%29" : return ")";
		case "%2a" : return "*";
		}
		return null;
	}

}
