import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		TreeMap<Integer, Integer> map = new TreeMap<>();
		
		for (int i = 1; i <= TC; i++) {
			int k = Integer.parseInt(br.readLine());
			
			for (int j = 0; j < k; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String operator = st.nextToken();
				int number = Integer.parseInt(st.nextToken());
				
				if (operator.equals("I")) {
					if (!map.containsKey(number)) {
						map.put(number, 1);
					} else {
						map.put(number, map.get(number) + 1);
					}
				} else {
					if (!map.isEmpty()) {
						if (number == -1) {
							Entry<Integer, Integer> entry = map.firstEntry();
							int key = entry.getKey();
							int value = entry.getValue();
							
							if (entry.getValue() == 1) {
								map.remove(key);
							} else {
								map.put(key, value - 1);
							}
							
						} else {
							Entry<Integer, Integer> entry = map.lastEntry();
							int key = entry.getKey();
							int value = entry.getValue();
							
							if (entry.getValue() == 1) {
								map.remove(key);
							} else {
								map.put(key, value - 1);
							}
						}
					}
				}
			}
			
			if (map.isEmpty()) {
				sb.append("EMPTY \n");
			} else {
				if (map.size() == 1) {
					sb.append(map.firstKey() + " " + map.firstKey() + "\n");
				} else {
					sb.append(map.lastKey() + " " + map.firstKey() + "\n");
				}
			}
			
			map.clear();
		}
		
		System.out.println(sb.toString());
	}
}