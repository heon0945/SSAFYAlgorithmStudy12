import java.io.*;
import java.util.*;

public class Main {

	static int V, E;
	static int adj[][];
	static int minEdge[];
	static boolean visited[];
	static PriorityQueue<Vertex> pq;
	
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
		adj = new int[V][V];
		
		for(int i = 0; i < V; i++) {
			for(int j = 0; j < V; j++) {
				adj[i][j] = sc.nextInt();
			}
		}
		
		pq = new PriorityQueue<Vertex>((e1, e2)-> e1.w-e2.w);
		visited = new boolean[V];
		long cost = 0;
		int cnt = 0;
		pq.add(new Vertex(0, 0));
		
		while(!pq.isEmpty()) {
			Vertex cur = pq.poll();
			
			if(visited[cur.num]) continue;
			
			cost += cur.w;
			cnt++;
			visited[cur.num] = true;
			
			if(cnt == V) break;
			
			for(int i = 0; i < V; i++) {
				if(visited[i]) continue;
				pq.add(new Vertex(i, adj[cur.num][i]));
			}
		}
		
		System.out.println(cost);
		
	}
}