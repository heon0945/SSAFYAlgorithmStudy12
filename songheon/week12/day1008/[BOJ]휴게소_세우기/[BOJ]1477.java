import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m, l;
	static int[] dist;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		l = sc.nextInt();
		
		dist = new int[n + 2];
		dist[0] = 0;
		dist[n + 1] = l;
		for(int i = 1; i <= n; i++) {
			dist[i] = sc.nextInt();
		}
		Arrays.sort(dist);
		
		int answer = Integer.MAX_VALUE;
		int left = 1, right = l-1;
		
		while(left <= right) {
			int tmpInterval = (left + right) / 2;
			int buildable = 0;
			
			for(int i = 1; i <= n+1; i++) {
				int interval = dist[i] - dist[i-1];
				buildable += interval / tmpInterval;
				if(interval % tmpInterval == 0) buildable--;
			}
			
			if(buildable > m) left = tmpInterval + 1;
			else {
				right = tmpInterval - 1;
				answer = Math.min(answer, tmpInterval);;
			}
			
		}
		
		System.out.println(answer);
		
	}
}