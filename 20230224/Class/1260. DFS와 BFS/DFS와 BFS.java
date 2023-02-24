import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static boolean[] check;
	static int N;
	
	static void dfs(int E) {
		check[E] = true;
		System.out.print(E + " ");
		
		
		for (int i = 1; i <= N; i++) {
			if (check[i] != true && arr[E][i] == 1) {
				dfs(i);
			}
		}
	}
	
	static void bfs(int E) {
		System.out.print(E + " ");
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(E);
		check[E] = true;
		
		while(!queue.isEmpty()) {
			int outValue = queue.poll();
			
			for (int i = 1; i <= N; i++) {
				if (check[i] != true && arr[outValue][i] == 1) {
					queue.offer(i);
					System.out.print(i + " ");
					check[i] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			arr[first][second] = 1;
			arr[second][first] = 1;
		}
		
		check = new boolean[N+1];
		dfs(V);
		
		System.out.println();
		
		check = new boolean[N+1];
		bfs(V);
	}

}