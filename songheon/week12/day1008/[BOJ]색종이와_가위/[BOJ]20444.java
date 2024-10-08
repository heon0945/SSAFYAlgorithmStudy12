import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static long k;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextLong();
		
		long left = 0;
		long right = n/2;
		
		while(left <= right) {
			long h = (left + right) / 2;
			long v = n - h;
			
			long cnt = (h + 1) * (v + 1);
			
			if(cnt == k) {
				System.out.println("YES");
				System.exit(0);
			}
			else if(cnt > k) {
				right = h - 1;
			}
			else if(cnt < k) {
				left = h + 1;
			}
		}
		System.out.println("NO");
		
	}
}