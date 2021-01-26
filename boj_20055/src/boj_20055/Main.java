package boj_20055;
import java.io.*;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] a = new int[2 * N];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 2 * N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			
			int cur = 0;
			int stage = 1;
			
			while(K > 0) {
				if(a[cur] == 0) {
					cur++;
					cur %= (2 * N);
					continue;
				}
				a[cur]--;
				if(a[cur] == 0) {
					K--;
				}
				cur = (cur + 4) % (2 * N);
				stage++;
			}
			
			System.out.println(stage - 1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
