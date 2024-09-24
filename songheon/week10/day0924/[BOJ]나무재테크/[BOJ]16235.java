import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m, k;
	static int A[][];
	static int map[][];
	static ArrayList<Tree> trees;
	static int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static class Tree implements Comparable<Tree>{
		int x, y;
		int age;
		boolean active = true;
		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}
		@Override
		public int compareTo(Tree o) {
			return this.age-o.age;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		
		A = new int[n][n];
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				A[i][j] = sc.nextInt();
				map[i][j] = 5;
			}
		}
		
		trees = new ArrayList<>();
		for(int i = 0; i < m; i++) {
			trees.add(new Tree(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt()));
		}
		
		Collections.sort(trees);
		
		System.out.println(gameStart());
	}
	
	static boolean OOB(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}
	
	
	static int gameStart() {
		int year = 0;
		while(true) {
			if(year >= k) break;
			year++;
			
			//봄
			//나이만큼 양분, 나이 먹음
			for(int i = 0; i < trees.size(); i++) {
				Tree cur = trees.get(i);
				int x = cur.x;
				int y = cur.y;
				
				
				if(map[x][y] >= cur.age) {
					map[x][y] -= cur.age;
					cur.age++;
				}
				else
					cur.active = false;
			}
			
			
			//여름
			//봄에 죽은 나무가 양분 됨
			ArrayList<Tree> tmp = new ArrayList<>();
			//for(int i = trees.size()-1; i >= 0; i--) {
			for(int i = 0; i < trees.size(); i++) {
				Tree cur = trees.get(i);
				int x = cur.x;
				int y = cur.y;
				
				if(!cur.active) {
					map[x][y] += (cur.age/2);
					//trees.remove(i);
				}
				else {
					tmp.add(cur);
				}
			}
			trees = tmp;
			
			//가을
			//번식하기
			for(int i = 0; i < trees.size(); i++) {
				Tree cur = trees.get(i);
				int x = cur.x;
				int y = cur.y;
				
				if(cur.age % 5 != 0) continue;
				
				for(int j = 0; j < 8; j++) {
					int tx = x + dx[j];
					int ty = y + dy[j];
					
					if(OOB(tx, ty)) continue;
					trees.add(new Tree(tx, ty, 1));
				}
			}
			
			Collections.sort(trees);
			
			//겨울
			//땅에 양분 추가
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					map[i][j] += A[i][j];
				}
			}
		}
		
		
		return trees.size();
	}
}