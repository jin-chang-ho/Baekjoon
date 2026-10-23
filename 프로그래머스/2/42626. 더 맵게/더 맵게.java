import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = -1;
        
        PriorityQueue<Long> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Long i1, Long i2) {
                return Long.compare(i1, i2);
            }
        });
        
        for (int sco : scoville) {
            pq.offer((long)sco);
        }
        
        int count = 0;
        while (pq.size() >= 2) {
            long first = pq.poll();
            
            if (first >= K) {
                answer = count;
                break;
            }
            
            count++;
            
            long second = pq.poll();
            pq.offer(first + (second * 2));
        }
        
        if (count != 0 && pq.size() == 1) {
            long last = pq.poll();
            if (last >= K) {
                answer = count;
            }
        }
        
        return answer;
    }
}