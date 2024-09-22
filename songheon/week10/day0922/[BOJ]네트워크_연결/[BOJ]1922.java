import java.io.*;
import java.util.*;

public class Main {

	static int V, E;
	static Node adj[];
	static int minEdge[];
	static boolean visited[];
	static PriorityQueue<Vertex> pq;
	
	
	static class Node{
		int to;
		Node next;
		int weight;
		public Node(int to, Node next, int weight) {
			super();
			this.to = to;
			this.next = next;
			this.weight = weight;
		}
	}
	
	static class Vertex{
		int num;
		int w;
		public Vertex(int num, int w) {
			super();
			this.num = num;
			this.w = w;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		adj = new Node[V+1];
		
		for(int i = 0; i < E; i++) {
			int e1 = sc.nextInt();
			int e2 = sc.nextInt();
			int w = sc.nextInt();
			adj[e1] = new Node(e2, adj[e1], w);
			adj[e2] = new Node(e1, adj[e2], w);
		}
		
		pq = new PriorityQueue<Vertex>((e1, e2)-> e1.w-e2.w);
		visited = new boolean[V+1];
		int cost = 0;
		int cnt = 0;
		pq.add(new Vertex(1, 0));
		
		while(!pq.isEmpty()) {
			Vertex cur = pq.poll();
			
			if(visited[cur.num]) continue;
			
			cost += cur.w;
			cnt++;
			visited[cur.num] = true;
			
			if(cnt == V) break;
			
			for(Node n = adj[cur.num]; n != null; n = n.next) {
				if(visited[n.to]) continue;
				pq.add(new Vertex(n.to, n.weight));
			}
		}
		
		System.out.println(cost);
		
	}
}