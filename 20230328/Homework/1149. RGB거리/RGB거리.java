import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] color = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			color[i][0] = Integer.parseInt(st.nextToken());
			color[i][1] = Integer.parseInt(st.nextToken());
			color[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int[][] distance = new int[N][3];
		distance[0][0] = color[0][0];
		distance[0][1] = color[0][1];
		distance[0][2] = color[0][2];
		
		for (int i = 1; i < N; i++) {
			distance[i][0] = Math.min(color[i][0] + distance[i-1][1], color[i][0] + distance[i-1][2]);
			distance[i][1] = Math.min(color[i][1] + distance[i-1][0], color[i][1] + distance[i-1][2]);
			distance[i][2] = Math.min(color[i][2] + distance[i-1][0], color[i][2] + distance[i-1][1]);
		}
		
		int[] answerLine = distance[N-1];
		Arrays.sort(answerLine);
		
		System.out.println(answerLine[0]);
	}
}