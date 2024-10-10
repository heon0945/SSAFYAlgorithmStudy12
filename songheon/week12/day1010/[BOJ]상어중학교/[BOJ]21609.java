import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m;
	static int map[][];
	static int score;
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, -1, 0, 1};
	
	static class Group{
		int x, y;
		int size;
		int rainbow;
		public Group(int x, int y, int size, int rainbow) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.rainbow = rainbow;
		}
		@Override
		public String toString() {
			return "Group [x=" + x + ", y=" + y + ", size=" + size + ", rainbow=" + rainbow + "]";
		}
		
	}
	
	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		while(true) {
			//크기가 가장 큰 블록 찾기
			Group find = null;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(map[i][j] == -1 || map[i][j] == 0 || map[i][j] == -2) continue;
					Group tmp = checking(i, j);
					if(find == null) find = tmp;
					else if(tmp == null) continue;
					else {
						if(find.size < tmp.size) find = tmp;
						else if(find.size == tmp.size) {
							if(find.rainbow < tmp.rainbow) find = tmp;
							else if(find.rainbow == tmp.rainbow) {
								if(find.x < tmp.x) find = tmp;
								else if(find.x == tmp.x && find.y < tmp.y) find = tmp;
							}
						}
					}
				}
			}
			
			//System.out.println(find);
			
			//점수 업데이트
			if(find != null) score += (find.size * find.size);
			else break; //블록을 찾을 수 없는 경우
			
			//System.out.println("제거");
			//찾은 블록의 블록 그룹 제거
			removing(find.x, find.y);
			//printmap();
			
			//System.out.println("중력");
			//중력
			gravity();
			//printmap();
			
			//System.out.println("회전");
			//회전
			rotating();
			//printmap();
			
			//System.out.println("중력");
			//중력
			gravity();
			//printmap();
		}
		
		System.out.println(score);
	}
	
	static void rotating() {
		int tmp[][] = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				tmp[n-1-j][i] = map[i][j];
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}
	
	static void printmap() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void gravity() {
		for(int i = n-2; i >= 0; i--) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == -1 || map[i][j] == -2) continue;
				int x = i;
				while(true) {
					int tx = x + 1;
					if(tx >= n || map[tx][j] != -2) break;
					x = tx;
				}
				if(x == i) continue;
				map[x][j] = map[i][j];
				map[i][j] = -2;
			}
		}
	}
	
	static void removing(int x, int y) {
		Queue<Pair> q = new ArrayDeque<>();
		int flag = map[x][y];
		boolean visit[][] = new boolean[n][n];
		
		q.add(new Pair(x, y));
		visit[x][y] = true;
		map[x][y] = -2;		
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int tx = cur.x + dx[i];
				int ty = cur.y + dy[i];
				
				if(OOB(tx, ty)) continue;
				if(visit[tx][ty]) continue;
				
				if(map[tx][ty] == 0 || map[tx][ty] == flag) {
					q.add(new Pair(tx, ty));
					visit[tx][ty] = true;
					map[tx][ty] = -2;		
				}
			}
		}
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}
	
	static Group checking(int x, int y) {
		Queue<Pair> q = new ArrayDeque<>();
		int flag = map[x][y];
		boolean visit[][] = new boolean[n][n];
		int size = 0;
		int rainbow = 0;
		int r = x, c = y;
		
		q.add(new Pair(x, y));
		visit[x][y] = true;
		size++;
		
		while(!q.isEmpty()) {
			Pair cur = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int tx = cur.x + dx[i];
				int ty = cur.y + dy[i];
				
				if(OOB(tx, ty)) continue;
				if(visit[tx][ty]) continue;
				if(map[tx][ty] == 0 || map[tx][ty] == flag) {
					q.add(new Pair(tx, ty));
					visit[tx][ty] = true;
					size++;
					if(map[tx][ty] == 0) rainbow++;
					else {
						if(tx < r) {
							r = tx; c = ty;
						}
						else if(tx == r && ty < c) {
							c = ty;
						}
					}
				}
				
			}
		}
		
		if(size < 2) return null;
		return new Group(r, c, size, rainbow);
		
	}
	
	
}