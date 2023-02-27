import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static int[] arr;
	static int N;
	
	static void make() {
		for (int i = 1; i <= N; i++) {
			arr[i] = -1;
		}
	}
	
	static void union(int first, int second) {
		int checkOne = find(second);
		int checkTwo = find(first);
		
		if (checkOne != checkTwo) {
			arr[find(second)] = find(first);
		}
	}
	
	static int find(int chase) {
		if (arr[chase] == -1) {
			return chase;
		}
		
		return arr[chase] = find(arr[chase]);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			arr = new int[N + 1];
			make();
			
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				
				if (first < second) {
					union(first, second);
				} else {
					union(second, first);
				}
			}
			
			int count = 0;
			
			for (int j = 1; j <= N; j++) {
				if (arr[j] == -1) {
					count++;
				}
			}
			
			sb.append("#" + i + " " + count + "\n");
		}
		
		System.out.println(sb.toString());
	}
}