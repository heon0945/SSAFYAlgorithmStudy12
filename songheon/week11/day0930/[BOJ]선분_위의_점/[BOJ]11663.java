import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static int point[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		point = new int[n];
		for(int i = 0; i < n; i++) {
			point[i] = sc.nextInt();
		}
		
		Arrays.sort(point);
		
		for(int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			int low = 0, high = n-1;
			
			int xpos = Integer.MAX_VALUE;
			int ypos = Integer.MIN_VALUE;
			
			while(low <= high) {
				int mid = ( low + high ) / 2;
				
				if(point[mid] >= x) {
					xpos = Math.min(xpos, mid);
					high = mid-1;
				}
				else
					low = mid + 1;
			}
			
			low = 0; high = n-1;
			while(low <= high) {
				int mid = (low + high) / 2;
				
				if(point[mid] <= y) {
					ypos = Math.max(ypos, mid);
					low = mid + 1;
				}
				else
					high = mid - 1;
			}
			
			if(xpos == Integer.MAX_VALUE || ypos == Integer.MIN_VALUE)
				System.out.println(0);
			else
				System.out.println(ypos-xpos + 1);
		}
		
	}
}