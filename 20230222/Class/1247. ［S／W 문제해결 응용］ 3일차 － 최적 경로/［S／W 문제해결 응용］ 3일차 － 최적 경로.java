/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int N;
	static int[] start;
	static int[] end;
	static int[][] client;
	static int minDistance = 10000;
	
	static int[][] chaseArr;
	static boolean[] check;
	
	static void dfs(int chase) {
		if (chase == N) {
			int sumDistance = 0;
			
			sumDistance += Math.abs(start[0] - chaseArr[0][0]);
			sumDistance += Math.abs(start[1] - chaseArr[0][1]);
			
			for (int i = 0; i < N - 1; i++) {
				sumDistance += Math.abs(chaseArr[i][0] - chaseArr[i+1][0]);
				sumDistance += Math.abs(chaseArr[i][1] - chaseArr[i+1][1]);
			}
			
			sumDistance += Math.abs(chaseArr[N-1][0] - end[0]);
			sumDistance += Math.abs(chaseArr[N-1][1] - end[1]);
			
			if (minDistance > sumDistance) {
				minDistance = sumDistance; 
			}
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (check[i] == true) {
				continue;
			}
			
			chaseArr[chase][0] = client[i][0];
			chaseArr[chase][1] = client[i][1];
			check[i] = true;
			dfs(chase + 1);
			check[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			start = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			end = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			client = new int[N][2];
			for (int i = 0; i < N; i++) {
				client[i][0] = Integer.parseInt(st.nextToken());
				client[i][1] = Integer.parseInt(st.nextToken());
			}
			
			chaseArr = new int[N][2];
			check = new boolean[N];
			dfs(0);
			sb.append("#" + tc + " " + minDistance + "\n");
			
			minDistance = 10000;
		}
		
		System.out.println(sb.toString());
	}
}