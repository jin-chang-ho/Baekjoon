import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			char[][] arr = new char[H][W];
			
			int tankX = 0;
			int tankY = 0;
			
			for (int i = 0; i < H; i++) {
				String line = br.readLine();
				
				for (int j = 0; j < W; j++) {
					arr[i][j] = line.charAt(j);
					
					if (arr[i][j] == '>' || arr[i][j] == '<' || arr[i][j] == 'v' || arr[i][j] == '^') {
						tankX = j;
						tankY = i;
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			
			String order = br.readLine();
			for (int i = 0; i < N; i++) {
				if (order.charAt(i) == 'U') {
					arr[tankY][tankX] = '^';
					
					if (tankY - 1 >= 0 && arr[tankY - 1][tankX] == '.') {
						arr[tankY][tankX] = '.';
						arr[tankY - 1][tankX] = '^';
						tankY = tankY - 1;
					}
					
				} else if (order.charAt(i) == 'D') {
					arr[tankY][tankX] = 'v';
					
					if (tankY + 1 < H && arr[tankY + 1][tankX] == '.') {
						arr[tankY][tankX] = '.';
						arr[tankY + 1][tankX] = 'v';
						tankY = tankY + 1;
					}
					
				} else if (order.charAt(i) == 'L') {
					arr[tankY][tankX] = '<';
					
					if (tankX - 1 >= 0 && arr[tankY][tankX - 1] == '.') {
						arr[tankY][tankX] = '.';
						arr[tankY][tankX - 1] = '<';
						tankX = tankX - 1;
					}
					
				} else if (order.charAt(i) == 'R') {
					arr[tankY][tankX] = '>';
					
					if (tankX + 1 < W && arr[tankY][tankX + 1] == '.') {
						arr[tankY][tankX] = '.';
						arr[tankY][tankX + 1] = '>';
						tankX = tankX + 1;
					}
					
				} else {
					if (arr[tankY][tankX] == '^') {
						for (int j = tankY - 1; j >= 0; j--) {
							if (arr[j][tankX] == '*') {
								arr[j][tankX] = '.';
								break;
							} else if (arr[j][tankX] == '#') {
								break;
							}
						}
						
					} else if (arr[tankY][tankX] == 'v') {
						for (int j = tankY + 1; j < H; j++) {
							if (arr[j][tankX] == '*') {
								arr[j][tankX] = '.';
								break;
							} else if (arr[j][tankX] == '#') {
								break;
							}
						}
					} else if (arr[tankY][tankX] == '<') {
						for (int j = tankX - 1; j >= 0; j--) {
							if (arr[tankY][j] == '*') { 
								arr[tankY][j] = '.';
								break;
							} else if (arr[tankY][j] == '#') {
								break;
							}
							
						}
					} else {
						for (int j = tankX + 1; j < W; j++) {
							if (arr[tankY][j] == '*') { 
								arr[tankY][j] = '.';
								break;
							} else if (arr[tankY][j] == '#') {
								break;
							}
						}
					}
				}
			}
			
			sb.append("#" + tc + " ");
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(arr[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}