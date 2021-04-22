package boj_17472;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] bridge;
	static int[][] island;
	static int[][] map;
	static int N, M;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			island = new int[N][M];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int islands = get_island();
			
			bridge = new int[islands][islands];
			
			find_bridge(islands);
			
			int len = connect(islands);
			
			
			System.out.println(len);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int get_island() {
		int cnt = 1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 1 && island[i][j] == 0) {
					island_bfs(i, j, cnt);
					cnt++;
				}
			}
		}
		return cnt - 1;
	}
	
	public static void island_bfs(int start_i, int start_j, int n) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int cur_i = start_i;
		int cur_j = start_j;
		island[cur_i][cur_j] = n;
		queue.add(cur_i);
		queue.add(cur_j);
		
		while(!queue.isEmpty()) {
			cur_i = queue.remove();
			cur_j = queue.remove();
			for(int i = 0; i < 4; i++) {
				int next_i = cur_i + dy[i];
				int next_j = cur_j + dx[i];
				if(next_i >= N || next_i < 0 || next_j >= M || next_j < 0) continue;
				if(island[next_i][next_j] != 0) continue;
				if(map[next_i][next_j] == 1) {
					island[next_i][next_j] = n;
					queue.add(next_i);
					queue.add(next_j);
				}
			}
			
		}
		
	}
	
	public static void find_bridge(int islands) {
		boolean[] check = new boolean[islands];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 1 && !check[island[i][j] - 1]) {
					bridge_bfs(i, j, island[i][j], 0);
					bridge_bfs(i, j, island[i][j], 1);
					check[island[i][j] - 1] = true;
				}
			}
		}
	}
	
	public static void bridge_bfs(int start_i, int start_j, int n, int dir) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		int[][] check = new int[N][M];
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		for(int i = start_i; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(island[i][j] == n) {
					queue.add(i);
					queue.add(j);
					check[i][j] = 1;
				}
			}
		}
		
		
		while(!queue.isEmpty()) {
			int cur_i = queue.remove();
			int cur_j = queue.remove();
			for(int i = 0; i < 4; i++) {
				if(dir == 0 && (i == 2 || i == 3)) continue;
				else if(dir == 1 && (i == 0 || i == 1)) continue;
				
				int next_i = cur_i + dy[i];
				int next_j = cur_j + dx[i];
				if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= M) continue;
				if(check[next_i][next_j] != 0) continue;
				if(island[next_i][next_j] == n) continue;
				
				check[next_i][next_j] = check[cur_i][cur_j] + 1;
				
				if(map[next_i][next_j] == 1) {
					if(check[next_i][next_j] - 2 <= 1) continue;
					if(bridge[n - 1][island[next_i][next_j] - 1] == 0
						|| bridge[n - 1][island[next_i][next_j] - 1] > check[next_i][next_j] - 2) {
						bridge[n - 1][island[next_i][next_j] - 1] = check[next_i][next_j] - 2;
						bridge[island[next_i][next_j] - 1][n - 1] = check[next_i][next_j] - 2;
					}
					continue;
				}
				queue.add(next_i);
				queue.add(next_j);
			}
		}
		
		
	}
	
	public static int connect(int islands) {
		boolean[] c = new boolean[islands];
		int length = 0;
		ArrayList<Integer> nodes = new ArrayList<>();
		nodes.add(0);
		c[0] = true;
		
		for(int bridges = 0; bridges < islands - 1; bridges++) {
			int min = Math.max(N, M);
			int next = -1;
			for(int n : nodes) {
				for(int j = 0; j < islands; j++) {
					if(!c[j] && bridge[n][j] != 0) {
						if(min > bridge[n][j]) {
							min = bridge[n][j];
							next = j;
						}
					}
				}
			}
			if(next != -1) {
				length += min;
				nodes.add(next);
				c[next] = true;
				//System.out.println(next + " " + min);
			}
			else {
				return -1;
			}
		}
		return length;
	}
	

}
