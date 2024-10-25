import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int map[][];
	static int px, py; //아기 상어 위치
	static int psize = 2; //아기 상어 크기
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, -1, 0, 1};
	static ArrayList<Fish> fish;
	static int dist[][];
	
	static class Fish{
		int x, y, size;

		public Fish(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
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
		//System.out.println("엄마상어,,도와줘!!!");
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		map = new int[n][n];
		fish = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			for(int j  = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
				
				if(map[i][j] == 9) {
					px = i; py = j;
				}
				else if(map[i][j] != 0) {
					fish.add(new Fish(i, j, map[i][j]));
				}
			}
		}
		
		int tmpeat = 0;
		int time = 0;
		while(true) {
			int num = selectFish();
			//먹을 물고기 없는 경우 -> 종료
			if(num == -1) {
				System.out.println(time);
				System.exit(0);
			}
			
			//물고기 먹음
			Fish f = fish.get(num);
			
			//System.out.println(f.x + " " + f.y);
			time += dist[f.x][f.y];
			tmpeat++;
			
			if(tmpeat == psize) {
				psize++;
				tmpeat = 0;
			}
			
			map[f.x][f.y] = 9;
			map[px][py] = 0;
			px = f.x; py = f.y;
			fish.remove(num);
			
		}
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}
	
	static int selectFish()	{
		
		//bfs
		dist = new int[n][n];
		boolean visit[][] = new boolean[n][n];
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(px, py));
		visit[px][py] = true;
		
		while(!q.isEmpty()) {
			
			Pair cur = q.poll();
			
			int x = cur.x;
			int y = cur.y;
			int curd = dist[x][y];
			
			for(int i = 0; i < 4; i++) {
				int tx = x + dx[i];
				int ty = y + dy[i];
				
				if(OOB(tx, ty)) continue;
				if(visit[tx][ty]) continue;
				if(map[tx][ty] > psize) continue;
				
				q.add(new Pair(tx, ty));
				visit[tx][ty] = true;
				dist[tx][ty] = curd + 1;
			}
		}
		
		int num = -1;
		int minD = Integer.MAX_VALUE;
		for(int i = 0; i < fish.size(); i++) {
			Fish f = fish.get(i);
			
			if(dist[f.x][f.y] == 0) continue;
			if(minD <= dist[f.x][f.y]) continue;
			if(map[f.x][f.y] >= psize) continue;
			num = i;
			minD = dist[f.x][f.y];
		}
		
		return num;
	}
}