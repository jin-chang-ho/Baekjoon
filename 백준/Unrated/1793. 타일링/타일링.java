import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
         
        BigInteger[] DP = new BigInteger[250 + 1];
         
        DP[0] = new BigInteger("1"); 
        DP[1] = new BigInteger("1");
        DP[2] = new BigInteger("3");
        
        for(int i = 3; i <= 250; i++) {
            DP[i] = DP[i-2].multiply(new BigInteger("2"));
            DP[i] = DP[i].add(DP[i-1]);
        }
         
        while (sc.hasNextInt()) {
           int n = Integer.parseInt(sc.next());
           System.out.println(DP[n]);
        }
    }
}