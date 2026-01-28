import java.io.*;
import java.util.*;

public class Main {
	
	static class Node implements Comparable<Node> {
		int number;
		int weight;
		
		Node() {}
		Node(int number, int weight) {
			this.number = number;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node node) {
			return this.weight - node.weight;
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<Node>[] graph = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[start].add(new Node(end, weight));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		int[] D = new int[N+1];
		Arrays.fill(D, Integer.MAX_VALUE);
		D[start] = 0;
		
		boolean[] check = new boolean[N+1];
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node output = pq.poll();
			
			if (check[output.number]) {
				continue;
			}
			
			check[output.number] = true;
			if (output.number == end) {
				System.out.println(output.weight);
				break;
			}
			
			for (Node node : graph[output.number]) {
				if (!check[node.number] && output.weight + node.weight < D[node.number]) {
					D[node.number] = output.weight + node.weight;
					pq.offer(new Node(node.number, D[node.number]));
				}
			}
		}
	}
}