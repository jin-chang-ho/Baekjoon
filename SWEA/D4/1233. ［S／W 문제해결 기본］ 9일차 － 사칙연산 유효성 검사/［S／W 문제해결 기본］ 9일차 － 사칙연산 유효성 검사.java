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

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int temp = N;
			int pow = 0;
			
			while ((temp / (int) Math.pow(2, pow)) != 0) {
				pow++;
			}
			
			pow = pow - 3;
			int answer = 1;
			
			String[] arr = new String[N+1];
			
			for (int i = 1; i <= N; i++) {
				arr[i] = br.readLine();
			}
			
			for (int i = 1; i < (int) Math.pow(2, pow); i++) {
				if (!arr[i].split(" ")[1].equals("+") && !arr[i].split(" ")[1].equals("-") && !arr[i].split(" ")[1].equals("*") && !arr[i].split(" ")[1].equals("/")) {
					answer = 0;
					break;
				}
			}
			
			int leaf_count = 0;
			for (int i = (int) Math.pow(2, pow + 2); i <= N; i++) {
				try {
					Integer.parseInt(arr[i].split(" ")[1]);
				} catch (Exception e) {
					answer = 0;
					break;
				}
				leaf_count++;
			}
			
			for (int i = (int) Math.pow(2, pow + 1); i < (int) Math.pow(2, pow + 1) + (int) Math.ceil(leaf_count / 2.0); i++) {
				if (!arr[i].split(" ")[1].equals("+") && !arr[i].split(" ")[1].equals("-") && !arr[i].split(" ")[1].equals("*") && !arr[i].split(" ")[1].equals("/")) {
					answer = 0;
					break;
				}
			}
			
			for (int i = (int) Math.pow(2, pow + 1) + (int) Math.ceil(leaf_count / 2.0); i < (int) Math.pow(2, pow + 2); i++) {
				try {
					Integer.parseInt(arr[i].split(" ")[1]);
				} catch (Exception e) {
					answer = 0;
					break;
				}
			}
			
			System.out.println("#" + tc + " " + answer);
		}
	}
}