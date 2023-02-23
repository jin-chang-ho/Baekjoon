import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		String boom = br.readLine();
		
		for (int i = 0; i < str.length(); i++) {
			sb.append(str.charAt(i));
			
			if (sb.length() >= boom.length()) {
				boolean check = true;
				
				for (int j = 0; j < boom.length(); j++) {
					if (sb.charAt(sb.length() - boom.length() + j) != boom.charAt(j)) {
						check = false;
						break;
					}
				}
				
				if (check) {
					sb.delete(sb.length() - boom.length(), sb.length());
				}
			}
		}
		
		if (sb.toString().isEmpty()) {
			System.out.println("FRULA");
		}
		
		System.out.println(sb.toString());	
	}
}