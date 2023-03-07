import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static List<Integer>[] listArr;
	static int[] inDegree;
	static int[] line;
	static int chase = 1;
	
	static void findLine() {
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int number = queue.poll();
			
			line[chase] = number;
			chase++;
			
			for (int value : listArr[number]) {
				inDegree[value]--;
				
				if (inDegree[value] == 0) {
					queue.offer(value);
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		listArr = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<Integer>();
		}
		
		inDegree = new int[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			listArr[first].add(second);
			inDegree[second]++;
		}
		
		line = new int[N + 1];
		findLine();
		
		for (int i = 1; i <= N; i++) {
			sb.append(line[i] + " ");
		}
		
		System.out.println(sb.toString());
	}
}