import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int red = Integer.parseInt(st.nextToken());
			int green = Integer.parseInt(st.nextToken());
			int blue = Integer.parseInt(st.nextToken());
			
			arr[i][0] = red;
			arr[i][1] = green;
			arr[i][2] = blue;
		}
		
		// 0번 출발지에서 시작
		int[][] reddp = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				reddp[i][j] = 10000000;
			}
		} 

		reddp[0][0] = arr[0][0];
		
		for (int i = 1; i < N - 1; i++) {
			reddp[i][0] = Math.min(reddp[i-1][1] + arr[i][0], reddp[i-1][2] + arr[i][0]);
			reddp[i][1] = Math.min(reddp[i-1][0] + arr[i][1], reddp[i-1][2] + arr[i][1]);
			reddp[i][2] = Math.min(reddp[i-1][0] + arr[i][2], reddp[i-1][1] + arr[i][2]);
		}
		
		reddp[N-1][1] = Math.min(reddp[N-2][0] + arr[N-1][1], reddp[N-2][2] + arr[N-1][1]);
		reddp[N-1][2] = Math.min(reddp[N-2][0] + arr[N-1][2], reddp[N-2][1] + arr[N-1][2]);
		
		// 1번 출발지에서 시작
		int[][] greendp = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				greendp[i][j] = 10000000;
			}
		} 

		greendp[0][1] = arr[0][1];
		
		for (int i = 1; i < N - 1; i++) {
			greendp[i][0] = Math.min(greendp[i-1][1] + arr[i][0], greendp[i-1][2] + arr[i][0]);
			greendp[i][1] = Math.min(greendp[i-1][0] + arr[i][1], greendp[i-1][2] + arr[i][1]);
			greendp[i][2] = Math.min(greendp[i-1][0] + arr[i][2], greendp[i-1][1] + arr[i][2]);
		}
		
		greendp[N-1][0] = Math.min(greendp[N-2][1] + arr[N-1][0], greendp[N-2][2] + arr[N-1][0]);
		greendp[N-1][2] = Math.min(greendp[N-2][0] + arr[N-1][2], greendp[N-2][1] + arr[N-1][2]);
		
		// 2번 출발지에서 시작
		int[][] bluedp = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				bluedp[i][j] = 10000000;
			}
		} 

		bluedp[0][2] = arr[0][2];
		
		for (int i = 1; i < N - 1; i++) {
			bluedp[i][0] = Math.min(bluedp[i-1][1] + arr[i][0], bluedp[i-1][2] + arr[i][0]);
			bluedp[i][1] = Math.min(bluedp[i-1][0] + arr[i][1], bluedp[i-1][2] + arr[i][1]);
			bluedp[i][2] = Math.min(bluedp[i-1][0] + arr[i][2], bluedp[i-1][1] + arr[i][2]);
		}
		
		bluedp[N-1][0] = Math.min(bluedp[N-2][1] + arr[N-1][0], bluedp[N-2][2] + arr[N-1][0]);
		bluedp[N-1][1] = Math.min(bluedp[N-2][0] + arr[N-1][1], bluedp[N-2][2] + arr[N-1][1]);
		
		int answer = Math.min(reddp[N-1][1], reddp[N-1][2]);
		answer = Math.min(answer, greendp[N-1][0]);
		answer = Math.min(answer, greendp[N-1][2]);
		answer = Math.min(answer, bluedp[N-1][0]);
		answer = Math.min(answer, bluedp[N-1][1]);
		
		System.out.println(answer);
	}
}