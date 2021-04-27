package boj_10564;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] score;
	static boolean[][] d;
	static int max = 0;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		try {
			int tc = Integer.parseInt(br.readLine());
			while(tc --> 0) {
				st = new StringTokenizer(br.readLine());
				int N = Integer.parseInt(st.nextToken());
				d = new boolean[N + 1][N + 1];
				
				M = Integer.parseInt(st.nextToken());
				
				score = new int[M];
				int min = 0, max = 0;
				st = new StringTokenizer(br.readLine());
				for(int i = 0; i < M; i++) {
					score[i] = Integer.parseInt(st.nextToken());
				}
				
				d[0][0] = true;
				int ans = -1;
				for(int i = 0; i <= N; i++) {
					for(int j = 0; j <= i; j++) {
						if(d[i][j]) {
							for(int k = 0; k < M; k++) {
								if(i + j + score[k] <= N) {
									d[i + j + score[k]][j + score[k]] = true;
									if(i + j + score[k] == N) {
										ans = ans > j + score[k] ? ans : j + score[k];
									}
								}
							}
						}
					}
				}
				
				sb.append(ans).append('\n');
			}
			
			System.out.println(sb.toString());
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		

	}
	
	

}
