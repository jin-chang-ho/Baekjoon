import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution
{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			
			int[] chaseFirst = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				chaseFirst[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] chaseSecond = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				chaseSecond[i] = Integer.parseInt(st.nextToken());
			}
			
			int[][] chargerInfo = new int[A][5]; // x, y, 충전 범위, 충전량, 충전기 번호
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < 5; j++) {
					if (j == 4) {
						chargerInfo[i][j] = i;
						break;
					}
					
					chargerInfo[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			PriorityQueue<int[]> pqA = new PriorityQueue<>(new Comparator<int[]> () { // 충전량, 충전기 번호
				
				@Override
				public int compare(int[] arr1, int[] arr2) { // 충전량에 내림차순
					return -(arr1[0] - arr2[0]);
				}
			});
			
			PriorityQueue<int[]> pqB = new PriorityQueue<>(new Comparator<int[]> () { // 충전량, 충전기 번호
				
				@Override
				public int compare(int[] arr1, int[] arr2) { // 충전량에 내림차순
					return -(arr1[0] - arr2[0]);
				}
			});
			
			int[] xyA = {1, 1};
			int[] xyB = {10, 10};
			
			int count = 0;
			
			for (int i = 0; i < M; i++) { // 이동 시간 check
				// 첫번째 사용자가 들어있는 충전기 확인
				for (int j = 0; j < A; j++) {
					if (Math.abs(xyA[0] - chargerInfo[j][0]) + Math.abs(xyA[1] - chargerInfo[j][1]) <= chargerInfo[j][2]) {
						pqA.offer(new int[] {chargerInfo[j][3], chargerInfo[j][4]});
					}
				}
				
				// 두번째 사용자가 들어있는 충전기 확인
				for (int j = 0; j < A; j++) {
					if (Math.abs(xyB[0] - chargerInfo[j][0]) + Math.abs(xyB[1] - chargerInfo[j][1]) <= chargerInfo[j][2]) {
						pqB.offer(new int[] {chargerInfo[j][3], chargerInfo[j][4]});
					}
				}
				
				// 첫번째 사용자만 충전기에 들어간 경우
				if (!pqA.isEmpty() && pqB.isEmpty()) {
					count += pqA.poll()[0];
				}
				
				// 두번째 사용자만 충전기에 들어간 경우
				if (pqA.isEmpty() && !pqB.isEmpty()) {
					count += pqB.poll()[0];
				}
				
				// 두 사용자가 충전기에 들어간 경우
				if (!pqA.isEmpty() && !pqB.isEmpty()) {
					int[] infoA = pqA.poll();
					int[] infoB = pqB.poll();
					
					if (infoA[1] != infoB[1]) { // 두 사용자가 다른 충전기에 들어간 경우
						count += infoA[0];
						count += infoB[0];
					} else { // 두 사용자가 같은 충전기에 들어간 경우
						if (pqA.isEmpty() && pqB.isEmpty()) { // 두 사용자 모두 그 충전기에만 있을 경우
							count += infoA[0];
						} else if (!pqA.isEmpty() && pqB.isEmpty()) { // A 사용자만 2개 이상 충전기에 있을 경우
							int[] nextA = pqA.poll();
							count += infoB[0];
							count += nextA[0];
						} else if (pqA.isEmpty() && !pqB.isEmpty()) { // B 사용자만 2개 이상 충전기에 있을 경우
							int[] nextB = pqB.poll();
							count += infoA[0];
							count += nextB[0];
						} else { // A, B 사용자 둘 다 2개 이상 충전기에 있을 경우
							int[] nextA = pqA.poll();
							int[] nextB = pqB.poll();
							
							if (nextA[0] > nextB[0]) { // 그 다음 A 충전기가 그 다음 B 충전기보다 더 클 경우
								count += infoB[0];
								count += nextA[0];
							} else { // 그 다음 B 충전기가 그 다음 A 충전기보다 더 클 경우
								count += infoA[0];
								count += nextB[0];
							}
						}
					}
				}

				// 첫번째 사용자 움직이기
				if (chaseFirst[i] == 1) {
					xyA[1] -= 1;
				} else if (chaseFirst[i] == 2) {
					xyA[0] += 1;
				} else if (chaseFirst[i] == 3) {
					xyA[1] += 1;
				} else if (chaseFirst[i] == 4) {
					xyA[0] -= 1;
				}
				
				pqA.clear();
				
				// 두번째 사용자 움직이기
				if (chaseSecond[i] == 1) {
					xyB[1] -= 1;
				} else if (chaseSecond[i] == 2) {
					xyB[0] += 1;
				} else if (chaseSecond[i] == 3) {
					xyB[1] += 1;
				} else if (chaseSecond[i] == 4) {
					xyB[0] -= 1;
				}
				
				pqB.clear();
			}
			
			// 마지막 이동지 check
			// 첫번째 사용자가 들어있는 충전기 확인
			for (int j = 0; j < A; j++) {
				if (Math.abs(xyA[0] - chargerInfo[j][0]) + Math.abs(xyA[1] - chargerInfo[j][1]) <= chargerInfo[j][2]) {
					pqA.offer(new int[] {chargerInfo[j][3], chargerInfo[j][4]});
				}
			}
			
			// 두번째 사용자가 들어있는 충전기 확인
			for (int j = 0; j < A; j++) {
				if (Math.abs(xyB[0] - chargerInfo[j][0]) + Math.abs(xyB[1] - chargerInfo[j][1]) <= chargerInfo[j][2]) {
					pqB.offer(new int[] {chargerInfo[j][3], chargerInfo[j][4]});
				}
			}
			
			// 첫번째 사용자만 충전기에 들어간 경우
			if (!pqA.isEmpty() && pqB.isEmpty()) {
				count += pqA.poll()[0];
			}
			
			// 두번째 사용자만 충전기에 들어간 경우
			if (pqA.isEmpty() && !pqB.isEmpty()) {
				count += pqB.poll()[0];
			}
			
			// 두 사용자가 충전기에 들어간 경우
			if (!pqA.isEmpty() && !pqB.isEmpty()) {
				int[] infoA = pqA.poll();
				int[] infoB = pqB.poll();
				
				if (infoA[1] != infoB[1]) { // 두 사용자가 다른 충전기에 들어간 경우
					count += infoA[0];
					count += infoB[0];
				} else { // 두 사용자가 같은 충전기에 들어간 경우
					if (pqA.isEmpty() && pqB.isEmpty()) { // 두 사용자 모두 그 충전기에만 있을 경우
						count += infoA[0];
					} else if (!pqA.isEmpty() && pqB.isEmpty()) { // A 사용자만 2개 이상 충전기에 있을 경우
						int[] nextA = pqA.poll();
						count += infoB[0];
						count += nextA[0];
					} else if (pqA.isEmpty() && !pqB.isEmpty()) { // B 사용자만 2개 이상 충전기에 있을 경우
						int[] nextB = pqB.poll();
						count += infoA[0];
						count += nextB[0];
					} else { // A, B 사용자 둘 다 2개 이상 충전기에 있을 경우
						int[] nextA = pqA.poll();
						int[] nextB = pqB.poll();
						
						if (nextA[0] > nextB[0]) { // 그 다음 A 충전기가 그 다음 B 충전기보다 더 클 경우
							count += infoB[0];
							count += nextA[0];
						} else { // 그 다음 B 충전기가 그 다음 A 충전기보다 더 클 경우
							count += infoA[0];
							count += nextB[0];
						}
					}
				}
			}
			
			sb.append("#" + tc + " " + count + "\n");
		}
		System.out.println(sb.toString());
	}
}