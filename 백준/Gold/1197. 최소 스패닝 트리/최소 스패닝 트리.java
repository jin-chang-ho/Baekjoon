import java.io.*;
import java.util.*;

public class Main {
	static int V;
	static int E;
	static int[] arr;
	
	static void makeSet() {
		for (int i = 1; i <= V; i++) {
			arr[i] = i;
		}
	}
	
	static int findSet(int x) {
		if (x == arr[x]) {
			return x;
		}
		
		return arr[x] = findSet(arr[x]);
	}
	
	static boolean unionSet(int x, int y) {
		int first = findSet(x);
		int second = findSet(y);
		
		if (first == second) {
			return false;
		}
		
		arr[second] = first;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] a1, int[] a2) {
				return a1[2] - a2[2];
			}
		});
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.offer(new int[] {start, end, weight});
		}
		
		arr = new int[V+1];
		makeSet();
		
		int answer = 0;
		int count = 0;
		
		while (!pq.isEmpty()) {
			int[] output = pq.poll();
			
			if (unionSet(output[0], output[1])) {
				answer += output[2];
				count++;
				
				if (count == V-1) {
					break;
				}
			}
		}
		
		sb.append(answer);
		bw.write(sb.toString());
		bw.flush();
	}
}