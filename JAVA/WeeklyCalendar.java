import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class WeeklyCalendar {
	
	private static final int DAY_OF_WEEK = 7;
	private static int[] lastDayOfMonth = {28, 30, 31};
	private static String[] dayOfTheWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				String[] input = reader.readLine().split(" ");
				int month = Integer.parseInt(input[0]), day = Integer.parseInt(input[1]), sunday = 0;
				for (int i = 0; i < DAY_OF_WEEK; i++) if (dayOfTheWeek[i].equals(input[2])) sunday = day - i;
				String weeklyCalendar = "";
				switch (month) {
				case 1 : case 8 :
					weeklyCalendar = createWeeklyCalendar(sunday, lastDayOfMonth[2], lastDayOfMonth[2]);
					break;
				case 2 :
					weeklyCalendar = createWeeklyCalendar(sunday, lastDayOfMonth[2], lastDayOfMonth[0]);
					break;
				case 3 :
					weeklyCalendar = createWeeklyCalendar(sunday, lastDayOfMonth[0], lastDayOfMonth[2]);
					break;
				case 4 : case 6 : case 9 : case 11 :
					weeklyCalendar = createWeeklyCalendar(sunday, lastDayOfMonth[2], lastDayOfMonth[1]);
					break;
				case 5 : case 7 : case 10 : case 12 :
					weeklyCalendar = createWeeklyCalendar(sunday, lastDayOfMonth[1], lastDayOfMonth[2]);
					break;
				}
				writer.append(weeklyCalendar);
			}
			writer.flush();
			writer.close(); reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String createWeeklyCalendar(int sunday, int last, int current) {
		StringBuffer stringBuffer = new StringBuffer();
		int lastday = current;
		if (sunday < 1) {
			sunday += last;
			lastday = last; 
		}
		for (int i = 0; i < DAY_OF_WEEK; i++) {
			if (i != 0) stringBuffer.append(" ");
			int day = (sunday + i) % lastday;
			if (day == 0) day = lastday;
			stringBuffer.append(String.valueOf(day));
		}
		stringBuffer.append("\n");
		return stringBuffer.toString();
	}
}
