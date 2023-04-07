import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] arr;
	static int cctvCount;
	static int[][] cctv;
	static int minCount = Integer.MAX_VALUE;
	
	static void dfs(int chase) {
		if (cctvCount == chase) {
			int count = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 0) {
						count++;
					}
				}
			}
			
			if (count < minCount) {
				minCount = count;
			}
			
			return;
		}
		
		int cctvX = cctv[chase][0];
		int cctvY = cctv[chase][1];
		
		if (arr[cctvY][cctvX] == 1) {
			for (int i = 0; i < 4; i++) {
				if (i == 0) { // 상
					for (int j = cctvY - 1; j >= 0; j--) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvY - 1; j >= 0; j--) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
				} else if (i == 1) { // 하
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
				} else if (i == 2) { // 좌
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
				} else { // 우
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
				}
			}
			
		} else if (arr[cctvY][cctvX] == 2) {
			for (int i = 0; i < 2; i++) {
				if (i == 0) { // 좌, 우
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
					
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
				} else { // 상, 하
					for (int j = cctvY - 1; j >= 0; j--) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvY - 1; j >= 0; j--) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
					
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
				}
			}
		} else if (arr[cctvY][cctvX] == 3) {
			for (int i = 0; i < 4; i++) {
				if (i == 0) { // 좌, 상
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					for (int j = cctvY - 1; j >= 0; j--) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
					
					for (int j = cctvY - 1; j >= 0; j--) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
				} else if (i == 1) { // 좌, 하
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
					
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
				} else if (i == 2) { // 우, 하
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
					
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
				} else { // 우, 상
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					for (int j = cctvY - 1; j >= 0; j--) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}

					
					dfs(chase + 1);
					
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
					
					for (int j = cctvY - 1; j >= 0; j--) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
				}
			}
		} else if (arr[cctvY][cctvX] == 4) {
			for (int i = 0; i < 4; i++) {
				if (i == 0) { // 상, 좌, 우
					for (int j = cctvY - 1; j >= 0; j--) { 
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvY - 1; j >= 0; j--) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
					
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
					
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
				} else if (i == 1) { // 상, 하, 좌
					for (int j = cctvY - 1; j >= 0; j--) { 
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvY - 1; j >= 0; j--) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
					
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
					
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
				} else if (i == 2) { // 상, 하, 우
					for (int j = cctvY - 1; j >= 0; j--) { 
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvY - 1; j >= 0; j--) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
					
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
					
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
				} else { // 하, 좌, 우
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] <= 0) {
							arr[j][cctvX] -= 1;
						}
					}
					
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] <= 0) {
							arr[cctvY][j] -= 1;
						}
					}
					
					dfs(chase + 1);
					
					for (int j = cctvY + 1; j < N; j++) {
						if (arr[j][cctvX] == 6) {
							break;
						}
						
						if (arr[j][cctvX] < 0) {
							arr[j][cctvX] += 1;
						}
					}
					
					for (int j = cctvX - 1; j >= 0; j--) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
					
					for (int j = cctvX + 1; j < M; j++) {
						if (arr[cctvY][j] == 6) {
							break;
						}
						
						if (arr[cctvY][j] < 0) {
							arr[cctvY][j] += 1;
						}
					}
				}
			}
		} else { // 상, 하, 좌, 우
			for (int j = cctvY - 1; j >= 0; j--) { 
				if (arr[j][cctvX] == 6) {
					break;
				}
				
				if (arr[j][cctvX] <= 0) {
					arr[j][cctvX] -= 1;
				}
			}
			
			for (int j = cctvY + 1; j < N; j++) {
				if (arr[j][cctvX] == 6) {
					break;
				}
				
				if (arr[j][cctvX] <= 0) {
					arr[j][cctvX] -= 1;
				}
			}
			
			for (int j = cctvX - 1; j >= 0; j--) {
				if (arr[cctvY][j] == 6) {
					break;
				}
				
				if (arr[cctvY][j] <= 0) {
					arr[cctvY][j] -= 1;
				}
			}
			
			for (int j = cctvX + 1; j < M; j++) {
				if (arr[cctvY][j] == 6) {
					break;
				}
				
				if (arr[cctvY][j] <= 0) {
					arr[cctvY][j] -= 1;
				}
			}
			
			dfs(chase + 1);
			
			for (int j = cctvY - 1; j >= 0; j--) {
				if (arr[j][cctvX] == 6) {
					break;
				}
				
				if (arr[j][cctvX] < 0) {
					arr[j][cctvX] += 1;
				}
			}
			
			for (int j = cctvY + 1; j < N; j++) {
				if (arr[j][cctvX] == 6) {
					break;
				}
				
				if (arr[j][cctvX] < 0) {
					arr[j][cctvX] += 1;
				}
			}
			
			for (int j = cctvX - 1; j >= 0; j--) {
				if (arr[cctvY][j] == 6) {
					break;
				}
				
				if (arr[cctvY][j] < 0) {
					arr[cctvY][j] += 1;
				}
			}
			
			for (int j = cctvX + 1; j < M; j++) {
				if (arr[cctvY][j] == 6) {
					break;
				}
				
				if (arr[cctvY][j] < 0) {
					arr[cctvY][j] += 1;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		cctvCount = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (1 <= arr[i][j] && arr[i][j] <= 5) {
					cctvCount++;
				}
			}
		}
		
		cctv = new int[cctvCount][2]; // x, y 저장
		int cctvChase = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (1 <= arr[i][j] && arr[i][j] <= 5) {
					cctv[cctvChase] = new int[] {j, i};
					cctvChase++;
				}
			}
		}
		
		dfs(0);
		
		if (minCount == Integer.MAX_VALUE) {
			minCount = 0;
		}
		
		System.out.println(minCount);
	}
}
