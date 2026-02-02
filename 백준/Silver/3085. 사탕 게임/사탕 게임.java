import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[][] arr = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
		
		// 상, 하, 좌, 우
		int[] dx = new int[] {0, 0, -1, 1};
		int[] dy = new int[] {-1, 1, 0, 0};
		
		boolean[][] check = new boolean[N][N];
		
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++) {
					int cx = j + dx[k];
					int cy = i + dy[k];
					
					if (0 <= cx && cx < N && 0 <= cy && cy < N && check[cy][cx] == false && arr[i][j] != arr[cy][cx]) {
						char[][] tempArr = new char[N][N];
						
						for (int l = 0; l < N; l++) {
							for (int m = 0; m < N; m++) {
								tempArr[l][m] = arr[l][m];
							}
						}
						
						char temp = tempArr[i][j];
						tempArr[i][j] = tempArr[cy][cx];
						tempArr[cy][cx] = temp;
						
						for (int l = 0; l < N; l++) { 
							char down = tempArr[0][l];
							int count = 0;
							
							for (int m = 0; m < N; m++) {
								if (down != tempArr[m][l]) {
									down = tempArr[m][l];
									answer = Math.max(answer, count);
									count = 0;
								}
								
								count++;
							}
							
							answer = Math.max(answer, count);
						}
						
						for (int l = 0; l < N; l++) { 
							char right = tempArr[l][0];
							int count = 0;
							
							for (int m = 0; m < N; m++) {
								if (right != tempArr[l][m]) {
									right = tempArr[l][m];
									answer = Math.max(answer, count);
									count = 0;
								}
								
								count++;
							}
							
							answer = Math.max(answer, count);
						}
					}
				}
				
				check[i][j] = true;
			}
		}
		
		System.out.println(answer);
	}
}