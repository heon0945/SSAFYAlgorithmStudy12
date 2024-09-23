import java.util.*;
import java.io.*;

public class Main {

	static int n, k, l;
	static int map[][];
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	static Queue<Rot> rotation;
	static int dir = 0;
	
	static class Rot{
		int t;
		char dir;
		public Rot(int t, char dir) {
			super();
			this.t = t;
			this.dir = dir;
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
		k = sc.nextInt();
		
		map = new int[n][n];
		
		for(int i = 0; i < k; i++) {
			map[sc.nextInt()-1][sc.nextInt()-1] = 1;
		}
		
		l = sc.nextInt();
		rotation = new ArrayDeque<>();
		
		for(int i = 0; i < l; i++) {
			rotation.add(new Rot(sc.nextInt(), sc.next().charAt(0)));
		}
		
		System.out.println(gameStart());
	}
	
	static int gameStart() {
		int time = 0;
		int x = 0, y = 0;
		
		Queue<Pair> snake = new ArrayDeque<>();
		snake.add(new Pair(0, 0));
		map[0][0] = -1;
		
		while(true) {
			time++;
			
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(OOB(nx, ny)) break;
			if(map[nx][ny] == -1) break;
			
			
			if(map[nx][ny] != 1) {
				Pair cur = snake.poll();
				map[cur.x][cur.y] = 0;
			}
			

			x = nx;
			y = ny;
			snake.add(new Pair(x, y));
			map[x][y] = -1;
			
			if(!rotation.isEmpty() && time == rotation.peek().t) {
				char d = rotation.poll().dir;
				
				if(d == 'D') { //오른쪽
					dir++;
					if(dir > 3) dir = 0;
				}
				else { //왼쪽
					dir--;
					if(dir < 0) dir = 3;
				}
			}
			
		}
		
		return time;
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}
}