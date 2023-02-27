import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static int n;
	static int[] arr;
	
	static void make() {
		for (int i = 1; i <= n; i++) {
			arr[i] = i;
		}
	}
	
	static void sum(int first, int second) {
		arr[find(second)] = find(first);
	}
	
	static int find(int check) {
		if (arr[check] == check) {
			return check;
		}
		
		return arr[check] = find(arr[check]);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			arr = new int[n + 1];
			make();
			
			sb.append("#" + tc + " ");
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int oper = Integer.parseInt(st.nextToken());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				
				if (oper == 0) {
					sum(first, second);
				}
				
				if (oper == 1) {
					if (find(first) == find(second)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}