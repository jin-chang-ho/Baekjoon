import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String P = br.readLine();
		
		int[] LPS = new int[P.length()];
		
		int base = 0;
		int index = 1;
		
		while (index < P.length()) {
			if (P.charAt(base) == P.charAt(index)) {
				base++;
				LPS[index] = base;
				index++;
			} else {
				if (base != 0) {
					base = LPS[base - 1];
				} else {
					index++;
				}
			}
		}
		
		int i = 0;
		int j = 0;
		int answer = 0;
		
		while (i < S.length()) {
			if (S.charAt(i) == P.charAt(j)) {
				i++;
				j++;
				if (j == P.length()) {
					answer = 1;
					break;
				}
			} else {
				if (j != 0) {
					j = LPS[j-1];
				} else {
					i++;
				}
			}
		}
		
		System.out.println(answer);
	}
}