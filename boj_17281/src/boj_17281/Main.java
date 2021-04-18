package boj_17281;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] info;
	static int[] order;
	static int cur = 0;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			N = Integer.parseInt(br.readLine());
			info = new int[N][9];
			order = new int[9];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 9; j++) {
					info[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < 9; i++) {
				order[i] = i;
			}
			
			int max = 0;
			
			while(next_permutation()) {
				
				if(order[3] != 0) continue;
				cur = 0;
				int point = 0;
				for(int i = 0; i < N; i++) {
					point += go(i, 0, 0, 0, 0);
				}
				max = max > point ? max : point;
			}
			
			System.out.println(max);	
			
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int go(int i, int one, int two, int three, int out) {
		int score = 0;
		switch(info[i][order[cur]]) {
		case 0:
			out++;
			break;
		case 1:
			if(three == 1) {
				score++;
				three = 0;
			}
			if(two == 1) {
				three = 1;
				two = 0;
			}
			if(one == 1) {
				two = 1;
				one = 0;
			}
			one = 1;
			break;
		case 2:
			if(three == 1) {
				score++;
				three = 0;
			}
			if(two == 1) {
				score++;
				two = 0;
			}
			if(one == 1) {
				three = 1;
				one = 0;
			}
			two = 1;
			break;
		case 3:
			if(three == 1) {
				score++;
				three = 0;
			}
			if(two == 1) {
				score++;
				two = 0;
			}
			if(one == 1) {
				score++;
				one = 0;
			}
			three = 1;
			break;
		case 4:
			if(three == 1) {
				score++;
				three = 0;
			}
			if(two == 1) {
				score++;
				two = 0;
			}
			if(one == 1) {
				score++;
				one = 0;
			}
			score++;
			break;
		}
		cur = (cur + 1) % 9;
		if(out == 3) {
			return 0;
		}
		else {
			return go(i, one, two, three, out) + score;
		}
			
	}
	
	public static boolean next_permutation() {
		int idx = 8;
		while(order[idx] < order[idx - 1]) {
			idx--;
			if(idx - 1 < 0) {
				return false;
			}
		}
		idx--;
		
		for(int i = 8; i > idx; i--) {
			if(order[i] > order[idx]) {
				int temp = order[i];
				order[i] = order[idx];
				order[idx] = temp;
				break;
			}
		}
		
		int s = idx + 1;
		int e = 8;
		for(int i = s; i < (s + e + 1) / 2; i++) {
			int temp = order[i];
			order[i] = order[e + s - i];
			order[e + s - i] = temp;
		}
		
		
		return true;
	}

}
