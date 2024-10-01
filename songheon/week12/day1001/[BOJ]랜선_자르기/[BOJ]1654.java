import java.util.*;
import java.io.*;

public class Main {
	
	static int k, n;
	static long len[];
	static long answer = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		k = sc.nextInt();
		n = sc.nextInt();
		len = new long[k];
		
		long tmp = 0;
		for(int i = 0; i < k; i++) {
			len[i] = sc.nextLong();
			tmp += len[i];
		}
		
		long low = 1;
		long high = tmp/n;
		
		while(low <= high) {
			long cnt = 0;
			long mid = (low+high)/2; 
			for(int i = 0; i < k; i++) {
				cnt += (len[i] / mid);
			}
			
			if(cnt >= n) {
				low = mid + 1;
				answer = Math.max(answer, mid);
			}
			else {
				high = mid - 1;
			}
		}
		
		System.out.println(answer);
		
	}
}