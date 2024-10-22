import java.io.*;
import java.util.*;

public class Main {
	
	static int r, c, m;
	static ArrayList<Shark> sharks;
	static int dx[] = {-1, 0, 1, 0}; //상하우좌 -> 상우하좌
	static int dy[] = {0, 1, 0, -1};
	static int score;
	static Shark map[][];
	
	static class Shark{
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		r = sc.nextInt();
		c = sc.nextInt();
		m = sc.nextInt();
		map = new Shark[r+1][c+1];
		sharks = new ArrayList<>();
		for(int i = 0; i < m; i++) {
			int tr = sc.nextInt();
			int tc = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt()-1;
			int z = sc.nextInt();
			
			if(d == 2) d = 1;
			else if(d == 1) d = 2;
			
			if(d == 0 || d == 2)
				s = s % ((r-1)*2);
			else
				s = s % ((c-1)*2);
			
			Shark shark = new Shark(tr, tc, s, d, z);
			sharks.add(shark);
			map[tr][tc] = shark;
		}
		
		int player = 0;
		while(true) {
			
			//플레이어 이동
			player++;
			
			if(player == c + 1) break;
			if(sharks.size() == 0) break;
			
			//상어 포획
			for(int i = 1; i <= r; i++) {
				if(map[i][player] != null) {
					sharks.remove(map[i][player]);
					score += map[i][player].z;
					map[i][player] = null;
					break;
				}
			}
			
			//상어 이동
			//한 칸씩 속력만큼 이동
			//우선순위큐에 이동한 상어를 사이즈 순서대로 넣기
			PriorityQueue<Shark> pq = new PriorityQueue<>((e1, e2)-> e2.z-e1.z);
			
			for(Shark shark : sharks) {
				map[shark.r][shark.c] = null;
				moving(shark);
				pq.add(shark);
			}
			
			//상어 정리
			//우선순위큐 하나씩 빼면서 맵에 기록
			//맵이 이미 차있는 경우 패스
			while(!pq.isEmpty()) {
				Shark cur = pq.poll();
				
				if(map[cur.r][cur.c] != null) {
					sharks.remove(cur);
					continue;
				}
				
				map[cur.r][cur.c] = cur;
			}
		}
		System.out.println(score);
	}
	
	static void moving(Shark shark) {
		for(int i = 0; i < shark.s; i++) {
			int tx = shark.r + dx[shark.d];
			int ty = shark.c + dy[shark.d];
			
			if(tx >= 1 && tx <= r && ty >= 1 && ty <= c) {
				shark.r = tx;
				shark.c = ty;
			}
			else {
				shark.d = (shark.d + 2) % 4;
				tx = shark.r + dx[shark.d];
				ty = shark.c + dy[shark.d];
				shark.r = tx;
				shark.c = ty;
			}
		}
	}
}