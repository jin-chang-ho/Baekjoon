import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> arrStack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arrStack.push(Integer.parseInt(st.nextToken()));
		}
		
		int[] answer = new int[N];
		Stack<int[]> chaseStack = new Stack<>();
		while (!arrStack.isEmpty()) {
			int first = arrStack.pop();
			int second = arrStack.size();
			
			int[] chaseArr = {first, second};
			chaseStack.push(chaseArr);
			
			if (!arrStack.isEmpty() && arrStack.peek() > chaseStack.peek()[0]) {
				while (!chaseStack.isEmpty() && arrStack.peek() > chaseStack.peek()[0]) {
					int[] tempArr = chaseStack.pop();
					answer[tempArr[1]] = second;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			sb.append(answer[i] + " ");
		}
		System.out.println(sb.toString());
	}
}