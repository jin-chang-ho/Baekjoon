import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int n1;
	int n2;
	
	public Node(int n1, int n2) {
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public int compareTo(Node n) {
		if (this.n2 == n.n2) {
			return this.n1 - n.n1;
		}
		
		return this.n2 - n.n2;
	}
}

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] arr = new Node[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Node node = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			arr[i] = node;
		}
		
		Arrays.sort(arr);
		
		Stack<Node> stack = new Stack<>();
		stack.push(arr[0]);
		
		for (int i = 1; i < N; i++) {
			if (arr[i].n1 < stack.peek().n2) {
				continue;
			}
			
			stack.push(arr[i]);
		}
		
		System.out.println(stack.size());
	}
}