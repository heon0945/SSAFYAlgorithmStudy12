import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static long m;
	static int height[];
	static long answer = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		height = new int[n];
		for(int i = 0; i < n; i++) {
			height[i] = sc.nextInt();
		}
		
		Arrays.sort(height);
		int high = height[n-1];
		int low = 0;
		
		//low랑 high 확인할 필요 없음 (답이 될 수 없음)
		
		int mid = (low + high) / 2;
		
		while(low + 1 < high) {
			
			//중간 값으로 컷팅
			long get = cutting(mid);
			
			//얻은 값이 원하는 값과 같은 경우
			if(get == m) {
				System.out.println(mid);
				return;
			}
			else if(get > m ){ //얻은 값이 원하는 값보다 큰 경우
				// 설정 값 올리기
				answer = Math.max(mid, answer);
				low = mid + 1;
				mid = (low + high) / 2;
				continue;
			}
			else { //얻은 값이 원하는 값보다 작은 경우
				//설정 값 내리기
				high = mid - 1;
				mid = (low + high) / 2;
				continue;
			}
		}
		
		if(cutting(high) >= m) {
			answer = Math.max(answer, high);
		}
		if(cutting(low) >= m) {
			answer = Math.max(answer, low);
		}
		System.out.println(answer);
		return;
		
		
	}
	
	
	static long cutting(int k) {
		long ans = 0;
		
		for(int i = 0; i < n; i++) {
			if(height[i] <= k) continue;
			ans += (height[i] - k);
		}
		return ans;
	}
}