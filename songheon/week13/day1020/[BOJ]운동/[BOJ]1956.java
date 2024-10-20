import java.io.*;
import java.util.*;

public class Main {
	
	static int v, e;
	static ArrayList<ArrayList<Node>> adj;
	static int dist[];
	static int answer;
	
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
		
		v = sc.nextInt();
		e = sc.nextInt();
		
		adj = new ArrayList<>();
		answer = Integer.MAX_VALUE;
		
		for(int i = 0; i < v; i++) {
			adj.add(new ArrayList<>());
		}
		
		for(int i = 0; i < e; i++) {
			int start = sc.nextInt()-1;
			int end = sc.nextInt()-1;
			int cost = sc.nextInt();
			
			adj.get(start).add(new Node(end, cost));
		}
		
		for(int i = 0; i < v; i++) {
			shortestPath(i);
			answer = Math.min(answer, dist[i]);
		}
		
		if(answer == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(answer);
	}
	
	static void shortestPath(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2)->e1.cost-e2.cost);
		dist = new int[v];
		for(int i = 0; i < v; i++)
			dist[i] = Integer.MAX_VALUE;
		
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(dist[cur.num] < cur.cost) continue;
			
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