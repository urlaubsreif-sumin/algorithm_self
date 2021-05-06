package boj_16973;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] rec;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			rec = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =0; j < M; j++) {
					rec[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int Sr = Integer.parseInt(st.nextToken()) - 1;
			int Sc = Integer.parseInt(st.nextToken()) - 1;
			int Fr = Integer.parseInt(st.nextToken()) - 1;
			int Fc = Integer.parseInt(st.nextToken()) - 1;
			
			int res = bfs(H, W, Sr, Sc, Fr, Fc);
			System.out.println(res);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int bfs(int h, int w, int sr, int sc, int fr, int fc) {
		Queue<Integer> queue = new LinkedList<>();
		int[][] d = new int[N][M];
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		int cur_i = sr;
		int cur_j = sc;
		d[cur_i][cur_j] = 1;
		queue.add(cur_i);
		queue.add(cur_j);
		while(!queue.isEmpty()) {
			cur_i = queue.remove();
			cur_j = queue.remove();
			if(cur_i == fr && cur_j == fc) {
				return d[cur_i][cur_j] - 1;
			}
			for(int k = 0; k < 4; k++) {
				int next_i = cur_i + dy[k];
				int next_j = cur_j + dx[k];
				if(!check(h, w, next_i, next_j, k)) continue;
				if(d[next_i][next_j] != 0) continue;
				queue.add(next_i);
				queue.add(next_j);
				d[next_i][next_j] = d[cur_i][cur_j] + 1;
			}
		}
		return -1;
		
	}
	
	public static boolean check(int h, int w, int sr, int sc, int dir) {
		if(sr < 0 || sc < 0) return false;
		if(sr + h > N || sc + w > M) return false;
		
		if(dir == 0) {
			for(int i = sr; i < sr + h; i++) {
				if(rec[i][sc] == 1) return false;
			}
		}
		else if(dir == 1) {
			for(int i = sr; i < sr + h; i++) {
				if(rec[i][sc + w - 1] == 1) return false;
			}
		}
		else if(dir == 2) {
			for(int j = sc; j < sc + w; j++) {
				if(rec[sr][j] == 1) return false;
			}
		}
		else if(dir == 3) {
			for(int j = sc; j < sc + w; j++) {
				if(rec[sr + h - 1][j] == 1) return false;
			}
		}
		
		return true;
	}

}
