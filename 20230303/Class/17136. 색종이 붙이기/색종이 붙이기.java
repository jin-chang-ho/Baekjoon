import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int[] color;
	static int answer = Integer.MAX_VALUE;
	
	public static boolean canTake(int x, int y, int size) {
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				if (i >= 10 || j >= 10) {
					return false;
				}
				
				if (arr[i][j] != 1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void changeColor(int x, int y, int size, int option) {
		for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				arr[i][j] = option;
			}
		}
	}
	
	public static void dfs(int x, int y, int count) {	
		if (x >= 9 && y > 9) {		
			if (count < answer) {
				answer = count;
			}
			
			return;
		}
		
		if (y > 9) {
			dfs(x + 1, 0, count);
			
			return;
		}
		
		if (arr[y][x] == 1) {
			for (int i = 4; i >= 0; i--) {
				if (color[i] > 0 && canTake(x, y, i + 1)) {	// 색종이가 남아있고, 붙일 수 있다면
					changeColor(x, y, i + 1, 0);	// 색종이 붙임
					color[i]--;
					dfs(x, y + 1, count + 1);
					changeColor(x, y, i + 1, 1);	// 색종이 뗌
					color[i]++; 
				}
			}
		} else {	// 오른쪽으로 이동
			dfs(x, y + 1, count);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new int[10][10];
		
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		color = new int[] {5, 5, 5, 5, 5};
		
		dfs(0, 0, 0);
		
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(answer);
	}
}
