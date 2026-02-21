import java.util.*;

class Solution {
    int answer = 0;
    int[] numbersSol;
    int targetSol;
    
    public void dfs(int index, int number) {
        if (index == numbersSol.length) {
            if (number == targetSol) {
                answer++;
            }
            
            return;
        }
        
        dfs(index+1, number - numbersSol[index]);
        dfs(index+1, number + numbersSol[index]);
    }
    
    public int solution(int[] numbers, int target) {
        numbersSol = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            numbersSol[i] = numbers[i];
        }
        
        targetSol = target;
        
        dfs(0, 0);
        
        return answer;
    }
}