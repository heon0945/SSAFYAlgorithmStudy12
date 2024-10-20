
import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m, r;
	static int items[];
	static ArrayList<ArrayList<Node>> adj;
	static int answer = 0;
	
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
		r = sc.nextInt();
		
		items = new int[n];
		for(int i = 0; i < n; i++) {
			items[i] = sc.nextInt();
		}
		
		adj = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			adj.add(new ArrayList<>());
		}
		
		for(int i = 0; i < r; i++) {
			int start = sc.nextInt()-1;
			int end = sc.nextInt()-1;
			int d = sc.nextInt();
			adj.get(start).add(new Node(end, d));
			adj.get(end).add(new Node(start, d));
		}
		
	
		for(int i = 0; i < n; i++) {
			shortestPath(i);
		}
		
		System.out.println(answer);
		
	}
	
	static void shortestPath(int start) {
		//다익스트라
		PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> e1.cost-e2.cost);
			
		int dist[] = new int[n];
		for(int i = 0; i < n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		int cnt = 0;
		dist[start] = 0;
		pq.add(new Node(start, 0));
		
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
		
		int acq = 0;
		for(int i = 0; i < n; i++) {
			if(dist[i] <= m) {
				acq += items[i];
			}
		}
		answer = Math.max(acq, answer);
		
	}
}