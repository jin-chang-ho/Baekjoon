import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static long findResult(int num, int multi, long quao) {
		if (multi == 1) {
			return num % quao;
		}
		
		long temp = findResult(num, multi / 2, quao);
		
		if (multi % 2 == 1) {
			long result = (temp * temp % quao) * num % quao;
			return result;
		} else {
			return temp * temp % quao;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		long C = Integer.parseInt(st.nextToken());
		
		long result = findResult(A, B, C);
		
		System.out.println(result);
	}
}