import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
    static final int MOD = 1_000_000_007;
    static int[] ansMatrix = {1,0,0,1,0,0,0,0};
    static long[][][] binaryMatrix = new long[32][8][8];
    static int[][] matrix = {
//           0 1 2 3 4 5 6 7
            {0,1,1,1,0,0,0,0},//0
            {1,0,0,1,0,0,0,0},//1
            {1,0,0,1,1,1,0,0},//2
            {1,1,1,0,0,1,0,0},//3
            {0,0,1,0,0,1,1,0},//4
            {0,0,1,1,1,0,0,1},//5
            {0,0,0,0,1,0,0,1},//6
            {0,0,0,0,0,1,1,0},//7
    };
    
    static void initMatrix() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                binaryMatrix[0][i][j] = matrix[i][j];
            }
        }
        
        for (int i = 1; i < 32; i++) {
            binaryMatrix[i] = mutiplyMatrix(binaryMatrix[i-1], binaryMatrix[i-1]);
        }
    }
    
    static long[][] mutiplyMatrix(long[][] a, long[][] b) {
        long[][] ans = new long[8][8];
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    ans[i][j] += a[i][k] * b[k][j];
                    ans[i][j] %= MOD;
                }
            }
        }
        
        return ans;
    }
    
    static int[] mutiplyMatrix(int[] a, long[][] b) {
        int[] ans = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                ans[i] += a[j] * b[i][j];
                ans[i] %= MOD;
            }
        }
        return ans;
    }
    
    static void solution(int count) {
        String s = Integer.toBinaryString(count);
        int size = s.length();
        long[][] toMultiply = new long[8][8];
        
        for (int i = 0; i < 8; i++) {
            toMultiply[i][i] = 1;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1'){
                toMultiply = mutiplyMatrix(toMultiply, binaryMatrix[size-i-1]);
            }
        }
        
        ansMatrix = mutiplyMatrix(ansMatrix, toMultiply);
        
        System.out.println(ansMatrix[1]);    
    }
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        initMatrix();

        solution(N-1);
    }
}