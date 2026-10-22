class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        String first = "";
        for (char c : new_id.toCharArray()) {
            if ('A' <= c && c <= 'Z') {
                first += (char)(c - 'A' + 'a');
            } else {
                first += c;
            }
        }
        
        //System.out.println(first);
        
        String second = "";
        for (char c : first.toCharArray()) {
            if ('a' <= c && c <= 'z') {
                second += c;
            } else if ('0' <= c && c <= '9') {
                second += c;
            } else if (c == '-' || c == '_' || c == '.') {
                second += c;
            }
        }
        
        //System.out.println(second);
        
        String third = "";
        boolean check = false;
        
        for (char c : second.toCharArray()) {
            if (c == '.') {
                if (check) {
                    continue;
                }
                
                check = true;
            } else {
                if (check) {
                    third += '.';
                    check = false;
                } 
                
                third += c;
            }
        }
        
        if (check == true) {
            third += '.';
        }
        
        //System.out.println(third);
        
        String fourth = "";
        
        for (int i = 0; i < third.length(); i++) {
            if (i == 0) {
              if (third.charAt(i) != '.') {
                  fourth += third.charAt(i);
              }  
            } else if (i == third.length() - 1) {
              if (third.charAt(i) != '.') {
                  fourth += third.charAt(i);
              }
            } else {
                fourth += third.charAt(i);
            }
        }
        
        //System.out.println(fourth);
        
        String fifth = fourth;
        
        if (fourth.equals("")) {
            fifth = "a";
        }
        
        //System.out.println(fifth);
        
        String sixth = "";
        
        for (int i = 0; i < fifth.length(); i++) {
            if (i == 14 && fifth.charAt(i) == '.') {
                break;
            } else if (i == 15) {
                break;
            }
            
            sixth += fifth.charAt(i);
        }
        
        //System.out.println(sixth);
        
        String seventh = sixth;
    
        while (seventh.length() < 3) {
            seventh += sixth.charAt(sixth.length() - 1);
        }
        
        //System.out.println(seventh);
        
        answer = seventh;
        
        return answer;
    }
}