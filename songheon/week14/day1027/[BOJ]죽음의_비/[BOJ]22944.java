import java.io.*;
import java.util.*;

public class Main {
	
	static int n, h, d;
	static char map[][];
	static int check[][]; //현재 플레이어의 체력을 저장
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, -1, 0, 1};
	static int px, py;
	static int destx, desty;
	static int answer;
	
	static class Node{
		int x, y, h, cost, cnt;

		public Node(int x, int y, int h, int cost, int cnt) {
			super();
			this.x = x; //플레이어 위치
			this.y = y;
			this.h = h; //체력
			this.cost = cost; //우산 내구도
			this.cnt = cnt; //현재 이동 횟수 (거리)
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		map = new char[n][n];
		check = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < n; j++) {
				map[i][j] = tmp.charAt(j);
				if(map[i][j] == 'S') {
					px = i; py = j; //플레이어 시작 위치
				}
				else if(map[i][j] == 'E') {
					destx = i; desty = j; //도착 위치
				}
			}
		}
		
		answer = bfs(px, py);
		System.out.println(answer);
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}
	
	
	static int bfs(int px, int py) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(px, py, h, 0, 0)); //시작점 추가
		check[px][py] = h; //시작점 방문 처리 (현재 플레이어 초기 체력)
		
		int min = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			
			Node cur = q.poll();
			
			for(int i = 0; i < 4; i++) { //현재 이동 가능한 칸 탐색
				int curh = cur.h, curc = cur.cost, curcnt = cur.cnt;
				
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(OOB(nx, ny)) continue; //범위 밖으로 나간 경우
				
				if(nx == destx && ny == desty) { //도착점에 도착한 경우
					min = Math.min(min, curcnt + 1);
					continue;
				}
				
				if(map[nx][ny] == 'U') curc = d; //우산인 경우 (우산 자리에도 비맞음)
				
				//비맞는 처리
				if(curc != 0) curc--;
				else curh--;
				
				//비맞고 플레이어 죽는 경우 
				if(curh == 0) continue;
				
				//플레이어 살아있는 경우 -> check 배열 업데이트 
				//(먼저 지나간 경우보다, 현재 체력이 더 많이 남은 경우 업데이트 -> 더 멀리 갈 수 있음)
				if(check[nx][ny] < curh + curc) {
					check[nx][ny] = curh + curc;
					q.add(new Node(nx, ny, curh, curc, curcnt + 1));
				}
			}
			
		}
		if(min == Integer.MAX_VALUE) return -1;
		return min;
	}
}