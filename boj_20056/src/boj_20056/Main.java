package boj_20056;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); //���� ���� ���� ũ��
			int M = Integer.parseInt(st.nextToken()); //���̾ ����
			int K = Integer.parseInt(st.nextToken()); //���� Ƚ��
			
			Fireball[] fb = new Fireball[M];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken()); //r
				int c = Integer.parseInt(st.nextToken()); //c
				int m = Integer.parseInt(st.nextToken()); //m
				int s = Integer.parseInt(st.nextToken()); //s
				int d = Integer.parseInt(st.nextToken()); //d				
			}
			
			while(K --> 0) {
				
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static Fireball move(Fireball f) {
		int s = f.s;
		int d = f.d;
		
		f.c = f.c + dx[f.s] * f.d;
		f.r = f.r + dy[f.s] * f.d;
		
		return f;
	}

}

class Group {
	ArrayList<Fireball> g;
	Group(){
		g = new ArrayList<Fireball>();
	}
	public void add(Fireball f){
		g.add(f);
	}
	public int size() {
		return g.size();
	}
}

class Fireball {
	int r, c, m, s, d;
	Fireball(int r, int c, int m, int s, int d) {
		this.r = r;
		this.c = c;
		this.m = m;
		this.c = c;
		this.d = d;
	}
}
