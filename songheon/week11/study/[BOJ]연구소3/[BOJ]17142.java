import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static int ec;
	static int map[][];
	static List<Virus> virus = new ArrayList<>();
	static boolean pick[];
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, -1, 0, 1 };
	static int answer = 2500;

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class Virus {
		int x, y;

		public Virus(int x, int y) {
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

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();

				if (map[i][j] == 0)
					ec++;
				else if (map[i][j] == 2)
					virus.add(new Virus(i, j));
			}
		}
		pick = new boolean[virus.size()];

		// 활성화 바이러스 고르기
		picking(0, 0);
		if(answer == 2500) {
			System.out.println(-1);
			return;
		}
		System.out.println(answer);
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}

	static void spread(int[][] tmp, int empty) {
		int time = 0;
		boolean[][] visit = new boolean[n][n];

		// bfs 진행
		Queue<Pair> q = new ArrayDeque<Pair>();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (tmp[i][j] == 3) {
					q.add(new Pair(i, j));
					visit[i][j] = true;
				}
			}
		}

		while (!q.isEmpty()) {
			//종료 조건
			//최적 해가 아닌 경우 (가지치기)
			if(time > answer) break;
			//빈 칸이 없는 경우 -> 해
			if(empty <= 0) {
				answer = Math.min(answer, time);
				break;
			}
			
			int sz = q.size();
			while(sz > 0) {
				Pair cur = q.poll();
				int nx = cur.x;
				int ny = cur.y;
				
				
				for(int i = 0; i < 4; i++) {
					int tx = nx + dx[i];
					int ty = ny + dy[i];
					
					if(OOB(tx, ty)) continue;
					if(visit[tx][ty]) continue;
					if(tmp[tx][ty] == 1 || tmp[tx][ty] == 3) continue;
					q.add(new Pair(tx, ty));
					visit[tx][ty] = true;
					if(tmp[tx][ty] == 0) empty--;
					tmp[tx][ty] = 3;
				}
				
				sz--;
			}
			time++;
			
		}
	}

	static void picking(int order, int start) {
		if (order == m) {

			// System.out.println(Arrays.toString(pick));
			
			int tmp[][] = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			
			// 바이러스 활성화 체크
			for (int i = 0; i < virus.size(); i++) {
				if (!pick[i])
					continue;

				Virus cur = virus.get(i);
				tmp[cur.x][cur.y] = 3;
			}
			spread(tmp, ec);
			return;
		}

		for (int i = start; i < virus.size(); i++) {
			if (pick[i])
				continue;
			pick[i] = true;
			picking(order + 1, i + 1);
			pick[i] = false;
		}
	}
}