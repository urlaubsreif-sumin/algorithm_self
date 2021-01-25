package boj_14888;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] num;
	static int max = -1000000000;
	static int min = 1000000000;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			N = Integer.parseInt(br.readLine());
			num = new int[N];
			int[] op = new int[4];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			
			go(op, 1, num[0]);
			System.out.println(max);
			System.out.println(min);
			
			
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	public static void go(int[] op, int idx, int result) {
		if(idx == N) {
			if(result < min) {
				min = result;
			}
			if(result > max) {
				max = result;
			}
			return;
		}
		int n = num[idx];
		for(int i = 0; i < 4; i++) {
			if(op[i] != 0) {
				op[i]--;
				go(op, idx + 1, calc(i, result, n));
				op[i]++;
			}
		}
	}
	
	public static int calc(int op, int a, int b) {
		switch(op) {
		case 0:
			return a + b;
		case 1:
			return a - b;
		case 2:
			return a * b;
		case 3:
			if(a < 0) {
				return -((-a) / b);
			}
			return a / b;
		}
		return 0;
	}

}
