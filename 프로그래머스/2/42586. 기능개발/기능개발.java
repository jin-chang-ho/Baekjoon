import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answerList = new ArrayList<>();
        
        int pointer = 0;
        
        for (int day = 1; day <= 100; day++) {
            if (pointer == progresses.length) {
                break;
            }
            
            for (int i = pointer; i < progresses.length; i++) {
                if (progresses[i] >= 100) {
                    continue;
                }
                
                progresses[i] += speeds[i];
            }
            
            if (progresses[pointer] >= 100) {
                int tempAnswer = 0;
                
                while (pointer < progresses.length && progresses[pointer] >= 100) {
                    tempAnswer++;
                    pointer++;
                }
                
                answerList.add(tempAnswer);
            }
        }
        
        int[] answer = new int[answerList.size()];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}