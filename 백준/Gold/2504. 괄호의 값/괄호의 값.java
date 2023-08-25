import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
	static int Prefix = 1000;
	
	static class Data {
		int index;
		int ch;
		
		public Data(int index, int ch) {
			super();
			this.index = index;
			this.ch = ch;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		List<Integer> list = new ArrayList<>();
		
		for (int c : str.toCharArray()) {
			if (c != '(' && c != '[' && c != ')' && c != ']') {
				System.out.println(0);
				return;
			}
			
			list.add(c);
		}
			
		int openc = -1;
		int closec = -1;
		int opens = -1;
		int closes = -1;
		
		Stack<Data> stack = new Stack<>();
		
		int index = 0;
		int listSize = list.size();
		
		while (index < listSize) {
			int number = list.get(index);
			
			if (number == '(') {
				openc = index;
				stack.add(new Data(index, '('));
			} else if (number == '[') {
				opens = index;
				stack.add(new Data(index, '['));
			} else if (number == ')') {
				closec = index;
				
				if (stack.isEmpty() || stack.peek().ch != '(') {
					System.out.println(0);
					return;
				}
				
				stack.pop();
				
				if (closec - openc == 1) {
					list.remove(openc);
					list.remove(openc);
					list.add(openc, 2 + Prefix);
					
					if (stack.isEmpty()) {
						openc = -1;
					} else {
						if (stack.peek().ch == '(') {
							openc = stack.peek().index;
						} else {
							opens = stack.peek().index;
						}
					}
					
					index = index - 1;
					listSize = listSize - 1;
				} else if (closec - openc == 2) {
					list.remove(openc);
					int targetNumber = list.get(openc) - Prefix;
					list.remove(openc);
					list.remove(openc);
					list.add(openc, targetNumber * 2 + Prefix);
					
					if (stack.isEmpty()) {
						openc = -1;
					} else {
						if (stack.peek().ch == '(') {
							openc = stack.peek().index;
						} else {
							opens = stack.peek().index;
						}
					}
					
					index = index - 2;
					listSize = listSize - 2;
				} else {
					int gap = closec - openc;
					
					list.remove(openc);
					
					int value = 0;
					while (list.get(openc) != ')') {
						value += (list.get(openc) - Prefix);
						list.remove(openc);
					}
					
					list.remove(openc);
					list.add(openc, value * 2 + Prefix);
					
					if (stack.isEmpty()) {
						openc = -1;
					} else {
						if (stack.peek().ch == '(') {
							openc = stack.peek().index;
						} else {
							opens = stack.peek().index;
						}
					}
					
					index = index - gap;
					listSize = listSize - gap;
				}
			} else if (number == ']') {
				closes = index;
				
				if (stack.isEmpty() || stack.peek().ch != '[') {
					System.out.println(0);
					return;
				}
				
				stack.pop();
				
				if (closes - opens == 1) {
					list.remove(opens);
					list.remove(opens);
					list.add(opens, 3 + Prefix);
					
					if (stack.isEmpty()) {
						opens = -1;
					} else {
						if (stack.peek().ch == '(') {
							openc = stack.peek().index;
						} else {
							opens = stack.peek().index;
						}
					}
					
					index = index - 1;
					listSize = listSize - 1;
				} else if (closes - opens == 2) {
					list.remove(opens);
					int targetNumber = list.get(opens) - Prefix;
					list.remove(opens);
					list.remove(opens);
					list.add(opens, targetNumber * 3 + Prefix);
					
					if (stack.isEmpty()) {
						opens = -1;
					} else {
						if (stack.peek().ch == '(') {
							openc = stack.peek().index;
						} else {
							opens = stack.peek().index;
						}
					}
					
					index = index - 2;
					listSize = listSize - 2;
				} else {
					int gap = closes - opens;
					
					list.remove(opens);
					
					int value = 0;
					while (list.get(opens) != ']') {
						value += (list.get(opens) - Prefix);
						list.remove(opens);
					}
					
					list.remove(opens);
					list.add(opens, value * 3 + Prefix);
					
					if (stack.isEmpty()) {
						opens = -1;
					} else {
						if (stack.peek().ch == '(') {
							openc = stack.peek().index;
						} else {
							opens = stack.peek().index;
						}
					}
					
					index = index - gap;
					listSize = listSize - gap;
				}
			}
			
//			System.out.println(index);
//			System.out.println(list);
			index++;
		}
		
		int answer = 0;
		
		for (int number : list) {
			answer += (number - Prefix);
		}
		
		if (stack.isEmpty()) {
			System.out.println(answer);
		} else {
			System.out.println(0);
		}
	}
}
