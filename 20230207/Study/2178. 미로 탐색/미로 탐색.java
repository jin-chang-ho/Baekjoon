import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int[] arr;
	
	Node(int[] arr) {
		this.arr = arr;
	}
	
	@Override
	public int compareTo(Node n) {
		return this.arr[0] - n.arr[0];
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String line = st.nextToken();
			for (int j = 0; j < M; j++) {
				arr[i][j] = Character.getNumericValue(line.charAt(j));
			}
		}
		
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		
		arr[0][0] = 0;
		
		int[] start = {1, 0, 0};
		PQ.add(new Node(start));
		
		while (!PQ.isEmpty()) {
			Node pollNode = PQ.poll();
			
			int price = pollNode.arr[0];
			int x = pollNode.arr[1];
			int y = pollNode.arr[2];
			
			if (x == M - 1 && y == N - 1) {
				System.out.println(price);
				break;
			}
			
			// 좌, 우, 상, 하
			int[] cx = {-1, 1, 0, 0};
			int[] cy = {0, 0, -1, 1};
			
			for (int i = 0; i < 4; i++) {
				int dx = x + cx[i];
				int dy = y + cy[i];
				
				if (0 <= dy && dy < N && 0 <= dx && dx < M && arr[dy][dx] == 1) {
					arr[dy][dx] = 0;
					int[] tempArr = {price+1, dx, dy};
					PQ.add(new Node(tempArr));
				}
			}
		}
	}
}