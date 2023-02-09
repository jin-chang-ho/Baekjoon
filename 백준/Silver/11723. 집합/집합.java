import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.BitSet;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		BitSet bitSet = new BitSet(20);
		
		
		int N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			String order = st.nextToken();
			
			if (order.equals("all")) {
				bitSet = new BitSet(20);
				bitSet.flip(0, 20);
			} else if (order.equals("empty")) {
				bitSet.clear();
			} else {
				int number = Integer.parseInt(st.nextToken()) - 1;
				
				if (order.equals("add")) {
					bitSet.set(number);
				} else if (order.equals("remove")) {
					bitSet.clear(number);
				} else if (order.equals("check")) {
					if (bitSet.get(number)) {
						sb.append(1 + "\n");
					} else {
						sb.append(0 + "\n");
					}
				} else {
					bitSet.flip(number);
				}
			}
		}
		System.out.println(sb.toString());
	}
}