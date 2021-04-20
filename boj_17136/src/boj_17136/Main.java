package boj_17136;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int[][] a;
	static int[] chk = {5, 5, 5, 5, 5};
	static int min = -1;
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		a = new int[10][10];
		try {
			for(int i = 0; i < 10; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 10; j++) {
					a[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			go(0, 0, 0);
			System.out.println(min);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void go(int i, int j, int sz) {
		//System.out.println(i + " " + j + " " + sz);
		int r = i + (j + sz) / 10;
		int c = (j + sz) % 10;
		while(r < 10 && c < 10 && a[r][c] == 0) {
			r = r + (c + 1) / 10;
			c = (c + 1) % 10;
		}
		//System.out.println(r + " " + c);
		if(r < 10 && c < 10) {
			if(chk[0] - 1 >= 0) {
				chk[0]--;
				a[r][c] = 0;
				go(r, c, 1);
				a[r][c] = 1;
				chk[0]++;
			}
		}
		else {
			int total = 25;
			for(int k = 0; k < 5; k++) {
				total -= chk[k];
			}
			if(min == -1 || total < min) {
				min = total;
			}
			return;
		}
		boolean ok = true;
		for(int n = 1; n < 5; n++) {
			if(r + n >= 10 || c + n >= 10) {
				ok = false;
			}
			else if(a[r][c + n] == 1 && a[r + n][c] == 1) {
				for(int m = 0; m <= n; m++) {
					if(a[r + n][c + m] != 1) {
						ok = false;
					}
					if(a[r + m][c + n] != 1) {
						ok = false;
					}
					if(!ok) break;
				}
			}
			else {
				ok = false;
			}
			if(!ok) break;
			if(chk[n] - 1 >= 0) {
				chk[n]--;
				for(int p = r; p <= r + n; p++) {
					for(int q = c; q <= c + n; q++) {
						a[p][q] = 0;
					}
				}
				go(r, c, n + 1);
				for(int p = r; p <= r + n; p++) {
					for(int q = c; q <= c + n; q++) {
						a[p][q] = 1;
					}
				}
				chk[n]++;
			}
		}
		return;
	}

}
