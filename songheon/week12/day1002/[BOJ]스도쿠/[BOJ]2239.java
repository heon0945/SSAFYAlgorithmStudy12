import java.util.*;
import java.io.*;

public class Main {
	
	static int map[][];
	static boolean ori [][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[9][9];
		ori = new boolean[9][9];
		
		for(int i = 0; i < 9; i++) {
			String tmp = br.readLine();
			for(int j = 0; j < 9; j++) {
				map[i][j] = tmp.charAt(j) - '0';
				if(map[i][j] != 0) ori[i][j] = true;
			}
		}
		
		//dfs 진행
		dfs(0);
	}
	
	static void dfs(int order) {
		//System.out.println(order);
		
		//스도쿠 완성
		if(order == 81) {
			//결과 출력
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
			System.out.println(sb);
			
			//프로그램 종료
			System.exit(0);
			
		}
		
		//현재 맵 상 좌표 구하기
		int x = order / 9;
		int y = order % 9;
		
		//고정 좌표인 경우
		if(ori[x][y] == true) {
			dfs(order + 1);
			return;
		}
		
		for(int i = 1; i <= 9; i++) {
			//값 검사
			if(!valueCheck(x, y, i)) continue;
			
			map[x][y] = i;
			dfs(order + 1);
			map[x][y] = 0;
		}
		return;
	}
	
	static boolean valueCheck(int x, int y, int i) {
		//행 검사
		for(int k = 0; k < 9; k++) {
			if(map[x][k] == i) { //이미 존재하는 경우
				return false;
			}
		}
		
		//열 검사
		for(int k = 0; k < 9; k++) {
			if(map[k][y] == i) { //이미 존재하는 경우
				return false;
			}
		}
		
		//영역 구하기
		int xscope = 3 * (x / 3);
		int yscope = 3 * (y / 3);
		
		//영역 검사
		for(int r = xscope; r < xscope + 3; r++) {
			for(int c = yscope; c < yscope + 3; c++) {
				if(map[r][c] == i) { //이미 존재하는 경우
					return false;
				}
			}
		}
		
		return true;
	}
}