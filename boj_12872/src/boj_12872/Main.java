package boj_12872;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
	final static int MOD = 1000000007;
	static int N, M, P;
	static int[][] d;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			
			d = new int[P + 1][N + 2];
			d[0][0] = 1;
			for(int p = 1; p <= P; p++) {
				for(int x = 1; x <= N; x++) {
					double tmp = ((double)d[p - 1][x - 1] * (double)(N - x + 1)) % MOD;
					if(x - M > 0) {
						tmp += ((double)d[p - 1][x] * (double)(x - M) % MOD);
					}
					
					d[p][x] = (int)(tmp % MOD);
				}
			}
			
			System.out.println(d[P][N]);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
}
