import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Point>[] listArr;
	static int[] item;
	static int n;
	static int m;
	static boolean[] check;
	static int maxScore;
	
	static class Point {
		int to;
		int weight;
		
		public Point(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
	
	static void dfs(int chase, int weight) {
		if(weight > m) {
            return;
        }
		
		check[chase] = true;
		
		for (Point point : listArr[chase]) {
			dfs(point.to, weight + point.weight);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		item = new int[n+1];
		for (int i = 1; i <= n; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		
		listArr = new ArrayList[n+1];
		
		for (int i = 1; i <= n; i++) {
			listArr[i] = new ArrayList<Point>();
		}
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			listArr[first].add(new Point(second, weight));
			listArr[second].add(new Point(first, weight));
		}
		
		maxScore = 0;
		
		for (int i = 1; i <= n; i++) {
			check = new boolean[n+1];
			dfs(i, 0);
				
			int sum = 0;
				
			for (int j = 1; j <= n; j++) {
				if (check[j]) {
					sum += item[j];
				}
			}
				
			if (sum > maxScore) {
				maxScore = sum;
			}
		}
		
		System.out.println(maxScore);
	}
}