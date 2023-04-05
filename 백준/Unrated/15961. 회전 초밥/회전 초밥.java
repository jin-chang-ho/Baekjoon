import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < k; i++) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}
		
		if (map.containsKey(c)) {
			map.put(c, map.get(c) + 1);
		} else {
			map.put(c, 1);
		}
		
		int maxLength = map.size();
		
		for (int i = 0; i < N - k; i++) {
			int out = arr[i];
			
			if (map.get(out) == 1) {
				map.remove(out);
			} else {
				map.put(out, map.get(out) - 1);
			}
			
			int in = arr[i+k];
			
			if (map.containsKey(in)) {
				map.put(in, map.get(in) + 1);
			} else {
				map.put(in, 1);
			}
			
			maxLength = Math.max(maxLength, map.size());
		}
		
		for (int i = N - k; i < N - 1; i++) {
			int out = arr[i];
			
			if (map.get(out) == 1) {
				map.remove(out);
			} else {
				map.put(out, map.get(out) - 1);
			}
			
			int in = arr[(i + k) % N];
			
			if (map.containsKey(in)) {
				map.put(in, map.get(in) + 1);
			} else {
				map.put(in, 1);
			}
			
			maxLength = Math.max(maxLength, map.size());
		}
		
		System.out.println(maxLength);
	}
}