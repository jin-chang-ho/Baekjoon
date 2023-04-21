import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
      
        int[][] arr = new int[N][N];
      
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]> () { // x, y, value를 저장할 예정
            @Override
            public int compare(int[] arr1, int[] arr2) { 
                return -(arr1[2] - arr2[2]);
            }
        });
      
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                pq.offer(new int[] {j, i, arr[i][j]});
            }
        }
        
        int[][] check = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                check[i][j] = 0;
            }
        }
        
        // 상, 하, 좌, 우
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        
        while (!pq.isEmpty()) {
            int[] output = pq.poll();
            check[output[1]][output[0]] = 1;
            
            for (int i = 0; i < 4; i++) {
                int cx = output[0] + dx[i];
                int cy = output[1] + dy[i];
                
                if (0 <= cx && cx < N && 0 <= cy && cy < N && arr[output[1]][output[0]] < arr[cy][cx]) {
                    check[output[1]][output[0]] = Math.max(check[output[1]][output[0]], check[cy][cx] + 1);
                }
            }
            
        }
        
        int answer = 1;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, check[i][j]);
            }
        }
        
        System.out.println(answer);
    }
}