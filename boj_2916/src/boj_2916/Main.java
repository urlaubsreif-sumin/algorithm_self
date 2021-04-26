package boj_2916;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] angle;
	static boolean[] d;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
	
		try {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			angle = new int[N];
			int[] hw = new int[K];
			d = new boolean[361];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				angle[i] = Integer.parseInt(st.nextToken());
			}
			st= new StringTokenizer(br.readLine());
			for(int i = 0; i < K; i++) {
				hw[i] = Integer.parseInt(st.nextToken());
			}
			
			go(0);
			
			for(int i = 0; i < K; i++) {
				if(d[hw[i]]) {
					sb.append("YES\n");
				}
				else {
					sb.append("NO\n");
				}
			}
			
			System.out.println(sb.toString());
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void go(int a) {
		while(a > 360 || a < 0) {
			if(a < 0) a += 360;
			if(a > 360) a -= 360;
		}
		
		if(d[a]) return;
		else {
			d[a] = true;
		}
		
		for(int k = 0; k < N; k++) {
			go(a + angle[k]);
			go(a - angle[k]);
		}
		
	}

}
