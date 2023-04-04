import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int arr[][];

    static void dfs(int startX, int startY) {
        if (startX > 8 && startY >= 8) {
        	for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(arr[i][j]);
                }
                
                System.out.println();
            }

            System.exit(0);
        }

        if (startX > 8) {
            dfs(0, startY+1);
            return;
        }
        
        if (arr[startY][startX] == 0) {
            boolean[] check = new boolean[10];

            for (int i = 0; i < 9; i++) {
                check[arr[startY][i]] = true;
            }

            for (int i = 0; i < 9; i++) {
                check[arr[i][startX]] = true;
            }

            int checkY = (startY / 3) * 3;
            int checkX = (startX / 3) * 3;

            for (int i = checkY; i < checkY + 3; i++) {
                for (int j = checkX; j < checkX + 3; j++) {
                    check[arr[i][j]] = true;
                }
            }

            for (int i = 1; i <= 9; i++) {
                if (check[i] == false) {
                    arr[startY][startX] = i;
                    dfs(startX + 1, startY);
                    arr[startY][startX] = 0;
                }
            }
        } else {
            dfs(startX + 1, startY);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[9][9];

        for (int i = 0; i < 9; i++) {
            String line = br.readLine();

            for (int j = 0; j < 9; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        dfs(0, 0);
    }
}