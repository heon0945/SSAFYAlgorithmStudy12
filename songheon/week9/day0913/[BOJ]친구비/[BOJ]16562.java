import java.io.*;
import java.util.*;

public class Main {
	static int n, m, money;
	static int cost[];
	static int parents[];
	static int need;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		money = sc.nextInt();
		
		cost = new int[n];
		parents = new int[n];
		make();
		
		for(int i = 0; i < n; i++) {
			cost[i] = sc.nextInt();
		}
		
		
		
		for(int i = 0; i < m; i++) {
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;
			union(a, b);
		}
		
		int tmp = 0;
		for(int i = 0; i < n; i++) {
			if(parents[i] < 0) {
				tmp += cost[i];
			}
		}
//		System.out.println(Arrays.toString(parents));
//		System.out.println(Arrays.toString(cost));
		if(tmp > money) System.out.println("Oh no");
		else System.out.println(tmp);
	}
	
	static void make() {
		for(int i = 0; i < n; i++) {
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
		if(cost[aRoot] < cost[bRoot]) {
			parents[bRoot] = aRoot;
			
		}
		else {
			parents[aRoot] = bRoot;
		}
		return true;
	}
	
}