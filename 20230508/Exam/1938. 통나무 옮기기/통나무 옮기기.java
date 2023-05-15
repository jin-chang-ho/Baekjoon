import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 지도의 크기
		
		char[][] arr = new char[N][N]; // 지도
		
		int BCount = 0; // B 중앙 위치를 찾기 위함
		int startX = -1; // B 중앙의 X 시작 위치
		int startY = -1; // B 중앙의 Y 시작 위치 
		int startDirection = -1; // 0이면 세로, 1이면 가로
		
		int ECount = 0; // E 중앙 위치를 찾기 위함
		int endX = -1; // E 중앙의 X 시작 위치
		int endY = -1; // E 중앙의 Y 시작 위치
		
		boolean[][][] check = new boolean[2][N][N]; // 세로와 가로 나눠서 방문 체크 

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = line.charAt(j);
				
				if (BCount == 1 && arr[i][j] == 'B') { // 시작 위치 설정
					startX = j;
					startY = i;
					
					if (startX - 1 < 0 || arr[startY][startX - 1] != 'B') {
						startDirection = 0;
					} else if (startY - 1 < 0 || arr[startY - 1][startX] != 'B') {
						startDirection = 1;
					}
				}
				
				if (arr[i][j] == 'B') {
					BCount++;
				}
				
				if (ECount == 1 && arr[i][j] == 'E') { // 끝 위치 설정
					endX = j;
					endY = i;
				}
				
				if (arr[i][j] == 'E') {
					ECount++;
				}
				
				if (arr[i][j] == '1') { // 기존 벽 설정
					for (int k = 0; k < 2; k++) {
						check[k][i][j] = true;
					}
				}
			} 
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { // weight에 따른 우선순위 큐
			@Override
			public int compare(int[] arr1, int[] arr2) {
				return arr1[3] - arr2[3];
			}
		});
		
		pq.offer(new int[] {startX, startY, startDirection, 0}); // x, y, direction, weight 설정
		
		check[startDirection][startY][startX] = true; // 시작 초기화
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		while (!pq.isEmpty()) {
			int[] output = pq.poll();
			
			// 도착한 경우
			if (output[0] == endX && output[1] == endY) {
				System.out.println(output[3]);
				return;
			}
			
			// 상, 하, 좌, 우 움직이기
			for (int i = 0; i < 4; i++) {
				int cx = output[0] + dx[i];
				int cy = output[1] + dy[i];
				
				if (0 <= cx && cx < N && 0 <= cy && cy < N && check[output[2]][cy][cx] == false) {
					if (output[2] == 0) { // 블럭이 세로일 때
						if (i == 0) { // 상
							if (0 <= cy - 1 && check[output[2]][cy - 1][cx] == false) {
								pq.offer(new int[] {cx, cy, output[2], output[3] + 1});
								check[output[2]][cy][cx] = true;
							}
						} else if (i == 1) { // 하
							if (cy + 1 < N && check[output[2]][cy + 1][cx] == false) {
								pq.offer(new int[] {cx, cy, output[2], output[3] + 1});
								check[output[2]][cy][cx] = true;
							}
						} else if (i == 2) { // 좌
							if (check[output[2]][cy - 1][cx] == false && check[output[2]][cy + 1][cx] == false) {
								pq.offer(new int[] {cx, cy, output[2], output[3] + 1});
								check[output[2]][cy][cx] = true;
							}
						} else { // 우
							if (check[output[2]][cy - 1][cx] == false && check[output[2]][cy + 1][cx] == false) {
								pq.offer(new int[] {cx, cy, output[2], output[3] + 1});
								check[output[2]][cy][cx] = true;
							}
						}
					} else { // 블럭이 가로일 때
						if (i == 0) { // 상
							if (check[output[2]][cy][cx - 1] == false && check[output[2]][cy][cx + 1] == false) {
								pq.offer(new int[] {cx, cy, output[2], output[3] + 1});
								check[output[2]][cy][cx] = true;
							}
						} else if (i == 1) { // 하
							if (check[output[2]][cy][cx - 1] == false && check[output[2]][cy][cx + 1] == false) {
								pq.offer(new int[] {cx, cy, output[2], output[3] + 1});
								check[output[2]][cy][cx] = true;
							}
						} else if (i == 2) { // 좌
							if (0 <= cx - 1 && check[output[2]][cy][cx - 1] == false) {
								pq.offer(new int[] {cx, cy, output[2], output[3] + 1});
								check[output[2]][cy][cx] = true;
							}
						} else { // 우
							if (cx + 1 < N && check[output[2]][cy][cx + 1] == false) {
								pq.offer(new int[] {cx, cy, output[2], output[3] + 1});
								check[output[2]][cy][cx] = true;
							}
						}
					}
				}
			}
			
			// 플레그가 없고, 회전할 수 있으면 회전하자.
			if (check[1][output[1]][output[0]] == false&& output[2] == 0) {
				if (0 <= output[0] - 1 && output[0] + 1 < N && arr[output[1] - 1][output[0] - 1] != '1' && arr[output[1]][output[0] - 1] != '1' && arr[output[1] + 1][output[0] - 1] != '1' && arr[output[1] - 1][output[0] + 1] != '1' && arr[output[1]][output[0] + 1] != '1' && arr[output[1] + 1][output[0] + 1] != '1') {
					pq.offer(new int[] {output[0], output[1], 1, output[3] + 1});
				}
			} else if (check[0][output[1]][output[0]] == false && output[2] == 1) {
				if (0 <= output[1] - 1 && output[1] + 1 < N && arr[output[1] - 1][output[0] - 1] != '1' && arr[output[1] - 1][output[0]] != '1' && arr[output[1] - 1][output[0] + 1] != '1' && arr[output[1] + 1][output[0] - 1] != '1' && arr[output[1] + 1][output[0]] != '1' && arr[output[1] + 1][output[0] + 1] != '1') {
					pq.offer(new int[] {output[0], output[1], 0, output[3] + 1});
				}
			}
		}
		
		System.out.println(0); // 도착하지 못한 경우
	}
}