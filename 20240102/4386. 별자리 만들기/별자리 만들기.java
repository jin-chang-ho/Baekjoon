import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		double[] x = new double[n];
		double[] y = new double[n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			x[i] = Double.parseDouble(st.nextToken());
			y[i] = Double.parseDouble(st.nextToken());
		}
		
		boolean[] check = new boolean[n];
		
		double[] distance = new double[n];
		Arrays.fill(distance, Double.MAX_VALUE);
		distance[0] = 0D;
		
		PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<>() {
			@Override
			public int compare(double[] d1, double[] d2) {
				if (d1[1] < d2[1]) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		pq.add(new double[] {0D, 0D});
		
		while (!pq.isEmpty()) {
			double[] output = pq.poll();
			
			if (check[(int) output[0]]) {
				continue;
			}
			
			check[(int) output[0]] = true;
			
			boolean bre = true;
			
			for (boolean c : check) {
				if (!c) {
					bre = false;
					break;
				}
			}
			
			if (bre) {
				break;
			}
			
			for (int i = 0; i < n; i++) {
				if ((int) output[0] == i) {
					continue;
				}
				
				if (!check[i] && Math.sqrt(Math.pow(x[i] - x[(int) output[0]], 2) + Math.pow(y[i] - y[(int) output[0]], 2)) < distance[i]) {
					distance[i] = Math.sqrt(Math.pow(x[i] - x[(int) output[0]], 2) + Math.pow(y[i] - y[(int) output[0]], 2));
					pq.add(new double[] {i, distance[i]});
				}
			}
		}
		
		double answer = 0D;
		
		for (double d : distance) {
			answer += d;
		}
		
		System.out.println(String.format("%.2f", answer));
	}
}
