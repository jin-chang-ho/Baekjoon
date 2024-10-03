import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        
        for (int i = 0; i < K; i++){
            answer += arr[i];
        }

        int chase = answer;

        for (int i = 0; i < N - K; i++) {
            chase -= arr[i];
            chase += arr[i + K];

            answer = Math.max(answer, chase);
        }

        System.out.println(answer);
    }
}