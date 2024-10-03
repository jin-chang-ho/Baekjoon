import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[][] arr = new boolean[N+1][N+1];
        int[] weight = new int[N+1];
        int[] tp = new int[N+1];
        
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int nodeWeight = Integer.parseInt(st.nextToken());
            int tpNumber = Integer.parseInt(st.nextToken());

            for (int j = 0; j < tpNumber; j++) {
                int start = Integer.parseInt(st.nextToken());
                arr[i][start] = true;
            }

            weight[i] = nodeWeight;
            tp[i] = tpNumber;
        }

        int[] dp = new int[N+1];
        
        for (int i = 1; i <= N; i++) {
			dp[i] = weight[i];
		}
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 1; i <= N; i++) {
            if (tp[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            PriorityQueue<Integer> tempPQ = new PriorityQueue<>();

            while (!pq.isEmpty()) {
                tempPQ.offer(pq.poll());
            }

            while (!tempPQ.isEmpty()) {
                int tempIndex = tempPQ.poll();

                for (int i = 1; i <= N; i++) {
					if (arr[i][tempIndex] == true) {
						tp[i]--;
						
						if (tp[i] == 0) {
                            pq.offer(i);
                            
							for (int j = 1; j <= N; j++) {
								if (arr[i][j] == true) {
									dp[i] = Math.max(dp[i], dp[j] + weight[i]);
								}
							}
						}
					}
				}
            }
        }

        int answer = 0;

        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}