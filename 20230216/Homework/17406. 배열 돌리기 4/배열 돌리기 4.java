import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static String[][] orderArr;
	static int orderChase = 0;
	static int orderLimit = 1;
	static String[] order;
	static int[] orderIndex;
	static boolean[] orderCheck;
	
	static void order(int chase) {
		if (chase == K) {
			String[] tempArr = new String[K];
				
			for (int j = 0; j < K; j++) {
				tempArr[j] = order[orderIndex[j]];
			}
				
			orderArr[orderChase] = tempArr;
			orderChase++;
			
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if (orderCheck[i] == true) {
				continue;
			}
			
			orderCheck[i] = true;
			orderIndex[chase] = i;
			order(chase+1);
			orderCheck[i] = false;
		}
	}
	
	static void rotate(int[][] arr, int[] XY) {
		int firstOut = 0;
		
		for (int i = XY[2]; i > XY[0]; i--) {
			if (i == XY[2]) {
				firstOut = arr[XY[1]][i];
			} 
			
			arr[XY[1]][i] = arr[XY[1]][i-1];
		}
		
		int secondOut = 0;
		
		for (int i = XY[3]; i > XY[1]; i--) {
			if (i == XY[3]) {
				secondOut = arr[i][XY[2]];
			} 
			
			arr[i][XY[2]] = arr[i - 1][XY[2]];
		}
		
		arr[XY[1] + 1][XY[2]] = firstOut;
		
		int thirdOut = 0;
		
		for (int i = XY[0]; i < XY[2]; i++) {
			if (i == XY[0]) {
				thirdOut = arr[XY[3]][i];
			} 
			
			arr[XY[3]][i] = arr[XY[3]][i+1];
		}
		
		arr[XY[3]][XY[2] - 1] = secondOut;
		
		int fourthOut = 0;
		
		for (int i = XY[1]; i < XY[3]; i++) {
			if (i == XY[1]) {
				fourthOut = arr[i][XY[0]];
			} 
			
			arr[i][XY[0]] = arr[i + 1][XY[0]];
		}
		
		arr[XY[3] - 1][XY[0]] = thirdOut;
		
		arr[XY[1]][XY[0] + 1] = fourthOut;
		
		XY[0]++;
		XY[1]++;
		XY[2]--;
		XY[3]--;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		order = new String[K]; 
		for (int i = 0; i < K; i++) {
			order[i] = br.readLine();
		}
		
		orderLimit = 1;
		for (int i = 1; i <= K; i++) {
			orderLimit *= i;
		}
		
		orderArr = new String[orderLimit][K];
		orderIndex = new int[K];
		orderCheck = new boolean[K];
		
		order(0);
		
		int min = 99999999;
		
		for (int i = 0; i < orderLimit; i++) {
			String[] getOrder = orderArr[i];
			
			int[][] tempArr = new int[N][M];
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					tempArr[j][k] = arr[j][k];
				}
			}
			
			for (int j = 0; j < K; j++) {
				
				
				String[] realOrder = getOrder[j].split(" ");
				
				int Y = Integer.parseInt(realOrder[0]) - 1;
				int X = Integer.parseInt(realOrder[1]) - 1;
				int gab = Integer.parseInt(realOrder[2]);
				
				int[] XY = {X - gab, Y - gab, X + gab, Y + gab}; //startX, startY, endX, endY
				
				while (XY[0] != XY[2]) {
					rotate(tempArr, XY);
				}
			}
			
			int sum = 0;
			
			for (int j = 0; j < N; j++) {
				sum = 0;
				
				for (int k = 0; k < M; k++) {
					sum += tempArr[j][k];
				}
				
				if (sum < min) {
					min = sum;
				}
			}
		}
		
		System.out.println(min);
	}
}