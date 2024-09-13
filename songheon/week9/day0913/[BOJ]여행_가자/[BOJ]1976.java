import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int parents[];
	static boolean visit[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		parents = new int[n+1];
		visit = new boolean[n+1];
		
		make();
		
		for(int i = 1; i <= n; i++) {
			int a = i;
			for(int j = 1; j <= n; j++) {
				int b = j;
				if(sc.nextInt() == 0) continue;
				union(a, b);
			}
		}
		
		int cur = find(sc.nextInt());
		
		for(int j = 0; j < m-1; j++) {
			int k = sc.nextInt();
			if(cur != find(k)) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
	
	static void make() {
		for(int i = 1; i <= n; i++) {
			parents[i] = -1;
		}
	}
	
	static int find(int a) {
		if(parents[a] < 0)
			return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}
}