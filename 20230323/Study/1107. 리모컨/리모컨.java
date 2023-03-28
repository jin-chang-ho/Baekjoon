import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int targetNumber = Integer.parseInt(br.readLine());
		int errorCount = Integer.parseInt(br.readLine());
		
		int[] errorNumber = new int[0];
		
		if (errorCount != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			errorNumber = new int[errorCount];
			for (int i = 0; i < errorCount; i++) {
				errorNumber[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<Integer> count = new ArrayList<Integer>();
		
		// 가장 작게 근접
		int minValue = -1;
		
		for (int i = targetNumber; i >= 0; i--) {
			boolean check = false;
			int checkNumber = i;
			
			for (int error : errorNumber) {
				if (checkNumber == error) {
					check = true;
					break;
				}
			}
			
			if (check) {
				continue;
			}
			
			while (checkNumber != 0) {
				for (int error : errorNumber) {
					if (checkNumber % 10 == error) {
						check = true;
						break;
					}
				}
				
				if (check) {
					break;
				}
				
				checkNumber = checkNumber / 10;
			}
			
			if (check) {
				continue;
			}
			
			minValue = i;
			break;
		}
		
		if (minValue != -1) {
			int minCount = 0;
			
			if (minValue == 0) {
				minCount++;
			}
			
			int tempValue = minValue;
			
			while (tempValue != 0) {
				minCount++;
				
				tempValue = tempValue / 10;
			}
			
			minCount += (targetNumber - minValue);
			
			count.add(minCount);
		}
		
		// 가장 크게 근접
		int maxValue = 1000000;
		
		for (int i = targetNumber; i <= 999999; i++) {
			boolean check = false;
			int checkNumber = i;
			
			for (int error : errorNumber) {
				if (checkNumber == error) {
					check = true;
					break;
				}
			}
			
			if (check) {
				continue;
			}
			
			while (checkNumber != 0) {
				for (int error : errorNumber) {
					if (checkNumber % 10 == error) {
						check = true;
						break;
					}
				}
				
				if (check) {
					break;
				}
				
				checkNumber = checkNumber / 10;
			}
			
			if (check) {
				continue;
			}
			
			maxValue = i;
			break;
		}
		
		if (maxValue != 1000000) {
			int maxCount = 0;
			int tempValue = maxValue;
			
			if (maxValue == 0) {
				maxCount++;
			}
			
			while (tempValue != 0) {
				maxCount++;
				
				tempValue = tempValue / 10;
			}
			
			maxCount += (maxValue - targetNumber);
			
			count.add(maxCount);
		}
		
		// + 버튼
		if (targetNumber >= 100) {
			count.add(targetNumber - 100);
		}
		
		// - 버튼
		if (targetNumber < 100) {
			count.add(100 - targetNumber);
		}
		
		Collections.sort(count);
		
		System.out.println(count.get(0));
	}
}