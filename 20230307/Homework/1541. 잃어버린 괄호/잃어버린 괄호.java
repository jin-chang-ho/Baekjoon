import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String op = br.readLine();
		
		List<Integer> operand = new ArrayList<>();
		
		String[] minusArr = op.split("-");
		
		for (int i = 0; i < minusArr.length; i++) {
			if (minusArr[i].contains("+")) {
				String[] plusArr = minusArr[i].split("\\+");
				
				for (int j = 0; j < plusArr.length; j++) {
					while (plusArr[j].charAt(0) == '0') {
						plusArr[j] = plusArr[j].substring(1);
					}
					
					operand.add(Integer.parseInt(plusArr[j]));
				}
			} else {
				while (minusArr[i].charAt(0) == '0') {
					minusArr[i] = minusArr[i].substring(1);
				}
				
				operand.add(Integer.parseInt(minusArr[i]));
			}
		}
		
		List<Character> operator = new ArrayList<>();
		
		for (int i = 0; i < op.length(); i++) {
			if (op.charAt(i) == '+' || op.charAt(i) == '-') {
				operator.add(op.charAt(i));
			}
		}
		
		int answer = operand.get(0);
		
		boolean minus = false;
		for (int i = 0; i < operator.size(); i++) {
			if (operator.get(i) == '-') {
				minus = true;
			}
			
			if (minus) {
				answer -= operand.get(i + 1);
			} else {
				answer += operand.get(i + 1);
			}
		}
		
		System.out.println(answer);
	}
}