import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int len = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			Set<Integer>[] arr = new HashSet[101];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < len; i = i + 2) {
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				
				if (arr[first] == null) {
					arr[first] = new HashSet<Integer>();
				}
				
				arr[first].add(second);
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] arr1, int[] arr2) {
					if (arr1[1] == arr2[1]) {
						return -(arr1[0] - arr2[0]);
					}
					
					return -(arr1[1] - arr2[1]);
				}	
			});
			
			Queue<int[]> queue = new ArrayDeque<>();
			
			for (int v : arr[start]) {
				queue.add(new int[] {v, 0});
				pq.add(new int[] {v, 0});
			}
			
			boolean[] check = new boolean[101];
			
			while (!queue.isEmpty()) {
				int[] output = queue.poll();
				check[output[0]] = true;
				
				if (arr[output[0]] != null) {
					for (int v : arr[output[0]]) {
						if (check[v] == false) {
							queue.add(new int[] {v, output[1] + 1});
							pq.add(new int[] {v, output[1] + 1});
							check[v] = true;
						}
					}
				}
			}
			
			sb.append("#" + tc + " " + pq.peek()[0] + "\n");
		}
		
		System.out.println(sb.toString());
	}
}