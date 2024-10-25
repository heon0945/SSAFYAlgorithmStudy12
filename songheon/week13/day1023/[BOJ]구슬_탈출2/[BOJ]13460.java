import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static Pair dest;
	static char map[][];
	static int dx[] = { -1, 0, 1, 0 }; //상 좌 하 우
	static int dy[] = { 0, -1, 0, 1 };
	static int answer = 11;

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];
		Pair red = null, blue = null;
		for (int i = 0; i < n; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = tmp.charAt(j);
				if (map[i][j] == 'O')
					dest = new Pair(i, j);
				else if (map[i][j] == 'R')
					red = new Pair(i, j);
				else if (map[i][j] == 'B')
					blue = new Pair(i, j);
			}
		}

		// dfs : 연산횟수, 빨간 구슬 위치, 파란 구슬 위치
		dfs(0, red, blue);

		if(answer == 11)
			System.out.println(-1);
		else
			System.out.println(answer);
	}

	static void dfs(int cnt, Pair red, Pair blue) {

		// 백 트래킹
		if (cnt >= answer)
			return;

		// 종료 조건
		// 파란 구슬이 목적지에 도달(실패)
		if (blue.x == dest.x && blue.y == dest.y) {
			//System.out.println("파란구슬 실패");
			return;
		}
		// 빨간 구슬이 목적지에 도달(성공)
		if (red.x == dest.x && red.y == dest.y) {
			//System.out.println("빨간구슬 성공");
			answer = Math.min(answer, cnt);
			return;
		}

		// 현재 맵 상태 복사
		char tmpMap[][] = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tmpMap[i][j] = map[i][j];
			}
		}

		// 네 방향으로 이동 결정
		for (int i = 0; i < 4; i++) {
			
			// 이동 가능 판단 -> 이동
			Pair rpos = moving(red, dx[i], dy[i]);
			Pair bpos = moving(blue, dx[i], dy[i]);
			
			//움직임이 불가한 경우
			if(rpos.x == red.x && rpos.y == red.y 
					&& bpos.x == blue.x && bpos.y == blue.y) continue;
			
			//구슬 위치가 겹친 경우
			if (rpos.x == bpos.x && rpos.y == bpos.y) {
				if (map[rpos.x][rpos.y] != 'O') { // 두 구슬이 겹친 경우
					// 좌우로 기울인 경우 dy != 0
					if (dy[i] != 0) {
						if (dy[i] == 1) {
							if (red.y > blue.y) {
								bpos.y -= dy[i];
							} else {
								rpos.y -= dy[i];
							}
						} else { // dy[i] == -1
							if (red.y < blue.y) {
								bpos.y -= dy[i];
							} else {
								rpos.y -= dy[i];
							}
						}
					}
					// 상하로 기울인 경우 dx != 0
					else {
						if (dx[i] == 1) {
							if (red.x > blue.x) {
								bpos.x -= dx[i];
							} else {
								rpos.x -= dx[i];
							}
						} else { // dx[i] == -1
							if (red.x < blue.x) {
								bpos.x -= dx[i];
							} else {
								rpos.x -= dx[i];
							}
						}
					}
				}
			}
			
			//System.out.println("빨간 구슬 위치 " + rpos.x + " " + rpos.y);
			//System.out.println("파란 구슬 위치 " + bpos.x + " " + bpos.y);
			
			// dfs 시행
			map[red.x][red.y] = '.';
			map[blue.x][blue.y] = '.';
			if(map[rpos.x][rpos.y] != 'O')
				map[rpos.x][rpos.y] = 'R';
			if(map[bpos.x][bpos.y] != 'O')
				map[bpos.x][bpos.y] = 'B';
			dfs(cnt + 1, rpos, bpos);
			
			// 원복
			map[red.x][red.y] = 'R';
			map[blue.x][blue.y] = 'B';
			if(map[rpos.x][rpos.y] != 'O')
				map[rpos.x][rpos.y] = '.';
			if(map[bpos.x][bpos.y] != 'O')
				map[bpos.x][bpos.y] = '.';
		}

		// 맵 전체 원복
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = tmpMap[i][j];
			}
		}
	}

	static Pair moving(Pair ball, int dx, int dy) {
		int x = ball.x;
		int y = ball.y;

		while (true) {
			int nx = x + dx;
			int ny = y + dy;

			if (map[nx][ny] == '#')
				break;
			if (map[nx][ny] == 'O') {
				x = nx;
				y = ny;
				break;
			}
			x = nx;
			y = ny;
		}

		return new Pair(x, y);
	}

}