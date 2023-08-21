import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < M; j++) {
				char number = line.charAt(j);
				arr[i][j] = number - '0';
			}
		}
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		int groupNumber = 10;
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					int count = 1;
					arr[i][j] = groupNumber;
					
					Queue<int[]> queue = new ArrayDeque<>();
					queue.offer(new int[] {j, i});
					
					while (!queue.isEmpty()) {
						int[] output = queue.poll();
						
						for (int k = 0; k < 4; k++) {
							int cx = output[0] + dx[k];
							int cy = output[1] + dy[k];
							
							if (0 <= cx && cx < M && 0 <= cy && cy < N && arr[cy][cx] == 0) {
								arr[cy][cx] = groupNumber;
								count++;
								queue.offer(new int[] {cx, cy});
							}
						}
					}
					
					map.put(groupNumber, count);
					groupNumber++;
				}
			}
		}
		
		int[][] answer = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					Set<Integer> set = new HashSet<>();
					
					for (int k = 0; k < 4; k++) {
						int cx = j + dx[k];
						int cy = i + dy[k];
						
						if (0 <= cx && cx < M && 0 <= cy && cy < N && arr[cy][cx] != 1) {
							set.add(arr[cy][cx]);
						}
					}
					
					
					answer[i][j] = 1;
					for (int value : set) {
						answer[i][j] += map.get(value);
					}
					answer[i][j] %= 10;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(answer[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}