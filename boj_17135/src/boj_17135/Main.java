package boj_17135;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, D;
	static int[][] map;
	static int[][] init;
	static boolean[][] check;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			init = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					init[i][j] = map[i][j];
				}
			}
			
			int max = 0;
			
			for(int a = 0; a < M; a++) {
				for(int b = a + 1; b < M; b++) {
					for(int c = b + 1; c < M; c++) {
						int cnt = 0;
						//System.out.println("======================");
						for(int s = 1; s <= N; s++) {
							Point one = bfs(a, s);
							Point two = bfs(b, s);
							Point three = bfs(c, s);
							//System.out.println("<sec" + s + ">");
							//System.out.println(one.x + " " + one.y);
							//System.out.println(two.x + " " + two.y);
							//System.out.println(three.x + " " + three.y);
							if(one.x >= 0) {
								map[one.y][one.x] = 0;
								cnt++;
							}
							if(two.x >= 0 && map[two.y][two.x] != 0) {
								cnt++;
								map[two.y][two.x] = 0;
							}
							if(three.x >= 0 && map[three.y][three.x] != 0) {
								cnt++;
								map[three.y][three.x] = 0;
							}
						}
						//System.out.println(cnt);
						max = max > cnt ? max : cnt;
						initialize();
					}
				}
			}
			
			System.out.print(max);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	public static Point bfs(int pos, int s) {
		Queue<Point> queue = new LinkedList<>();
		int[][] visit = new int[N][M];
		int[] dx = {-1, 0, 1};
		int[] dy = {0, -1, 0};
		Point answer = new Point(-1, -1);
		int d = -1;
		
		Point start = new Point(pos, N - s);
		visit[start.y][start.x] = 1;
		if(map[start.y][start.x] == 1) {
			answer.x = start.x;
			answer.y = start.y;
			d = 1;
		}
		queue.add(start);
		
		while(!queue.isEmpty()) {
			Point cur = queue.remove();
			if((d != -1 && visit[cur.y][cur.x] >= d) || visit[cur.y][cur.x] >= D) {
				return answer;
			}
			for(int i = 0; i < 3; i++) {
				int next_x = cur.x + dx[i];
				int next_y = cur.y + dy[i];
				if(next_x < 0 || next_x >= M || next_y < 0 || next_y >= N)
					continue;
				Point next = new Point(next_x, next_y);
				if(visit[next.y][next.x] != 0) {
					continue;
				}
				if(map[next.y][next.x] == 1) {
					if(answer.x == -1 || answer.x > next.x) {
						answer.x = next.x;
						answer.y = next.y;
						d = visit[cur.y][cur.x] + 1;
					}
				}
				queue.add(new Point(next.x, next.y));
				visit[next.y][next.x] = visit[cur.y][cur.x] + 1;
			}
		}
		return answer;
	}
	
	public static void initialize() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = init[i][j];
			}
		}
	}

}

class Point {
	int x, y;
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

