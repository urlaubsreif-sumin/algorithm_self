package boj_2616;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int[] sum;
	static int w;
	static int[][] d;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			int n = Integer.parseInt(br.readLine());
			int[] train = new int[n];
			sum = new int[n];
			d = new int[n][4];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < n; i++) {
				train[i] = Integer.parseInt(st.nextToken());
				if(i != 0) {
					sum[i] = sum[i - 1] + train[i];
				}
				else {
					sum[0] = train[0];
				}
			}
			w = Integer.parseInt(br.readLine());
			
			d[w - 1][1] = sum[w - 1];
			d[2 * w - 1][2] = sum[2 * w - 1];
			d[3 * w - 1][3] = sum[3 * w - 1];
			
			int res = go(n - 1, 3);
			System.out.println(res);
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int go(int len, int k) {
		if(d[len][k]!= 0) {
			return d[len][k];
		}
		if(k == 0) return 0;
		d[len][k] = Math.max(go(len - 1, k), go(len - w, k - 1) + sum[len] - sum[len - w]);
		return d[len][k];
	}

}
