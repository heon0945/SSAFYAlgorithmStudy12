import java.io.*;
import java.util.*;

public class Solution {

	static int tc;
	static int n;
	static int map[][];
	static int score;
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1}; // 상 우 하 좌 (시계방향)
	static List<Pair> hole[];
	static Pair end;
	
	public static class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		tc = sc.nextInt();
		
		for(int t = 1; t <= tc; t++) {
			score = 0;
			n = sc.nextInt();
			map = new int[n][n];
			
			//빈 칸 정보 저장 -> 시작 위치 후보
			List<Pair> empty = new ArrayList<>();
			
			//웜 홀 정보 저장 (idx : 웜홀 번호 - 6)
			hole = new ArrayList[5];
			for(int i = 0; i < 5; i++) {
				hole[i] = new ArrayList<>();
			}
			
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] == 0) { //빈 칸
						empty.add(new Pair(i, j));
					}
					else if(map[i][j] >= 6) {
						int num = map[i][j];
						hole[num-6].add(new Pair(i, j));
					}
				}
			}
			
			for(int i = 0; i < empty.size(); i++) {
				//빈칸 대상으로 시작 위치 정하기
				Pair start = empty.get(i);
				//System.out.println("start " + start.x + " " + start.y);
				
				//시작 방향 정하기]
				for(int j = 0; j < 4; j++) {
					int dir = j;
					//System.out.println("dir " + dir);
					
					int result = gameStart(start, dir);
					//System.out.println(result);
					//정해진 시작위치와 방향으로 게임 시작
					score = Math.max(score, result);
				}
			}
			
			
			sb.append("#").append(t).append(" ").append(score).append('\n');
		}
		
		System.out.println(sb);
		
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}
	
	static int reflect(int num, int dir) {
		switch(num) {
		case 1 : {
			if(dir == 2) dir = 1;
			else if(dir == 3) dir = 0;
			else dir = (dir + 2) % 4;
			break;
		}
		case 2 : {
			if(dir == 0) dir = 1;
			else if(dir == 3) dir = 2;
			else dir = (dir + 2) % 4;
			break;
		}
		case 3 : {
			if(dir == 1) dir = 2;
			else if(dir == 0) dir = 3;
			else dir = (dir + 2) % 4;
			break;
		}
		case 4 : {
			if(dir == 1) dir = 0;
			else if(dir == 2) dir = 3;
			else dir = (dir + 2) % 4;
			break;
		}
		case 5 : {
			dir = (dir + 2) % 4;
			break;
		}
		}
		return dir;
	}
	
	static int gameStart(Pair start, int dir) {
		
		int score = 0;
		int sx = start.x;
		int sy = start.y;
		int x = sx;
		int y = sy;
		
		
		while(true) {
			int tx = x + dx[dir];
			int ty = y + dy[dir];
			
			//종료 조건
			//시작 포인트
			if(tx == sx && ty == sy) break;			
			if(OOB(tx, ty)) { //벽을 만나는 경우
				dir = (dir + 2) % 4;
				x = tx;
				y = ty;
				score++;
			}
			else {
				//종료 포인트
				if(map[tx][ty] == -1) break;
				int next = map[tx][ty];
				if(next > 0 && next <= 5) {
					dir = reflect(next, dir);
					x = tx;
					y = ty;
					score++;
				}
				else if(next >= 6){
					//System.out.println("웜홀");
					Pair tmp = hole[next-6].get(0);
					if(tmp.x == tx && tmp.y == ty) {
						Pair tmp2 = hole[next-6].get(1);
						x = tmp2.x; y = tmp2.y;
					}
					else {
						x = tmp.x; y = tmp.y;
					}
				}
				else {
					x = tx;
					y = ty;
				}
			}
		}
		
		
		return score;
	}
}