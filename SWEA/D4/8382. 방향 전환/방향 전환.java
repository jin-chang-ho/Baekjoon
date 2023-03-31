import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int sX = Integer.parseInt(st.nextToken());
            int sY = Integer.parseInt(st.nextToken());
            int dX = Integer.parseInt(st.nextToken());
            int dY = Integer.parseInt(st.nextToken());

            int cX = Math.abs(dX - sX);
            int cY = Math.abs(sY - dY);

            int answer = 0;
            int base = 0;

            if (cX < cY) {
            	if ((cY - cX) % 2 == 0) { 
                    base = 2 * (cY - cX);
                } else {
                    base = 2 * (cY - cX) - 1;
                }
            	
                answer = base + 2 * cX;
            } else if (cX > cY) {
                if ((cX - cY) % 2 == 0) { 
                    base = 2 * (cX - cY);
                } else {
                    base = 2 * (cX - cY) - 1;
                }

                answer = base + 2 * cY;
            } else {
                answer = 2 * cX;
            }

            sb.append("#" + tc + " " + answer + "\n");
        }
        
        System.out.println(sb.toString());
    }
}