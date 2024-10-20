import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int a, b, c;
	static int m;
	static ArrayList<ArrayList<Node>> adj;
	static int adist[], bdist[], cdist[];
	static int ansD, ansN;

	
	static class Node{
		int num, cost;

		public Node(int num, int cost) {
			super();
			this.num = num;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		a = sc.nextInt()-1;
		b = sc.nextInt()-1;
		c = sc.nextInt()-1;
		m = sc.nextInt();
		
		adj = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		
		for(int i = 0; i < m; i++) {
			int start = sc.nextInt()-1;
			int end = sc.nextInt()-1;
			int cost = sc.nextInt();
			
			adj.get(start).add(new Node(end, cost));
			adj.get(end).add(new Node(start, cost));
		}
		
		adist = new int[n];
		bdist = new int[n];
		cdist = new int[n];
		for(int i = 0; i < n; i++) {
			adist[i] = Integer.MAX_VALUE;
			bdist[i] = Integer.MAX_VALUE;
			cdist[i] = Integer.MAX_VALUE;
		}
		
		
		shortestPath(a, adist);
		shortestPath(b, bdist);
		shortestPath(c, cdist);
		
		for(int i = 0; i < n; i++) {
			
			int minD = Math.min(adist[i], Math.min(bdist[i], cdist[i]));
			
			if(minD > ansD) {
				ansD = minD;
				ansN = i;
			}
		}
		
		System.out.println(ansN+1);
	}
	
	static void shortestPath(int start, int dist[]) {
		PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2)->e1.cost-e2.cost);
	
		pq.add(new Node(start, 0));
		int cnt = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(dist[cur.num] < cur.cost) continue;
			
			cnt++;
			if(cnt >= n) break;
			
			for(int i = 0; i < adj.get(cur.num).size(); i++) {
				Node tmp = adj.get(cur.num).get(i);
				
				if(dist[tmp.num] > tmp.cost + cur.cost) {
					dist[tmp.num] = tmp.cost + cur.cost;
					pq.add(new Node(tmp.num, dist[tmp.num]));
				}
			}
			
		}
	}
}