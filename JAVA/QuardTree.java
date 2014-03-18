import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class QuardTree {
	public static class StringIterator implements Iterator<Character> {
		private String str;
		private int count = 0;
		public StringIterator(String s) {
			str = s;
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (count < str.length()) return true;
			return false;
		}

		@Override
		public Character next() {
			// TODO Auto-generated method stub
			if (count == str.length()) throw new NoSuchElementException();
			count++;
			return str.charAt(count-1);
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	}
	

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
			int cases = Integer.parseInt(reader.readLine());
			
			while (cases-- > 0) {
				String image = reader.readLine();
				StringIterator iterator = new StringIterator(image);
				writer.append(reverse(iterator));
				writer.append("\n");
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String reverse(StringIterator iterator) {
		char head = iterator.next();
		if (head == 'b' || head == 'w') return String.valueOf(head);
		String upperLeft = reverse(iterator);
		String upperRight = reverse(iterator);
		String lowerLeft = reverse(iterator);
		String lowerRight = reverse(iterator);
		StringBuffer buffer = new StringBuffer();
		buffer.append("x"); buffer.append(lowerLeft); buffer.append(lowerRight); buffer.append(upperLeft); buffer.append(upperRight);
		return buffer.toString();
	}

}
