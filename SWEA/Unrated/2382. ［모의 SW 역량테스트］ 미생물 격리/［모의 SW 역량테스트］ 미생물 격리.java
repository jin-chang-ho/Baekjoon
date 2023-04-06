import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
	
	static class Data {
		int y;
		int x;
		int number;
		int direction;
		
		public Data(int y, int x, int number, int direction) {
			super();
			this.y = y;
			this.x = x;
			this.number = number;
			this.direction = direction;
		}

		@Override
		public String toString() {
			return "Data [y=" + y + ", x=" + x + ", number=" + number + ", direction=" + direction + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			List<Data> list = new ArrayList<>();
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int number = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
			
				list.add(new Data(y, x, number, direction));
			}
			
			for (int i = 0; i < M; i++) {
				List<Data> tempList = new ArrayList<>();
				
				for (int j = 0; j < list.size(); j++) {
					Data output = list.get(j);
					
					int y = output.y;
					int x = output.x;
					int number = output.number;
					int direction = output.direction;
					
					if (direction == 1) {
						y = y - 1;
						
						if (y == 0) {
							number = number / 2;
							direction = 2;
						}
					} else if (direction == 2) {
						y = y + 1;
						
						if (y == N - 1) {
							number = number / 2;
							direction = 1;
						}
					} else if (direction == 3) {
						x = x - 1;
						
						if (x == 0) {
							number = number / 2;
							direction = 4;
						}
					} else {
						x = x + 1;
						
						if (x == N - 1) {
							number = number / 2;
							direction = 3;
						}
					}
					
					if (number == 0) {
						continue;
					}
					
					tempList.add(new Data(y, x, number, direction));
				}
				
				list.clear();
				
				Collections.sort(tempList, new Comparator<Data>() {
					@Override
					public int compare(Data data1, Data data2) {
						if (data1.y == data2.y) {
							return data1.x - data2.x;
						}
						return data1.y - data2.y;
					}
				});
				
				int tSize = tempList.size();
				int j = 0;
				
				while (j < tSize) {
					boolean check = true;
					
					if (j == tSize - 1) {
						list.add(tempList.get(j));
						break;
					}
					
					if ((tempList.get(j).x == tempList.get(j+1).x) && (tempList.get(j).y == tempList.get(j+1).y)) {
						List<Data> equalList = new ArrayList<>();
						
						int sumNumber = 0;
						while (j < tSize - 1 && (tempList.get(j).x == tempList.get(j+1).x) && (tempList.get(j).y == tempList.get(j+1).y)) {
							equalList.add(tempList.get(j));
							sumNumber += tempList.get(j).number;
							j++;
						}
						
						equalList.add(tempList.get(j));
						sumNumber += tempList.get(j).number;
						
						Collections.sort(equalList, new Comparator<Data>() {
							@Override
							public int compare(Data data1, Data data2) {
								return -(data1.number - data2.number);
							}
						});
						
						list.add(new Data(tempList.get(j).y, tempList.get(j).x, sumNumber, equalList.get(0).direction));
						
						check = false;
					}
					
					if (j == tSize - 1) {
						break;
					}
					
					if (check) {
						list.add(tempList.get(j));
					}
					
					j++;
				}
			}
			
			int sum = 0;
			for (int i = 0; i < list.size(); i++) {
				sum += list.get(i).number;
			}
			
			sb.append("#" + tc + " " + sum + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
