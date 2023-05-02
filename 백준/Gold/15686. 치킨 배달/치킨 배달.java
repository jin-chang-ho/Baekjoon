import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int M;
	static List<int[]> house;
	static List<int[]> sell;
	static int chaseArr[];
	static int answer = Integer.MAX_VALUE;
	
	static void dfs(int start, int chase) {
		if (chase == M) {
			int checkAnswer = 0;
			int minTempSize = 10000;
			
			for (int i = 0; i < house.size(); i++) {
				int[] houseOutput = house.get(i);
				
				for (int j = 0; j < M; j++) {
					int[] sellOutput = sell.get(chaseArr[j]);
					
					if ((Math.abs(houseOutput[0] - sellOutput[0]) + Math.abs(houseOutput[1] - sellOutput[1])) < minTempSize) {
						minTempSize = (Math.abs(houseOutput[0] - sellOutput[0]) + Math.abs(houseOutput[1] - sellOutput[1]));
					}
				}
				
				checkAnswer += minTempSize;
				minTempSize = 10000;
			}
			
			answer = Math.min(answer, checkAnswer);
			
			return;
		}
		
		for (int i = start; i < sell.size(); i++) {
			chaseArr[chase] = i;
			dfs(i + 1, chase + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		house = new ArrayList<>(); // x, y
		sell = new ArrayList<>(); // x, y
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				
				if (temp == 1) {
					house.add(new int[] {j, i});
				} else if (temp == 2) {
					sell.add(new int[] {j, i});
				}
			}
		}
		
		chaseArr = new int[M];
		
		dfs(0, 0);
		
		System.out.println(answer);
	}
}