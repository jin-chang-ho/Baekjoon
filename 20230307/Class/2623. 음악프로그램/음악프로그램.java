import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] inDegree;
	static int[] find;
	static List<Integer>[] listArr;
	static int N;
	static int chase = 0;
	
	static void findRoute() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int output = queue.poll();
			
			for (int v : listArr[output]) {
				inDegree[v]--;
				
				if (inDegree[v] == 0) {
					queue.offer(v);
				}
			}
			
			find[chase] = output;
			chase++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		listArr = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<Integer>();
		}
		
		inDegree = new int[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int serveN = Integer.parseInt(st.nextToken());
			
			int[] targets = new int[serveN];
			for (int j = 0; j < serveN; j++) {
				int target = Integer.parseInt(st.nextToken());
				targets[j] = target;
			}
			
			for (int j = 0; j < serveN - 1; j++) {
				listArr[targets[j]].add(targets[j+1]);
			}
			
			for (int j = 1; j < serveN; j++) {
				inDegree[targets[j]]++;
			}
		}
		
		find = new int[N];
		
		findRoute();
		
		for (int i = 0; i < N; i++) {	
			if (find[i] == 0) {
				sb = new StringBuilder();
				sb.append(0);
				break;
			}
			
			
			sb.append(find[i] + "\n");
		}
		
		System.out.println(sb.toString());
	}
}