import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m;
	static int[][] map;
	static boolean[][] visit;
	static int answer = 0;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][m];
		visit = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		//dfs
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				visit[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visit[i][j] = false;
			}
		}
		
		System.out.println(answer);
		
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}
	
	static void dfs(int x, int y, int order, int sum) {
		if(order == 4) {
			answer = Math.max(answer,  sum);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			if(OOB(tx, ty)) continue;
			
			if(visit[tx][ty]) continue;
			
			//보라색 블록 처리
			if(order == 2) {
				visit[tx][ty] = true;
				dfs(x, y, order + 1, sum + map[tx][ty]);
				visit[tx][ty] = false;
			}
			visit[tx][ty] = true;
			dfs(tx, ty, order + 1, sum + map[tx][ty]);
			visit[tx][ty] = false;
		}
	}
}