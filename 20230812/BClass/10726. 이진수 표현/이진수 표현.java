import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
						
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int target = -(int) Math.pow(2, N);
			
			if ((M | target) == -1) {
				sb.append("#" + tc + " ON\n");
			} else {
				sb.append("#" + tc + " OFF\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}