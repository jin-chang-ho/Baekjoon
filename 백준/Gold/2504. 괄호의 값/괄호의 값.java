import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String string = br.readLine();
		
		int answer = 0;
		Stack<Character> charStack = new Stack<>();
		Stack<Integer> intStack = new Stack<>();
		
		for (char c : string.toCharArray()) {
			if (c != '(' && c != ')' && c != '[' && c != ']') {
				System.out.println(0);
				return;
			}
			
			if (c == ')') {
				if (charStack.isEmpty()) {
					System.out.println(0);
					return;
				}
				
				if (charStack.peek() == '(') {
					intStack.push(2);
					charStack.pop();
					charStack.push('v');
				} else {
					int tempVal = 0;
					
					while (charStack.peek() != '(') {
						if (charStack.isEmpty() || charStack.peek() == '[') {
							System.out.println(0);
							return;
						}
						
						charStack.pop();
						tempVal += intStack.pop();
					}
					
					intStack.push(tempVal * 2);
					charStack.pop();
					charStack.push('v');
				}
			} else if (c == ']') {
				if (charStack.isEmpty()) {
					System.out.println(0);
					return;
				}
				
				if (charStack.peek() == '[') {
					intStack.push(3);
					charStack.pop();
					charStack.push('v');
				} else {
					int tempVal = 0;
					
					while (charStack.peek() != '[') {
						if (charStack.isEmpty() || charStack.peek() == '(') {
							System.out.println(0);
							return;
						}
						
						charStack.pop();
						tempVal += intStack.pop();
					}
					
					intStack.push(tempVal * 3);
					charStack.pop();
					charStack.push('v');
				}
			} else {
				charStack.push(c);
			}
			
			if (charStack.size() == 1 && charStack.peek() == 'v') {
				charStack.pop();
				answer += intStack.pop();
			}
		}
		
		if (!charStack.isEmpty()) {
			System.out.println(0);
		} else {
			System.out.println(answer);
		}
	}
}