package boj_1525;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int a;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		try {
			for(int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 3; j++) {
					a *= 10;
					int n = Integer.parseInt(st.nextToken());
					if(n == 0) {
						a += 9;
					}
					else {
						a += n;	
					}
				}
			}
			
			int res = bfs();
			System.out.println(res);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int bfs() {
		Queue<Integer> queue = new LinkedList<Integer>();
		HashMap<Integer, Integer> hm = new HashMap<>();
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int cur = a;
		hm.put(cur, 1);
		queue.add(cur);
		while(!queue.isEmpty()) {
			cur = queue.remove();
			//System.out.println(cur);
			if(cur == 123456789) {
				return hm.get(cur) - 1;
			}
			String curstr = String.valueOf(cur);
			int idx = curstr.indexOf('9');
			int cur_i = idx / 3;
			int cur_j = idx % 3;
			for(int i = 0; i < 4; i++) {
				int next_i = cur_i + dy[i];
				int next_j = cur_j + dx[i];
				if(next_i < 0 || next_i >= 3 || next_j < 0 || next_j >= 3) continue;
				char[] nextstr = String.valueOf(curstr).toCharArray();
				int from = cur_i * 3 + cur_j;
				int to = next_i * 3 + next_j;
				//System.out.println(from + " " + to);
				char temp = nextstr[from];
				nextstr[from] = nextstr[to];
				nextstr[to] = temp;
				int next = Integer.parseInt(String.valueOf(nextstr));
				if(hm.containsKey(next)) continue;
				
				queue.add(next);
				hm.put(next, hm.get(cur) + 1);
			}
		}
		return -1;
	}

}
