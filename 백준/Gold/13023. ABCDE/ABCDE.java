import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] listArr;
	static boolean[] check;
	static int answer;
	
	static void dfs(int start, int size) {
		if (size == 5) {
			answer = 1;
			
			return;
		}
		
		check[start] = true;
		
		for (int value : listArr[start]) {
			if (check[value] == false) {
				dfs(value, size + 1);
			}
		}
		
		check[start] = false;
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		listArr = new ArrayList[N];
 		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			if (listArr[first] == null) {
				listArr[first] = new ArrayList<Integer>();
			} 	
			listArr[first].add(second);
			
			if (listArr[second] == null) {
				listArr[second] = new ArrayList<Integer>();
			} 	
			listArr[second].add(first);
		}
		
		check = new boolean[N];
		
		for (int i = 0; i < N; i++) {	
			if (listArr[i] != null) {
				dfs(i, 1);
				
				if (answer == 1) {
					System.out.println(answer);
					return;
				}
			}
		}
		
		System.out.println(answer);
	}
}