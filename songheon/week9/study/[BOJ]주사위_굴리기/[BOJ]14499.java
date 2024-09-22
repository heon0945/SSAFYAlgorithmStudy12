import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m;
	static int x, y;
	static int k;
	static int dice[];
	static int map[][];	
	static int dx[] = {0, 0, 0, -1, 1};
	static int dy[] = {0, 1, -1, 0, 0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		n = sc.nextInt();
		m = sc.nextInt();
		x = sc.nextInt();
		y = sc.nextInt();
		k = sc.nextInt();
		
		map = new int[n][m];
		dice = new int[6];
		
		//map 정보 저장
		for(int i = 0; i < n; i ++) {
			for(int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		while(k > 0) {
			k--;
			
			//회전 방향
			//dir : 1(동) 2(서) 3(북) 4(남)
			int dir = sc.nextInt();
			
			//회전따라 주사위 위치 이동
			int tx = x + dx[dir];
			int ty = y + dy[dir];
			
			//범위를 벗어난 경우
			if(OOB(tx, ty)) {
				continue;
			}
			x = tx;
			y = ty;
			
			//주사위 회전따라 배열 정보 갱신
			rotating(dir);
			
			//회전 후 주사위 밑의 값 복사
			if(map[x][y] != 0) {
				dice[0] = map[x][y];
				map[x][y] = 0;
			}
			else 
				map[x][y] = dice[0];
			
			//주사위 위의 값 출력
			sb.append(dice[2]).append('\n');
			
		}
		
		System.out.println(sb);
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}
	
	static void rotating(int dir) {
		if(dir == 1) {
			//동쪽
			int tmp = dice[0];
			dice[0] = dice[5];
			dice[5] = dice[2];
			dice[2] = dice[4];
			dice[4] = tmp;
		}
		else if(dir == 2) {
			//서쪽
			int tmp = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[2];
			dice[2] = dice[5];
			dice[5] = tmp;
		}
		else if(dir == 3) {
			//북쪽
			int tmp = dice[3];
			dice[3] = dice[2];
			dice[2] = dice[1];
			dice[1] = dice[0];
			dice[0] = tmp;
		}
		else if(dir == 4) {
			//남쪽
			int tmp = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[3];
			dice[3] = tmp;
		}
	}
}