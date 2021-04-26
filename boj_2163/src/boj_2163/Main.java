package boj_2163;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int[][] d;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			d = new int[N + 1][M + 1];
			
			int res = go(N, M);
			System.out.println(res);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int go(int h, int w) {
		if(d[h][w] != 0) return d[h][w];
		if(h == 1 && w == 1) return 0;
		int min = Integer.MAX_VALUE;
		
		for(int k = 1; k < h; k++) {
			min = Math.min(min, go(h - k, w) + go(k, w) + 1);
		}
		
		for(int k = 1; k < w; k++) {
			min = Math.min(min, go(h, w - k) + go(h, k) + 1);
		}
		
		d[h][w] = min;
		return d[h][w];
	}

}
