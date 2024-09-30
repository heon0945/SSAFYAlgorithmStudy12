import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m;
	static int need[];
	static int answer = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		need = new int[n];
		for(int i = 0; i < n; i++) {
			need[i] = sc.nextInt();
		}
		m = sc.nextInt();
		
		Arrays.sort(need);
		
		int low = 0;
		int high = need[n-1];
		
		while(low + 1 < high) {
			
			int mid = (low + high) / 2;
			int sum = 0;
			for(int i = 0; i < n; i++) {
				if(need[i] <= mid) {
					sum += need[i];
				}
				else {
					sum += mid;
				}
			}
		
			if(sum > m) {
				high = mid - 1;
			}
			else {
				low = mid + 1;
				answer = mid;
			}
		}
		
		//low와 high 확인
		int sum = 0;
		for(int i = 0; i < n; i++) {
			if(need[i] <= low) {
				sum += need[i];
			}
			else {
				sum += low;
			}
		}
		if (sum <= m){
			//System.out.println("low");
			answer = low;
		}
		
		sum = 0;
		for(int i = 0; i < n; i++) {
			if(need[i] <= high) {
				sum += need[i];
			}
			else {
				sum += high;
			}
		}
		if (sum <= m){
			answer = high;
			//System.out.println("high");
		}
		
		System.out.println(answer);
	}
}