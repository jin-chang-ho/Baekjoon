import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		int sharkX = 0;
		int sharkY = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] == 9) {
					sharkY = i;
					sharkX = j;
					arr[i][j] = 0;
				}
			}
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] element1, int[] element2) { // dist, x, y 형태로 저장할 예정
				if (element1[0] == element2[0]) {
					if (element1[2] == element2[2]) {
						return element1[1] - element2[1];
					}
					return element1[2] - element2[2];
				}
				return element1[0] - element2[0];
			}	
		});
		
		// 좌, 우, 상, 하
		int[] cx = {-1, 1, 0, 0};
		int[] cy = {0, 0, -1, 1};
		
		int level = 2;
		int eat = 0;
		int answer = 0;
		
		while (true) {
			int[] start = {0, sharkX, sharkY};
			pq.offer(start);
			
			boolean check = true;
			
			boolean[][] boolArr = new boolean[N][N];
			boolArr[sharkY][sharkX] = true;
			
			while (!pq.isEmpty()) {
				int[] node = pq.poll();
				
				if (arr[node[2]][node[1]] != 0 && arr[node[2]][node[1]] < level) {
					eat += 1;
					
					if (eat == level) {
						eat = 0;
						level = level + 1;
					}
					
					arr[node[2]][node[1]] = 0;
					sharkX = node[1];
					sharkY = node[2];
					check = false;
					answer += node[0];
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int dx = node[1] + cx[i];
					int dy = node[2] + cy[i];
					
					if (0 <= dx && dx < N && 0 <= dy && dy < N && boolArr[dy][dx] == false && arr[dy][dx] <= level) {
						int[] temp = {node[0] + 1, dx, dy};
						boolArr[dy][dx] = true;
						pq.offer(temp);
					}
				}
			}
			
			if (check) {
				break;
			}
			
			pq.clear();
		}
		
		System.out.println(answer);

	}
}