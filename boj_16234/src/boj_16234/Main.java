package boj_16234;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R;
	static int[][] A;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] d;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			A = new int[N][N];
			d = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					A[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans = go();
			System.out.println(ans);
				
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int go() {
		boolean ok = true;
		int cnt = 0;
		while(ok) {
			ok = false;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(d[i][j] == 0 && compare(i, j)) {
						bfs(i, j);
						ok = true;
					}
				}
			}
			if(ok) cnt++;
			d = new int[N][N];
		}
		return cnt;
	}
	
	public static boolean compare(int i, int j) {
		for(int k = 0; k < 4; k++) {
			int next_i = i + dy[k];
			int next_j = j + dx[k];
			if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= N) continue;
			int diff = Math.abs(A[next_i][next_j] - A[i][j]);
			if(diff >= L && diff <= R) {
				return true;
			}
		}
		return false;
	}
	
	public static void bfs(int si, int sj) {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		//1. 인구이동할 땅 찾기
		int cnt = 1;
		int sum = A[si][sj];
		int cur_i = si;
		int cur_j = sj;
		d[si][sj] = 1;
		queue.add(si);
		queue.add(sj);
		while(!queue.isEmpty()) {
			cur_i = queue.remove();
			cur_j = queue.remove();
			for(int k = 0; k < 4; k++) {
				int next_i = cur_i + dy[k];
				int next_j = cur_j + dx[k];
				if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= N) continue;
				if(d[next_i][next_j] != 0) continue;
				int diff = Math.abs(A[next_i][next_j] - A[cur_i][cur_j]);
				if(diff < L || diff > R) continue;
				
				d[next_i][next_j] = 1;
				queue.add(next_i);
				queue.add(next_j);
				cnt++;
				sum += A[next_i][next_j];
			}
		}
		queue.clear();
		
		//2. 인구이동
		int num = sum / cnt;
		cur_i = si;
		cur_j = sj;
		queue.add(cur_i);
		queue.add(cur_j);
		A[cur_i][cur_j] = num;
		d[cur_i][cur_j] = -1;
		while(!queue.isEmpty()) {
			cur_i = queue.remove();
			cur_j = queue.remove();
			for(int k = 0; k < 4; k++) {
				int next_i = cur_i + dy[k];
				int next_j = cur_j + dx[k];
				if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= N) continue;
				if(d[next_i][next_j] != 1) continue;
				
				queue.add(next_i);
				queue.add(next_j);
				A[next_i][next_j] = num;
				d[next_i][next_j] = -1;
			}
		}
	}

}
