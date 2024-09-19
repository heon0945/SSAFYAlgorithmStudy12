import java.io.*;
import java.util.*;

public class Main {

	static int tc;
	static int n;
	static int k;
	static int m;
	static int parents[];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		tc = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= tc; t++) {
			sb.append("Scenario ").append(t).append(":").append('\n');
			
			n = Integer.parseInt(br.readLine());
			
			make();
			
			k = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < k; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			m = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(find(a) == find(b)) {
					sb.append(1).append('\n');
				}
				else {
					sb.append(0).append('\n');
				}
			}
			
			sb.append('\n');
		}
		
		System.out.println(sb);
		
		
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
		
		if(aRoot == bRoot)
			return false;
		parents[aRoot] += parents[bRoot]; //트리에 포함된 노드 개수
		parents[bRoot] = aRoot;
		return true;
	}
}