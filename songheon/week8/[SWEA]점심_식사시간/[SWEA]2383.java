import java.util.*;
import java.io.*;

public class Solution {

	static int tc;
	static int n;
	static int map[][];
	static int answer;
	static ArrayList<Pair> person; //사람들 위치
	static Pair s1, s2; //계단 위치
	static boolean inStr1[]; //계단 선택 정보
	static PriorityQueue<Pair> moving1, moving2; //이동 큐 
	
	static class Pair{
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		tc = sc.nextInt();
		for(int t = 1; t <= tc; t++) {
			sb.append("#").append(t).append(" ");
			
			answer = 1000;
			n = sc.nextInt();
			map = new int[n][n];
			person = new ArrayList<>();
			s1 = null; s2 = null;
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					map[i][j] = sc.nextInt();
					if(map[i][j] == 1) {
						person.add(new Pair(i, j));
					}
					else if(map[i][j] > 1) {
						if(s1 == null) s1 = new Pair(i, j);
						else s2 = new Pair(i, j);
					}
				}
			}
			
			//사람들의 계단 선택
			inStr1 = new boolean[person.size()];
			selectStairs(0);
			
			sb.append(answer).append('\n');
		}
		
		System.out.print(sb);
	}
	
	static int calc(PriorityQueue<Pair> pq, int k) {
		
//		System.out.println("calc " + k);
		int time = 0;
		
		Queue<Pair> q = new ArrayDeque<>();
		while(!pq.isEmpty() || !q.isEmpty()) {
//			System.out.println("time" + time);
			
			while(!q.isEmpty()) {
				if(q.peek().y <= time) { 
//					System.out.println("q poll " + q.peek().x);
					q.poll();
				}
				else break;
			}
			while(!pq.isEmpty() && q.size() < 3) {
				if(pq.peek().y < time) {
					Pair cur = pq.poll();
					q.add(new Pair(cur.x, time + k));
//					System.out.println("q add " + cur.x);
				}
				else
					break;
			}
			
			
			if(pq.isEmpty() && q.isEmpty()) break;
			time++;
		}
		
		return time;
	}
	
	static void selectStairs(int order) {
		if(order == person.size()) {
			//모든 사람들 선택 종료
			moving1 = new PriorityQueue<>((e1, e2)-> e1.y-e2.y); //인덱스, 이동 거리
			moving2 = new PriorityQueue<>((e1, e2)-> e1.y-e2.y);
			for(int i = 0; i < person.size(); i++) {
				Pair cur = person.get(i);
				int dist = 0;
				//계단 1 선택한 사람들
				if(inStr1[i]) {
//					System.out.println("str1 : " + i);
					dist = Math.abs(s1.x - cur.x) + Math.abs(s1.y - cur.y);
					moving1.add(new Pair(i, dist));
				}
				//계단 2 선택한 사람들
				else {
//					System.out.println("str2 : " + i);
					dist = Math.abs(s2.x - cur.x) + Math.abs(s2.y - cur.y);
					moving2.add(new Pair(i, dist));
				}	
			}
			
			int m1 = calc(moving1, map[s1.x][s1.y]); int m2 = calc(moving2, map[s2.x][s2.y]);
//			System.out.println("moving1 " + m1);
//			System.out.println("moving2 " + m2);
			int total = Math.max(m1, m2);
			answer = Math.min(total, answer);
			return;
		}
		
		//계단 1선택
		inStr1[order] = true;
		selectStairs(order + 1);
		
		//계단 1선택 x (2선택)
		inStr1[order] = false;
		selectStairs(order + 1);
	}
	
}