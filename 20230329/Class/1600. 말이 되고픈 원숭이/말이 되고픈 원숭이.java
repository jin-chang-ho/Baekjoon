import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[H][W];
		
		boolean[][][] check = new boolean[K+1][H][W];
		
		for (int k = 0; k <= K; k++) {
			check[k][0][0] = true;
		}
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] == 1) {
					for (int k = 0; k <= K; k++) {
						check[k][i][j] = true;
					}
				}
			}
		}
		
		// 상, 하, 좌, 우
		int[] basedx = {0, 0, -1, 1};
		int[] basedy = {-1, 1, 0, 0};
		
		// 말
		int[] horsedx = {-2, -1, 1, 2, -2, -1, 1, 2};
		int[] horsedy = {-1, -2, -2, -1, 1, 2, 2, 1};

		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0, 0, 0, 0}); // x, y, check, weight
		
		while (!queue.isEmpty()) {
			int[] output = queue.poll();
			
			if (output[0] == W - 1 && output[1] == H - 1) {
				System.out.println(output[3]);
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int basecx = output[0] + basedx[i];
				int basecy = output[1] + basedy[i];
				
				if (0 <= basecx && basecx < W && 0 <= basecy && basecy < H && check[output[2]][basecy][basecx] == false) {
					queue.offer(new int[] {basecx, basecy, output[2], output[3] + 1});
					
					for (int k = output[2]; k <= K; k++) {
						check[k][basecy][basecx] = true;
					}
				}
			}
			
			if (output[2] < K) {
				for (int i = 0; i < 8; i++) { // 말로 이동할 수 있는가?
					int horsecx = output[0] + horsedx[i];
					int horsecy = output[1] + horsedy[i];
					
					if (0 <= horsecx && horsecx < W && 0 <= horsecy && horsecy < H && check[output[2] + 1][horsecy][horsecx] == false) {
						queue.offer(new int[] {horsecx, horsecy, output[2] + 1, output[3] + 1});
						for (int k = output[2] + 1; k <= K; k++) {
							check[k][horsecy][horsecx] = true;
						}
					}
				}
			}
		}
		
		System.out.println(-1);
	}
}