import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static ArrayList<ArrayList<Node>> adj;
	static int dist[];
	
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
		
		dist = new int[n];
		for(int i = 0; i < n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		shortestPath(0);
		
		System.out.println(dist[n-1]);
	}
	
	static void shortestPath(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2)-> e1.cost-e2.cost);
		
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(dist[cur.num] < cur.cost) continue;
			
			if(cur.num == n-1) break;
			
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