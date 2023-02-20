import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] arr;
	
	static void first() {
		for (int i = N / 2; i < N; i++) {
			int[] temp = new int[M];
			
			for (int j = 0; j < M; j++) {
				temp[j] = arr[i][j];
			}
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = arr[N - 1 - i][j];
			}
			
			for (int j = 0; j < M; j++) {
				arr[N - 1 - i][j] = temp[j];
			}
		}
	}
	
	static void second() {
		for (int i = M / 2; i < M; i++) {
			int[] temp = new int[N];
			
			for (int j = 0; j < N; j++) {
				temp[j] = arr[j][i];
			}
			
			for (int j = 0; j < N; j++) {
				arr[j][i] = arr[j][M - 1 - i];
			}
			
			for (int j = 0; j < N; j++) {
				arr[j][M - 1 - i] = temp[j];
			}
		}
	}
	
	static void third() {
		int[][] tempArr = new int[M][N];
		
		for (int i = 0; i < N; i++) {
			int temp[] = new int[M];
			
			for (int j = 0; j < M; j++) {
				temp[j] = arr[N - 1 - i][j];
			}
			
			for (int j = 0; j < M; j++) {
				tempArr[j][i] = temp[j];
			}
		}
		
		int temp = N;
		N = M;
		M = temp;
		
		arr = tempArr;
	}
	
	static void fourth() {
		int[][] tempArr = new int[M][N];
		
		for (int i = 0; i < N; i++) {
			int temp[] = new int[M];
			
			for (int j = 0; j < M; j++) {
				temp[j] = arr[i][M - 1 - j];
			}
			
			for (int j = 0; j < M; j++) {
				tempArr[j][i] = temp[j];
			}
		}
		
		int temp = N;
		N = M;
		M = temp;
		
		arr = tempArr;
	}
	
	static void five() {
		int[][] first = new int[N/2][M/2];
		int[][] second = new int[N/2][M/2];
		int[][] third = new int[N/2][M/2];
		int[][] fourth = new int[N/2][M/2];
		
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				first[i][j] = arr[i][j];
				second[i][j] = arr[i][M / 2 + j];
				third[i][j] = arr[N / 2 + i][M / 2 + j];
				fourth[i][j] = arr[N / 2 + i][j];
			}
		}
		
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				arr[i][j] = fourth[i][j];
				arr[i][M / 2 + j] = first[i][j];
				arr[N / 2 + i][M / 2 + j] = second[i][j];
				arr[N / 2 + i][j] = third[i][j];
			}
		}
	}
	
	static void six() {
		int[][] first = new int[N/2][M/2];
		int[][] second = new int[N/2][M/2];
		int[][] third = new int[N/2][M/2];
		int[][] fourth = new int[N/2][M/2];
		
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				first[i][j] = arr[i][j];
				second[i][j] = arr[i][M / 2 + j];
				third[i][j] = arr[N / 2 + i][M / 2 + j];
				fourth[i][j] = arr[N / 2 + i][j];
			}
		}
		
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				arr[i][j] = second[i][j];
				arr[i][M / 2 + j] = third[i][j];
				arr[N / 2 + i][M / 2 + j] = fourth[i][j];
				arr[N / 2 + i][j] = first[i][j];
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int operator[] = new int[R];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int oper : operator) {
			if (oper == 1) {
				first();
			}
			
			if (oper == 2) {
				second();
			}
			
			if (oper == 3) {
				third();
			}
			
			if (oper == 4) {
				fourth();
			}
			
			if (oper == 5) {
				five();
			}
			
			if (oper == 6) {
				six();
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}