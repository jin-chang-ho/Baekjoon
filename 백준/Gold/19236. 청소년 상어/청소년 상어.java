import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Map.*;
 
/* Name of the class has to be "Main" only if the class is public. */
public class Main
{
   static int maxValue = -1;
 
    // 상어의 방향
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
 
   static class Fish {
      int number;
      int direction;
 
      public Fish(int number, int direction) {
         this.number = number;
         this.direction = direction;
      }
 
      @Override
      public String toString() {
         return number + " " + direction;
      }
   }
 
   static void fishMove(int sharkX, int sharkY, Fish[][] arr, int key, Map<Integer, int[]> map) {
      for (int i = 0; i < 8; i++) {
         int[] value = map.get(key);
 
         int fishX = value[0] + dx[arr[value[1]][value[0]].direction];
         int fishY = value[1] + dy[arr[value[1]][value[0]].direction];
 
         if ((fishX == sharkX && fishY == sharkY) || fishX < 0 || fishX > 3 || fishY < 0 || fishY > 3) {
            arr[value[1]][value[0]].direction = arr[value[1]][value[0]].direction % 8 + 1;
            continue;
         }
 
         map.put(key, new int[] {fishX, fishY});
 
         if (arr[fishY][fishX] != null) {
            map.put(arr[fishY][fishX].number, new int[] {value[0], value[1]});
         }
 
         Fish temp = new Fish(arr[value[1]][value[0]].number, arr[value[1]][value[0]].direction);
 
         if (arr[fishY][fishX] == null) {
            arr[value[1]][value[0]] = null;
         } else {
            arr[value[1]][value[0]] = new Fish(arr[fishY][fishX].number, arr[fishY][fishX].direction);
         }
 
         arr[fishY][fishX] = new Fish(temp.number, temp.direction);
 
         break;
      }
   }
 
   static void dfs(int sharkX, int sharkY, Fish[][] arr, Map<Integer, int[]> map, int value) {
      value += arr[sharkY][sharkX].number;
      map.remove(arr[sharkY][sharkX].number);
      
      int direction = arr[sharkY][sharkX].direction;
 
      Fish[][] tempArr = new Fish[4][4];
 
      for (int i = 0; i < 4; i++) {
         for (int j = 0; j < 4; j++) {
            if (arr[i][j] == null) {
               tempArr[i][j] = null;
            } else {
               Fish fish = new Fish(arr[i][j].number, arr[i][j].direction);
               tempArr[i][j] = fish;
            }
         }
      }
 
      tempArr[sharkY][sharkX] = null;
 
      for (int key : map.keySet()) {
         fishMove(sharkX, sharkY, tempArr, key, map);
      }
 
      int count = 1;
 
      while (true) {
         int changeSharkX = sharkX + dx[direction] * count;
         int changeSharkY = sharkY + dy[direction] * count;
 
         if (changeSharkX < 0 || changeSharkX > 3 || changeSharkY < 0 || changeSharkY > 3) {
            break;
         }
 
         count++;
 
         if (tempArr[changeSharkY][changeSharkX] == null) {
            continue;
         }
 
         Map<Integer, int[]> tempMap = new TreeMap<>();
 
         for (Entry<Integer, int[]> entry : map.entrySet()) {
            tempMap.put(entry.getKey(), entry.getValue());
         }
        
         dfs(changeSharkX, changeSharkY, tempArr, tempMap, value);
      }
      
      maxValue = Math.max(maxValue, value);
   }
 
   public static void main (String[] args) throws java.lang.Exception
   {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
      Fish[][] arr = new Fish[4][4];
      Map<Integer, int[]> map = new TreeMap<>();
 
      for (int i = 0; i < 4; i++) {
         StringTokenizer st = new StringTokenizer(br.readLine());
 
         for (int j = 0; j < 4; j++) {
            int number = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
 
            arr[i][j] = new Fish(number, direction);
            map.put(number, new int[] {j, i});
         }
      }
 
      dfs(0, 0, arr, map, 0);
      
      System.out.println(maxValue);
   }
}