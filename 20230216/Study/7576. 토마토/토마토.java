import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int arr[][] = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		Queue<int[]> que = new ArrayDeque<>();
		
		for (int i = 0; i < M; i++) { // 초기 익은 토마토 넣기
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1) {
					int[] tempArr = new int[2];
					tempArr[0] = i;
					tempArr[1] = j;
					
					que.offer(tempArr);
				}
			}
		}
		
		// 좌, 우, 상, 하
		int[] cx = {-1, 1, 0, 0};
		int[] cy = {0, 0, -1, 1};
		Queue<int[]> tempQue = new ArrayDeque<>();
		
		
		while (true) { // 토마토 상태 추적
			while (!tempQue.isEmpty()) {
				que.offer(tempQue.poll());
			}
			
			while (!que.isEmpty()) {
				int[] checkArr = que.poll();
				
				for (int i = 0; i < 4; i++) {
					int dx = checkArr[1] + cx[i];
					int dy = checkArr[0] + cy[i];			
					
					if (0 <= dx && dx < N && 0 <= dy && dy < M && arr[dy][dx] == 0) {
						arr[dy][dx] = 1;
						int[] tempArr = new int[2];
						tempArr[0] = dy;
						tempArr[1] = dx;
						tempQue.offer(tempArr);
					}
				}
			}
			
			if (tempQue.isEmpty()) {
				break;
			}
			
			result++;
		}
		
		for (int i = 0; i < M; i++) { // 만약 안익은 토마토가 있으면 result = -1 처리하고 반환
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0) {
					result = -1;
				}
			}
		}
		
		System.out.println(result);
	}
}