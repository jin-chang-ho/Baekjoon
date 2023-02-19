import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		// 상, 하, 좌, 우
		int[] cx = {0, 0, -1, 1};
		int[] cy = {-1, 1, 0, 0};
		
		for (int i = 1; i <= TC; i++) {
			int N = Integer.parseInt(br.readLine());
			
			int arr[][] = new int[N][N];
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int k = 0; k < N; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<> (new Comparator<int[]> () {
				@Override
				public int compare(int[] arr1, int[] arr2) {
					if (arr1[0] == arr2[0]) {
						return arr1[1] - arr2[1];
					}
					
					return -(arr1[0] - arr2[0]);
				}
			});
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					int baseX = k;
					int baseY = j;
					int count = 0;
					Queue<int[]> que = new ArrayDeque<>();
					que.offer(new int[] {baseX, baseY});
					
					while (!que.isEmpty()) {
						int [] queArr = que.poll();
						
						for (int l = 0; l < 4; l++) {
							int dx = queArr[0] + cx[l];
							int dy = queArr[1] + cy[l];
							
							if (0 <= dx && dx < N && 0 <= dy && dy < N && arr[queArr[1]][queArr[0]] + 1 == arr[dy][dx]) {
								que.offer(new int[] {dx, dy});
							}
						}
						
						count = count + 1;
					}
					
					pq.offer(new int[] {count, arr[baseY][baseX]});
				}
			}
			
			int[] result = pq.poll();
			System.out.println("#" + i + " " + result[1] + " " + result[0]);
		}
	} 
}