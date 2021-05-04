package boj_15558;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, k;
	static char[][] a;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			a = new char[2][N];
			
			a[0] = br.readLine().toCharArray();
			a[1] = br.readLine().toCharArray();
			
			boolean res = bfs();
			System.out.println(res ? 1 : 0);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static boolean bfs() {
		Queue<Integer> queue = new LinkedList<>();
		int d[][] = new int[2][N];
		
		int cur_i = 0;
		int cur_j = 0;
		queue.add(cur_i);
		queue.add(cur_j);
		d[cur_i][cur_j] = 1;
		while(!queue.isEmpty()) {
			cur_i = queue.remove();
			cur_j = queue.remove();
			
			//1. 한 칸 앞으로 이동한다.
			int next_i = cur_i;
			int next_j = cur_j + 1;
			if(next_j >= N) return true;
			if(next_j >= d[cur_i][cur_j] && d[next_i][next_j] == 0 && a[next_i][next_j] == '1') {
				queue.add(next_i);
				queue.add(next_j);
				d[next_i][next_j] = d[cur_i][cur_j] + 1;
			}
			
			//2. 한 칸 뒤로 이동한다.
			next_i = cur_i;
			next_j = cur_j - 1;
			if(next_j >= d[cur_i][cur_j] && d[next_i][next_j] == 0 && a[next_i][next_j] == '1') {
				queue.add(next_i);
				queue.add(next_j);
				d[next_i][next_j] = d[cur_i][cur_j] + 1;
			}
			
			//3. 반대줄, k칸 앞으로 이동한다.
			next_i = (cur_i + 1) % 2;
			next_j = cur_j + k;
			if(next_j >= N) return true;
			if(next_j >= d[cur_i][cur_j] && d[next_i][next_j] == 0 && a[next_i][next_j] == '1') {
				queue.add(next_i);
				queue.add(next_j);
				d[next_i][next_j] = d[cur_i][cur_j] + 1;
			}
		}
		return false;
	}

}
