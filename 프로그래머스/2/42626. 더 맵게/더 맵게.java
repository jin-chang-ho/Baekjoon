import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = -1;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1 - i2;
            }
        });
        
        for (int sco : scoville) {
            pq.offer(sco);
        }
        
        int count = 0;
        while (pq.size() >= 2) {
            int first = pq.poll();
            
            if (first >= K) {
                answer = count;
                break;
            }
            
            count++;
            
            int second = pq.poll();
            pq.offer(first + (second * 2));
        }
        
        if (count != 0 && pq.size() == 1) {
            int last = pq.poll();
            if (last >= K) {
                answer = count;
            }
        }
        
        return answer;
    }
}