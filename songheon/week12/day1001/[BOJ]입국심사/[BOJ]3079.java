import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static long time[];
	static long answer;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		time = new long[n];
		for(int i = 0; i< n; i++) {
			time[i] = sc.nextInt();
		}
		Arrays.sort(time);
		
		
		long low = time[0];
		long high = time[n-1] * m;
		//System.out.println(high);
		answer = high;
		
		while(low <= high) {
			long mid = (low+high)/2;
			
			long cnt = 0;
			for(int i = 0; i < n; i++) {
				cnt += (mid/time[i]);
				if(cnt > m) break;
			}
			
			if(cnt >= m) {
				answer = Math.min(answer, mid);
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
			
		}
		
		System.out.println(answer);
		
	}
}