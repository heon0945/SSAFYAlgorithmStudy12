import java.io.*;
import java.util.*;

public class Main {
	
	static int h, n, m;
	static char[][] map;
	static int output[];
	static int answer;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		h = sc.nextInt();
		
		map = new char[h][n];
		
		//사다리 놓을 수 있는 칸 : T -> 전부 T로 초기화
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < n; j++) {
				map[i][j] = 'T';
			}
		}
		
		//사다리 타고 오른쪽 움직이는 칸 : R
		//사다리 타고 왼쪽 움직이는 칸 : L
		//사다리를 놓을 수 없는 칸 : F
		for(int i = 0; i < m; i++) {
			int r = sc.nextInt()-1;
			int c = sc.nextInt()-1;
			
			map[r][c] = 'R';
			if(!OOB(r, c-1) && map[r][c-1] == 'T') map[r][c-1] = 'F';
			if(!OOB(r, c+1)) map[r][c+1] = 'L';
		}
		
		
		for(int i = 0; i <= 3; i++) {
			//System.out.println(i + "개 뽑기");
			output = new int[i];
			//원하는 개수만큼 사다리 놓기
			putLadders(0, 0, i);
		}
		
		
		System.out.println(-1);
	}
	
	//2차원 배열의 조합으로 원하는 개수만큼 사다리 설치할 위치 구하기
	static void putLadders(int start, int order, int cnt) {
		if(order == cnt) { //원하는 개수의 사다리를 다 놓은 경우
			//System.out.println(Arrays.toString(output));
			
			//사다리 놓기
			char tmp[][] = new char[h][n];
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < n; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			
			for(int i = 0; i < cnt; i++) {
				int pos = output[i];
				int x = pos / n;
				int y = pos % n;
				tmp[x][y] = 'R';
				//미리 만들어진 사다리가 없는 경우에만 
				if(!OOB(x, y-1) && tmp[x][y-1] == 'T') tmp[x][y-1] = 'F';
				if(!OOB(x, y+1)) tmp[x][y+1] = 'L';
				
			}
			
			//사다리 타기
			if(gameStart(tmp)) {
				//전부 제자리인 경우 -> 성공
				System.out.println(cnt);
				//종료
				System.exit(0);
			}
			return;
		}
		
		for(int i = start; i < h * n; i++) {
			//사다리를 설치할 수 있는 칸만 선택
			int r = i / n;
			int c = i % n;
			//가장 마지막 세로 줄에는 사다리 설치 불가
			if(c == n-1) continue;
			//사다리 설치되어 있거나 연결되는 칸은 설치 불가
			if(map[r][c] != 'T') continue;
	
			output[order] = i;
			putLadders(i + 2, order + 1, cnt);
		}
	}
	
	static boolean gameStart(char map[][]) {
		
		//각 세로줄마다 시작
		for(int i = 0; i < n; i++) {
			int pos = i;
			//각 행을 내려감
			for(int j = 0; j < h; j++) {
				if(map[j][pos] == 'R') 
					pos++;
				else if(map[j][pos] == 'L')
					pos--;
			}
			
			//제자리가 아닌 경우
			if(pos != i) return false;
		}
		
		return true;
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= h || y < 0 || y >= n;
	}
}