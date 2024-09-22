import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static Edge[] edges;
	static int[] parents;
	
	
	static class Edge implements Comparable<Edge>{
		int start, end;
		int weight;
		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight-o.weight;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		edges = new Edge[m];

		for(int i = 0; i < m; i++) {
			int s = sc.nextInt()-1;
			int e = sc.nextInt()-1;
			int c = sc.nextInt();
			edges[i] = new Edge(s, e, c);
		}
		
		Arrays.sort(edges);
		
		make();
		int cnt = 0;
		long cost = 0;
		int max = 0;
		
		for(int i = 0; i < m; i++) {
			
			if(cnt == n-1)
				break;
			
			Edge cur = edges[i];
			
			if(!union(cur.start, cur.end)) continue;
			
			cnt++;
			cost += cur.weight;
			
			if(cnt == n-1)
				max = cur.weight;
		}

		System.out.println(cost - max);
	}
	
	static void make() {
		parents = new int[n];
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
		
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}
}