import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution
{
	static int N;
	static int arr[][];
	
	// 상, 하, 좌, 우
	static int dx[] = {0, 0, -1, 1};
	static int dy[] = {-1, 1, 0, 0};
	
	private static int dijkstra() {
		int[][] d = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				d[i][j] = Integer.MAX_VALUE;
			}
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<> (new Comparator<int[]>() { // x, y, 비용
			@Override
			public int compare(int[] arr1, int[] arr2) {
				return arr1[2] - arr2[2];
			}
		});
		
		d[0][0] = 0;
		pq.offer(new int[] {0, 0, 0});
		
		while (!pq.isEmpty()) {
			int[] output = pq.poll();
			
			if (visited[output[1]][output[0]] == true) {
				continue;
			}
			
			if (output[0] == N - 1 && output[1] == N - 1) {
				return output[2];
			}
			
			visited[output[1]][output[0]] = true;
			
			for (int i = 0; i < 4; i++) {
				int cx = output[0] + dx[i];
				int cy = output[1] + dy[i];
				
				if (0 <= cx && cx < N && 0 <= cy && cy < N && visited[cy][cx] == false && d[cy][cx] > d[output[1]][output[0]] + arr[output[1]][output[0]]) {
					d[cy][cx] = d[output[1]][output[0]] + arr[output[1]][output[0]];
					pq.offer(new int[] {cx, cy, d[cy][cx]});
				}
			}	
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int TC = Integer.parseInt(br.readLine());
		
        for (int tc = 1; tc <= TC; tc++) {
        	N = Integer.parseInt(br.readLine());
            
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                 
                for (int j = 0; j < N; j++) {
                    arr[i][j] = line.charAt(j) - '0';
                }
            }
            
            sb.append("#" + tc + " " + dijkstra() + "\n");
        }
        
        System.out.println(sb.toString());
	}
}