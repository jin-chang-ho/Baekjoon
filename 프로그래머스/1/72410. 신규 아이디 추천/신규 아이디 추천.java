class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        StringBuilder sb = new StringBuilder();
        
        for (char c : new_id.toCharArray()) {
            if ('A' <= c && c <= 'Z') {
                sb.append((char)(c - 'A' + 'a'));   
            } else {
                sb.append(c);
            }
        }
        
        String first = sb.toString();
        sb.setLength(0);
        
        //System.out.println(first);
        
        for (char c : first.toCharArray()) {
            if ('a' <= c && c <= 'z') {
                sb.append(c);
            } else if ('0' <= c && c <= '9') {
                sb.append(c);
            } else if ((c == '-') || (c == '_') || c == '.') {
                sb.append(c);
            }
        }
        
        String second = sb.toString();
        sb.setLength(0);
        
        //System.out.println(second);
        
        boolean check = false;
        
        for (char c : second.toCharArray()) {
            if (c == '.') {
                check = true;
            } else {
                if (check) {
                    sb.append('.');  
                }
                
                check = false;
                sb.append(c);
            }
        }
        
        if (check) {
            sb.append('.');
        }
        
        String third = sb.toString();
        sb.setLength(0);
        
        //System.out.println(third);
        
        for (int i = 0; i < third.length(); i++) {
            if (i == 0 || i == third.length() - 1) {
                if (third.charAt(i) == '.') {
                    continue;
                }
            }
            
            sb.append(third.charAt(i));
        }
        
        String fourth = sb.toString();
        sb.setLength(0);
        
        //System.out.println(fourth);
        
        String fifth = fourth;
        
        if (fifth.isEmpty()) {
            fifth = "a";
        }
        
        //System.out.println(fifth);
        
        for (int i = 0; i < fifth.length(); i++) {
            if (i == 14 && fifth.charAt(i) == '.') {
                break;
            } else if (i == 15) {
                break;
            }
            
            sb.append(fifth.charAt(i));
        }
        
        String sixth = sb.toString();
        sb.setLength(0);
        
        //System.out.println(sixth);
        
        String seventh = sixth;
        
        while (seventh.length() <= 2) {
            seventh += sixth.charAt(sixth.length() - 1);
        }
        
        //System.out.println(seventh);
        
        answer = seventh;
        
        return answer;
    }
}