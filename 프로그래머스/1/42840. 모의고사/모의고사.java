class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        int[] first = new int[] {1, 2, 3, 4, 5};
        int[] second = new int[] {2, 1, 2, 3, 2, 4, 2, 5};
        int[] third = new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int[] score = new int[] {0, 0, 0};
        
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == first[i % 5]) {
                score[0]++;
            } 
            
            if (answers[i] == second[i % 8]) {
                score[1]++;
            }
            
            if (answers[i] == third[i % 10]) {
                score[2]++;
            }
        }
        
        int maxScore = Math.max(Math.max(score[0], score[1]), score[2]);
        int maxCount = 0;
        
        for (int i = 0; i < 3; i++) {
            if (maxScore == score[i]) {
                maxCount++;
            }
        }
        
        answer = new int[maxCount];
        int answerIndex = 0;
        
        for (int i = 0; i < 3; i++) {
            if (maxScore == score[i]) {
                answer[answerIndex++] = i + 1;
            }
        }
        
        return answer;
    }
}