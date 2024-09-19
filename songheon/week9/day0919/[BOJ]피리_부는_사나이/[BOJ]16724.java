import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static char pos[];
	static int parents[];
	static int count;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		pos = new char[n * m];
		parents = new int[n * m];
		count = n * m;
		
		
		make();
		
		for(int i = 0; i < n; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < m; j++) {
				pos[i * m + j] = tmp.charAt(j);
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				int p = i * m + j;
				char cur = pos[p];
				int opp = p + 1; //r
				
				if(cur == 'D') {
					opp = p + m;
				}
				else if(cur == 'L') {
					opp = p - 1;
				}
				else if(cur == 'U') {
					opp = p - m;
				}
				
				union(p, opp);
			}
		}
		
		System.out.println(count);
	}
	
	
	static void make() {
		for(int i = 0; i < n * m; i++) {
			parents[i] = -1;
		}
	}
	
	static int find(int a) {
		if(parents[a] < 0) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return true;
		
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		count--;
		return true;
	}
	
	
}