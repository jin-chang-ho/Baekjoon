import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		Queue<Character> queue = new ArrayDeque<>();
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			queue.add(ch);
		}
		
		if (queue.isEmpty()) {
			System.out.println("1");
			return;
		}
		
		Map<Character, Integer> counter = new HashMap<>();
		char preCh = 's';
		char ch = ' ';
		
		while (!queue.isEmpty()) {
			ch = queue.poll();
			
			if (preCh != ch) {
				if (preCh == 's' || preCh == 'f') {
					if (ch != 'w') {
						System.out.println(0);
						return;
					}
				}
				
				if (preCh == 'w') {
					if (ch != 'o') {
						System.out.println(0);
						return;
					}
				}
				
				if (preCh == 'o') {
					if (ch != 'l') {
						System.out.println(0);
						return;
					}
				}
				
				if (preCh == 'l') {
					if (ch != 'f') {
						System.out.println(0);
						return;
					}
				}
			}

			if (ch == 'w' && preCh == 'f') {
				if (counter.size() != 4) {
					System.out.println(0);
					return;
				}
				
				int firstNum = -1;
				
				for (Entry<Character, Integer> entry : counter.entrySet()) {
					int count = entry.getValue();
 					
					if (firstNum == -1) {
						firstNum = count;
					} else {
						if (count != firstNum) {
							System.out.println(0);
							return;
						}
					}
				}
				
				counter.clear();
			}
			
			if (counter.containsKey(ch)) {
				counter.put(ch, counter.get(ch) + 1);
			} else {
				counter.put(ch, 1);
			}
			
			preCh = ch;
		}
		
		if (ch != 'f') {
			System.out.println("0");
			return;
		}
		
		if (counter.size() != 4) {
			System.out.println(0);
			return;
		}
		
		int firstNum = -1;
		
		for (Entry<Character, Integer> entry : counter.entrySet()) {
			int count = entry.getValue();
				
			if (firstNum == -1) {
				firstNum = count;
			} else {
				if (count != firstNum) {
					System.out.println(0);
					return;
				}
			}
		}
		
		System.out.println(1);
	}
}