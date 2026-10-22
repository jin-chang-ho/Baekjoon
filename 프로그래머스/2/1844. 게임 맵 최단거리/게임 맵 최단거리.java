import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = -1;
        
        int Y = maps.length;
        int X = maps[0].length;
        
        // 상, 하, 좌, 우
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        
        // y, x
        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.offer(new int[]{0, 0, 1});
        maps[0][0] = 0;
        
        while (!queue.isEmpty()) {
            int[] output = queue.poll();
            
            if (output[0] == (Y - 1) && output[1] == (X - 1)) {
                answer = output[2];
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int cy = output[0] + dy[i];
                int cx = output[1] + dx[i];
                
                if (0 <= cy && cy < Y && 0 <= cx && cx < X && maps[cy][cx] != 0) {
                    maps[cy][cx] = 0;
                    queue.offer(new int[]{cy, cx, output[2] + 1});
                }
            }
        }
        
        
        return answer;
    }
}