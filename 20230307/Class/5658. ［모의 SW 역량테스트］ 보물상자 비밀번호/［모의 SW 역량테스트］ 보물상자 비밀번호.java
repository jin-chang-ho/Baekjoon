import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			char[] line = br.readLine().toCharArray();
			
			Set<String> set = new HashSet<>();
			int multi = N / 4;
			
			for (int t = 0; t < multi; t++) {
				for (int i = 0; i < N; i += multi) {
					String temp = new String();
					
					for (int j = i; j < i + multi; j++) {
						temp += Character.toString(line[j]);
					}
					
					set.add(temp);
				}
				
				char first = ' ';
				
				for (int i = N - 1; i > 0; i--) {
					if (i == N - 1) {
						first = line[i];
					}
					
					line[i] = line[i - 1];
				}
				
				line[0] = first;
			}
			
			String[] stringArr = new String[set.size()];
			int chase = 0;
			
			for (String string : set) {
				stringArr[chase] = string;
				chase++;
			}
			
			Arrays.sort(stringArr, Collections.reverseOrder());
			
			String answerArr = stringArr[K-1];
			int answer = 0;
			
			for (int i = multi - 1; i >= 0; i--) {
				int number = -1;
				
				if (Character.isDigit(answerArr.charAt(i))) {
					number = answerArr.charAt(i) - '0';
				} else {
					number = answerArr.charAt(i) - 'A' + 10;					
				}
				
				answer += number * (int)Math.pow(16, multi - i - 1);
			}
			
			sb.append("#" + tc + " " + answer + "\n");
		}
		
		System.out.println(sb.toString());
	}
}