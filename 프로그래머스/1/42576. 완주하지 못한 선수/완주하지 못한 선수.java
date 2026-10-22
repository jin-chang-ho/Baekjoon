import java.util.*;
import java.io.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> map = new HashMap<>();
        
        for (String par : participant) {
            map.put(par, map.getOrDefault(par, 0) + 1);
        }
        
        for (String com : completion) {
            map.put(com, map.get(com) - 1);
            
            if (map.get(com) == 0) {
                map.remove(com);
            }
        }
        
        for (String k : map.keySet()) {
            answer = k;
        }
        
        return answer;
    }
}