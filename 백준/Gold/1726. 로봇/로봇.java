import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[M][N];
		boolean[][][] visited = new boolean[5][M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int startY = Integer.parseInt(st.nextToken()) - 1;
		int startX = Integer.parseInt(st.nextToken()) - 1;
		int startDirection = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int endY = Integer.parseInt(st.nextToken()) - 1;
		int endX = Integer.parseInt(st.nextToken()) - 1;
		int endDirection = Integer.parseInt(st.nextToken());
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {startX, startY, startDirection, 0});
		visited[startDirection][startY][startX] = true;
		
		// 우, 좌, 하, 상
		int[] dx = {0, 1, -1, 0, 0};
		int[] dy = {0, 0, 0, 1, -1};
		
		while (!queue.isEmpty()) {
			int[] output = queue.poll();
			
			if (output[0] == endX && output[1] == endY && output[2] == endDirection) {
				System.out.println(output[3]);
				break;
			}
			
			for (int i = 1; i <= 3; i++) {
                int nx = output[0] + (dx[output[2]] * i);
                int ny = output[1] + (dy[output[2]] * i);

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) break;

                if (arr[ny][nx] == 0) {
                    if (!visited[output[2]][ny][nx]) {
                        visited[output[2]][ny][nx] = true;
                        queue.add(new int[] {nx, ny, output[2], output[3] + 1});
                    }
                } else {
                    break;
                }
            }

            for (int i = 1; i <= 4; i++) {
                if (output[2] != i && !visited[i][output[1]][output[0]]) {
                    int turn = 1;
                    if (output[2] == 1) {
                        if (i == 2) {
                            turn++;
                        }
                    } else if (output[2] == 2) {
                        if (i == 1) {
                            turn++;
                        }
                    } else if (output[2] == 3) {
                        if (i == 4) {
                            turn++;
                        }
                    } else {
                        if (i == 3) {
                            turn++;
                        }
                    }

                    visited[i][output[1]][output[0]] = true;
                    queue.add(new int[] {output[0], output[1], i, output[3] + turn});
                }
            }
		}
	}
}