import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			
			int zeroFirst = 1;
			int zeroSecond = 0;
			int zeroTemp = 0;
			
			if (N == 0) {
				sb.append(zeroFirst + " ");
			} else if (N == 1) {
				sb.append(zeroSecond + " ");
			} else {
				for (int j = 0; j <= N - 2; j++) {
					zeroTemp = zeroFirst + zeroSecond;
					zeroFirst = zeroSecond;
					zeroSecond = zeroTemp;
				}
				sb.append(zeroTemp + " ");
			}
			
			int oneFirst = 0;
			int oneSecond = 1;
			int oneTemp = 0;
			
			if (N == 0) {
				sb.append(oneFirst + "\n");
			} else if (N == 1) {
				sb.append(oneSecond + "\n");
			} else {
				for (int j = 0; j <= N - 2; j++) {
					oneTemp = oneFirst + oneSecond;
					oneFirst = oneSecond;
					oneSecond = oneTemp;
				}
				sb.append(oneTemp + "\n");
			}
		}
		
		System.out.print(sb.toString());
	}
}