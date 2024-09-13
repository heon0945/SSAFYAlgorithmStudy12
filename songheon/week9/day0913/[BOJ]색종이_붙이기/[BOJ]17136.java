import java.io.*;
import java.util.*;

public class Main {

	// 바로 큰 종이부터 붙이는 그리디적인 사고를 이용하되,
	// DFS와 백트래킹을 사용함으로써 완전탐색을 수행
	// 일단, nxn 범위 내에 모두 1이 존재한다면 nxn 색종이를 붙였다가 뗐다가 반복

	static int map[][];
	static int n;
	static int count[];
	static int answer;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = 10;
		map = new int[n][n];
		count = new int[5];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		answer = Integer.MAX_VALUE;
		
		dfs(0, 0);
		
		if(answer == Integer.MAX_VALUE)
			answer = -1;
		
		System.out.println(answer);

	}
	
	static void dfs(int pos, int cnt) {
		if(pos > 99) {
//			System.out.println(Arrays.toString(count));
			answer = Math.min(answer, cnt);
			return;
		}
		
		
		if(answer <= cnt) return;
		
		int x = pos / 10;
		int y = pos % 10;
		
		if(map[x][y] == 0) dfs(pos + 1, cnt);
		else {
			for(int k = 4; k >= 0; k--) {
				if(count[k] >= 5) continue;
				if(!isAvailable(x, y, k)) continue;
				
				sticker(x, y, k, 0);
				count[k]++;
				dfs(pos + 1, cnt + 1);
				// 원복
				sticker(x, y, k, 1);
				count[k]--;
			}
		}
		
		
	}
	

	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}

	static void sticker(int x, int y, int sz, int paper) {

		for (int i = 0; i <= sz; i++) {
			for (int j = 0; j <= sz; j++) {
				int tx = x + i;
				int ty = y + j;

				map[tx][ty] = paper;
			}
		}

	}

	static boolean isAvailable(int x, int y, int sz) {

		for (int i = 0; i <= sz; i++) {
			for (int j = 0; j <= sz; j++) {
				int tx = x + i;
				int ty = y + j;

				if (OOB(tx, ty))
					return false;
				if (map[tx][ty] == 0)
					return false;
			}
		}

		return true;

	}

}