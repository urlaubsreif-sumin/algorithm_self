package boj_17471;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int total = 0;
	static int[] ppl;
	static int min = -1;
	static boolean[] check;
	static ArrayList<Integer>[] map;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			N = Integer.parseInt(br.readLine());
			ppl = new int[N];
			check = new boolean[N];
			map = (ArrayList[]) new ArrayList[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				map[i] = new ArrayList<Integer>();
				ppl[i] = Integer.parseInt(st.nextToken());
				total += ppl[i];
			}
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				if(n == 0) continue;
				for(int j = 0; j < n; j++) {
					int k = Integer.parseInt(st.nextToken()) - 1;
					map[i].add(k);
				}
			}
			
			check[0] = true;
			go();
			
			System.out.println(min);
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void go() {
		for(int i = 1; i < (1 << N); i++) {
			check = new boolean[N];
			int p = 0;
			for(int j = 0; j < N; j++) {
				if((i & (1 << j)) != 0) {
					check[j] = true;
					p += ppl[j];
				}
				if(bfs1() && bfs2()) {
					if(min == -1 || Math.abs(total - 2 * p) < min) {
						min = Math.abs(total - 2 * p);
					}
				}
			}
		}
	}
	
	public static boolean bfs1() {
		int start = -1;
		for(int i = 0; i < N; i++) {
			if(check[i]) {
				start = i;
				break;
			}
		}
		if(start == -1) {
			return false;
		}
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];
		
		queue.add(start);
		visited[start] = true;
		while(!queue.isEmpty()) {
			int cur = queue.remove();
			if(map[cur].size() == 0) continue;
			for(int i = 0; i < map[cur].size(); i++) {
				int next = map[cur].get(i);
				if(!check[next]) continue;
				if(visited[next]) continue;
				visited[next] = true;
				queue.add(next);
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i] && check[i]) {
				return false;
			}
		}
		return true;
		
	}
	
	public static boolean bfs2() {
		int start = -1;
		for(int i = 0; i < N; i++) {
			if(!check[i]) {
				start = i;
				break;
			}
		}
		
		if(start == -1) {
			return false;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N];
		
		queue.add(start);
		visited[start] = true;
		while(!queue.isEmpty()) {
			int cur = queue.remove();
			if(map[cur].size() == 0) continue;
			for(int i = 0; i < map[cur].size(); i++) {
				int next = map[cur].get(i);
				if(check[next]) continue;
				if(visited[next]) continue;
				visited[next] = true;
				queue.add(next);
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i] && !check[i]) {
				return false;
			}
		}
		return true;
	}

}