import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<Integer> list = new ArrayList<>();
		
		int[] arr = new int[K];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		
		for (int i = 0; i < K; i++) {
			if (list.contains(arr[i])) {
				continue;
			}
			
			if (list.size() == N) {
				PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
					@Override
					public int compare(int[] arr1, int[] arr2) {
						return -(arr1[1] - arr2[1]);
					}
				});
				
				for (int j = 0; j < list.size(); j++) {
					int listValue = list.get(j);
					
					int index = Integer.MAX_VALUE;
					
					for (int k = i + 1; k < K; k++) {
						if (arr[k] == listValue) {
							index = k;
							break;
						}
					}
					
					pq.offer(new int[] {j, index});
				}
				
				int[] output = pq.poll();
				
				list.remove(output[0]);
				list.add(output[0], arr[i]);
				
				answer++;
			} else {
				list.add(arr[i]);
			}
		}
		
		System.out.println(answer);
	}
}