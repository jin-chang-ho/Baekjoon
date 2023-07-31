import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[][] map = new boolean[N+1][N+1];
		
		int K = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			map[y][x] = true;
		}
		
		int L = Integer.parseInt(br.readLine());
		
		Queue<int[]> rotateQueue = new ArrayDeque<>(); // L이 1, D가 2
		
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int time = Integer.parseInt(st.nextToken());
			char ch = st.nextToken().charAt(0);
			
			if (ch == 'L') {
				rotateQueue.offer(new int[] {time, 1});
			} else {
				rotateQueue.offer(new int[] {time, 2});
			}
		}
		
		List<int[]> snakeList = new ArrayList<>();
		snakeList.add(new int[] {1, 1}); // x, y
		
		int direction = 1; // 상, 우, 하, 좌 - 0, 1, 2, 3
		int time = 1;
		
		while (true) {
			// 1. 뱀 이동
			int[] preHead = snakeList.get(snakeList.size() - 1);
			
			if (direction == 0) {
				snakeList.add(new int[] {preHead[0], preHead[1] - 1});
			} else if (direction == 1) {
				snakeList.add(new int[] {preHead[0] + 1, preHead[1]});
			} else if (direction == 2) {
				snakeList.add(new int[] {preHead[0], preHead[1] + 1});
			} else {
				snakeList.add(new int[] {preHead[0] - 1, preHead[1]});
			}
			
			// 2. 충돌난 지 확인 후 충돌했으면 break 
			int[] moveHead = snakeList.get(snakeList.size() - 1);
			
			if (moveHead[0] < 1 || moveHead[0] > N || moveHead[1] < 1 || moveHead[1] > N) {
				break;
			}
			
			boolean collide = false;
			
			for (int i = 0; i < snakeList.size() - 1; i++) {
				int[] snakeBody = snakeList.get(i);
				
				if (moveHead[0] == snakeBody[0] && moveHead[1] == snakeBody[1]) {
					collide = true;
					break;
				}
			}
			
			if (collide) {
				break;
			}
			
			// 3. 사과 여부에 따른 길이 조절
			if (map[moveHead[1]][moveHead[0]] == false) {
				snakeList.remove(0);
			} else {
				map[moveHead[1]][moveHead[0]] = false;
			}
			
			// 4. 돌아야 하면 돌려줌
			if (!rotateQueue.isEmpty()) {
				int[] rotate = rotateQueue.peek();
				
				if (time == rotate[0]) {
					if (rotate[1] == 1) {
						direction = direction - 1;
						
						if (direction == -1) {
							direction = 3;
						}
						
					} else {
						direction = (direction + 1) % 4;
					}
					
					rotateQueue.poll();
				}
			}
			
			// 5. 시간의 흐름
			time++;
		}
		
		System.out.println(time);
	}
}