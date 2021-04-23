package boj_1261;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				String input = br.readLine();
				for(int j = 0; j < M; j++) {
					map[i][j] = input.charAt(j) - '0';
				}
			}
			
			int res = bfs();
			System.out.println(res);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int bfs() {
		Deque<Pair> deque = new ArrayDeque<>();
		int[][] check = new int[N][M];
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		Pair cur = new Pair(0, 0);
		deque.add(cur);
		check[cur.y][cur.x] = 1;
		
		while(!deque.isEmpty()) {
			cur = deque.removeLast();
			if(cur.x == M - 1 && cur.y == N - 1) {
				break;
			}
			for(int i = 0; i < 4; i++) {
				Pair next = new Pair(cur.x + dx[i], cur.y + dy[i]);
				if(next.x < 0 || next.x >= M || next.y < 0 || next.y >= N) continue;
				if(check[next.y][next.x] != 0) continue;
				
				if(map[next.y][next.x] == 1) {
					deque.addFirst(next);
					check[next.y][next.x] = check[cur.y][cur.x] + 1; 
				}
				else if(map[next.y][next.x] == 0) {
					deque.addLast(next);
					check[next.y][next.x] = check[cur.y][cur.x]; 
				}
			}
		}
		
		return check[N - 1][M - 1] - 1;
		
	}

}

class Pair {
	int x, y;
	Pair(int x, int y){
		this.x = x;
		this.y = y;
	}
}
