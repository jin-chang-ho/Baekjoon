import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int G;

    static void init() {
        for (int i = 1; i <= G; i++) {
            arr[i] = i;
        }
    }

    static boolean union(int x, int y) {
        int xv = find(x);
        int yv = find(y);

        if (xv == yv) {
            return false;
        }

        arr[x] = yv;
        return true;
    }

    static int find(int x) {
        if (x == arr[x]) {
            return x;
        }

        return arr[x] = find(arr[x]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        arr = new int[G+1];

        init();

        int P = Integer.parseInt(br.readLine());
        int answer = 0;

        for (int i = 0; i < P; i++) {
            int g = Integer.parseInt(br.readLine());
            int val = find(g);

            if (val == 0) {
                break;
            }

            union(val, val-1);
            answer++;
        }

        System.out.println(answer);
    }
}
