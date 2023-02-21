import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] hp, weight;
	static int[] check;
	static int maxCount = 0;
	
	static void dfs(int start) {
		if (start == N) {
			int count = 0;
			
			for (int i = 0; i < N; i++) {
				count += check[i];
			}
			
			if (count > maxCount) {
				maxCount = count;
			}
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (i == start) {
				continue;
			}
			
			boolean collide = false;
			
			if (check[start] == 0 && check[i] == 0) {
				hp[start] -= weight[i];
				hp[i] -= weight[start];
				collide = true;
			}
			
			if (hp[start] <= 0) {
				check[start] = 1;
			}
			
			if (hp[i] <= 0) {
				check[i] = 1;
			}
			
			dfs(start + 1);
			
			if (collide) {
				hp[start] += weight[i];
				hp[i] += weight[start];
			}
			
			if (hp[start] > 0) {
				check[start] = 0;
			}
			
			if (hp[i] > 0) {
				check[i] = 0;
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		hp = new int[N];
		weight = new int[N];
		check = new int[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			hp[i] = Integer.parseInt(st.nextToken());
			weight[i] = Integer.parseInt(st.nextToken());
		}	
		
		dfs(0);
		
		System.out.println(maxCount);
	}
}