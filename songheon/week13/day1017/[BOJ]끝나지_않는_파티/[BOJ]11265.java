import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static int adj[][];
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
		adj = new int[n][n];
	
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				adj[i][j] = sc.nextInt();
			}
		}
		
		while(m > 0) {
			dist = new int[n];
			for(int i = 0; i < n; i++) {
				dist[i] = Integer.MAX_VALUE;
			}
			
			int start = sc.nextInt()-1;
			int end = sc.nextInt()-1;
			int limit = sc.nextInt();
			
			shortestPath(start, end);
			//System.out.println(dist[end]);
			//System.out.println(limit);
			if(dist[end] <= limit) System.out.println("Enjoy other party");
			else System.out.println("Stay here");
			
			m--;
		}
		
	}
	
	static void shortestPath(int start, int end) {
		//다익스트라 알고리즘 시행
		PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> e1.cost-e2.cost);
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.num == end) return; //이미 목적지까지의 경로를 구한 경우
			
			if(dist[cur.num] < cur.cost) continue; //이미 최단 경로인 경우
			
			//선택된 노드의 인접한 노드를 살펴보며 최단 경로 계산
			for(int i = 0; i < n; i++) {
				
				if(dist[i] > cur.cost + adj[cur.num][i]) {
					dist[i] = cur.cost + adj[cur.num][i];
					pq.offer(new Node(i, dist[i]));
				}
				
			}
		}	
		
	}
}