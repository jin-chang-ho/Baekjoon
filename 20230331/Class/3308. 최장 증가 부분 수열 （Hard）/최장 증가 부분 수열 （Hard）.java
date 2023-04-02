import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			List<Integer> LIS = new ArrayList<>();
			
			for (int value : arr) {
				boolean check = false;
				
				for (int i = 0; i < LIS.size(); i++) {
					if (value <= LIS.get(i)) {
						LIS.set(i, value);
						check = true;
						break;
					}
				}
				
				if (check == true) {
					continue;
				}
				
				LIS.add(value);
			}
			
			sb.append("#" + tc + " " + LIS.size() + "\n");
		}
		
		System.out.println(sb.toString());
	}
}