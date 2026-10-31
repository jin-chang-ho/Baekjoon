// 목표 : 각 캐시 경우마다 실행시간이 얼마나 걸리는지 반환해야 한다.

import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        // 1. 캐시를 담당할 map 에 key 를 city 로, value 를 가장 최근 사용 시간으로 저장한다.
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < cities.length; i++) {
            
            // 2. city 는 대문자로 통일한다.
            String city = "";
            
            StringBuilder sb = new StringBuilder();
            for (char c : cities[i].toCharArray()) {
                if ('a' <= c && c <= 'z') {
                    sb.append((char) (c - 'a' + 'A'));
                } else {
                    sb.append(c);
                }
            }
                    
            city = sb.toString();
            
            //System.out.println(city);
            //System.out.println(map.containsKey(city));
            
            // 3. 캐시 hit 일 때는 1을 더하고 가장 최근 시간을 갱신한 뒤 넘어간다.
            if (map.containsKey(city)) {
                answer += 1;
                map.put(city, i);
            } else { // 4. 캐시 miss 일 때는 map 이 비어있는 건지 아니면 교체를 해야하는 지 결정한다.
                answer += 5;
                
                if (cacheSize == 0) {
                    continue;
                }
                
                if (map.size() < cacheSize) { // map이 비어있는 경우
                    map.put(city, i);
                } else if (map.size() == cacheSize) { // 교체를 해야하는 경우, LRU 알고리즘
                    // 가장 나중에 쓰인 거 결정
                    String leastKey = "";
                    int leastTime = 200000;
                    
                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        String key = entry.getKey();
                        int value = entry.getValue();
                        
                        if (value < leastTime) {
                            leastKey = key;
                            leastTime = value;
                        }
                    }
                    
                    map.remove(leastKey);
                    map.put(city, i);
                }
            }
        }
        
        return answer;
    }
}