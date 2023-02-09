import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		String arr = st.nextToken();
		
		int[] checkArr = new int[4];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 4; i++) {
			checkArr[i] = Integer.parseInt(st.nextToken());
		}
		
		String startArr = arr.substring(0, P);
		
		for (int i = 0; i < P; i++) {
			if (startArr.charAt(i) == 'A') {
				checkArr[0]--;
			} else if (startArr.charAt(i) == 'C') {
				checkArr[1]--;
			} else if (startArr.charAt(i) == 'G') {
				checkArr[2]--;
			} else if (startArr.charAt(i) == 'T') {
				checkArr[3]--;
			}
		}
		
		int count = 1;
		
		for (int j = 0; j < 4; j++) {
			if (checkArr[j] > 0) {
				count = 0;
				break;
			}
		}
		
		for (int i = 0; i < S - P; i++) {
			boolean check = true;
			
			if (arr.charAt(i) == 'A') {
				checkArr[0]++;
			} else if (arr.charAt(i) == 'C') {
				checkArr[1]++;
			} else if (arr.charAt(i) == 'G') {
				checkArr[2]++;
			} else if (arr.charAt(i) == 'T') {
				checkArr[3]++;
			}
			
			if (arr.charAt(i + P) == 'A') {
				checkArr[0]--;
			} else if (arr.charAt(i + P) == 'C') {
				checkArr[1]--;
			} else if (arr.charAt(i + P) == 'G') {
				checkArr[2]--;
			} else if (arr.charAt(i + P) == 'T') {
				checkArr[3]--;
			} 
			
			for (int j = 0; j < 4; j++) {
				if (checkArr[j] > 0) {
					check = false;
					break;
				}
			}
			
			if (check) {
				count++;
			}
		}
		
		System.out.println(count);
	}
}