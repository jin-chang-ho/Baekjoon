import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        
        // 차량정보와 시간을 저장할 Map
        Map<String, Integer> feeMap = new HashMap<>();
        
        // record를 추적할 Map
        Map<String, String> chaseMap = new HashMap<>();
        
        // record 순회
        for (String record : records) {
            String[] recordArr = record.split(" ");
            
            // System.out.println(Arrays.toString(recordArr));
            
            // IN 일때는 chaseMap 에만 넣기. 이미 있는 차량은 다시 입차되지 않음.
            if (recordArr[2].equals("IN")) {
                chaseMap.put(recordArr[1], recordArr[0]);
            } else { // OUT 일때는 chaseMap 에서 빼고. 시간 계산해서 feeMap 에 넣기.
                String outTime = recordArr[0];
                String inTime = chaseMap.get(recordArr[1]);
                
                chaseMap.remove(recordArr[1]);
                
                String[] outArr = outTime.split(":");
                String[] inArr = inTime.split(":");
                
                int getTime = (Integer.parseInt(outArr[0]) - Integer.parseInt(inArr[0])) * 60 + (Integer.parseInt(outArr[1]) - Integer.parseInt(inArr[1]));
                
                feeMap.put(recordArr[1], feeMap.getOrDefault(recordArr[1], 0) + getTime);
            }
            
        }
        
        for (Map.Entry<String, String> entry : chaseMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            
            String[] outArr = value.split(":");
            int getTime = (23 - Integer.parseInt(outArr[0])) * 60 + (59 - Integer.parseInt(outArr[1]));
            
            //System.out.println(key);
            //System.out.println(getTime);
            
            feeMap.put(key, feeMap.getOrDefault(key, 0) + getTime);
        }
        
        // PriorityQueue<> 로 차량 번호 오름차순 출력
        PriorityQueue<String[]> pq = new PriorityQueue<>(new Comparator<String[]>() {
            @Override
            public int compare(String[] s1, String[] s2) {
                return s1[0].compareTo(s2[0]);
            }
        });
        
        for (Map.Entry<String, Integer> entry : feeMap.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            
            int fee = fees[1];
            if (value > fees[0]) {
                int otherTime = value - fees[0];
                    
                if ((otherTime / (double) fees[2]) > (otherTime / fees[2])) {
                    fee += (otherTime / fees[2] + 1) * fees[3];
                } else {
                    fee += (otherTime / fees[2]) * fees[3];
                }
            }
            
            pq.offer(new String[] {key, Integer.toString(fee)});
        }
        
        answer = new int[pq.size()];
        
        int index = 0;
        while(!pq.isEmpty()) {
            String[] output = pq.poll();
            answer[index++] = Integer.parseInt(output[1]);
        }
        
        return answer;
    }
}