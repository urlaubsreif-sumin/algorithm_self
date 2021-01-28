package boj_14499;
import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		try {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][M];
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int K = Integer.parseInt(st.nextToken());
			int[] inst = new int[K];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			Dice d = new Dice(x, y, map);
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < K; i++) {
				d.roll(Integer.parseInt(st.nextToken()));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

class Dice {
	static int[] dice = new int[6];
	int up;
	int down;
	int x;
	int y;
	int[][] map;
	Dice(int x, int y, int[][] map){
		this.x = x;
		this.y = y;
		this.map = map;
	}
	public void roll(int inst) {
		switch(inst) {
		case 1:
			east();
			break;
		case 2:
			west();
			break;
		case 3:
			north();
			break;
		case 4:
			south();
			break;	
		}
		if(dice[2] == 0) {
			dice[2] = map[y][x];
		}
		
		System.out.println(dice[0] + " " + dice[1] + " " + dice[2] + " " + dice[3] + " " + dice[4] + " " + dice[5]);
	}
	
	public void east() {
		if(x + 1 < map[0].length) {
			x++;
			int temp = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[3];
			dice[3] = temp;
			System.out.println(dice[0] + " " + y + " " + x);
		}
	}
	
	public void west() {
		if(x - 1 >= 0) {
			x--;
			int temp = dice[1];
			dice[1] = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[2];
			dice[2] = temp;
			System.out.println(dice[0] + " " + y + " " + x);
		}
	}
	
	public void north() {
		if(y - 1 >= 0) {
			y--;
			int temp = dice[5];
			dice[5] = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[2];
			dice[2] = temp;
			System.out.println(dice[0] + " " + y + " " + x);
		}
	}
	
	public void south() {
		if(y + 1 < map.length) {
			y++;
			int temp = dice[0];
			dice[0] = dice[5];
			dice[5] = dice[2];
			dice[2] = dice[4];
			dice[4] = temp;
			System.out.println(dice[0] + " " + y + " " + x);
		}
	}
}