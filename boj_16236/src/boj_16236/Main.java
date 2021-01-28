package boj_16236;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] sea;
	static Shark shark;
	static int[] dx = {0, -1, 1, 0};
	static int[] dy = {-1, 0, 0, 1};
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			//입력
			N = Integer.parseInt(br.readLine());
			sea = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					int k = Integer.parseInt(st.nextToken());
					if(k == 9) {
						shark = new Shark(i, j);
					}
					else {
						sea[i][j] = k;
					}
				}
			}
			
			int time = 0;
			int t = 0;
			while((t = go(shark.i, shark.j)) != 0) {
				time += t;
			}
			
			System.out.println(time);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int go(int si, int sj) {
		Queue<Integer> queue = new LinkedList<Integer>();
		Fish target = new Fish(N, N, 0, N * N);
		int[][] check = new int[N][N];
		//현재 위치 queue 추가
		int cur_i = si;
		int cur_j = sj;
		check[cur_i][cur_j] = 1;
		queue.add(cur_i);
		queue.add(cur_j);
		//bfs시작
		while(!queue.isEmpty()) {
			cur_i = queue.remove();
			cur_j = queue.remove();
			//찾아두었던 물고기보다 먼 경우 -> 그만둔다.
			if(check[cur_i][cur_j] > target.d)
				continue;
			//먹을 수 있는 물고기를 찾은 경우
			if(sea[cur_i][cur_j] != 0 && shark.size > sea[cur_i][cur_j]) {
				if(cur_i < target.i) { //위에 있으면 목표물로 지정한다.
					target = new Fish(cur_i, cur_j, sea[cur_i][cur_j], check[cur_i][cur_j]);
				}
				else if(cur_i == target.i && cur_j < target.j) { //똑같이 위에 있을 때, 왼쪽에 있으면 목표물로 지정한다.
					target = new Fish(cur_i, cur_j, sea[cur_i][cur_j], check[cur_i][cur_j]);
				}
				continue;
			}
			//물고기를 찾지 못한 경우 다음 칸 이동
			for(int i = 0; i < 4; i++) {
				int next_i = cur_i + dy[i];
				int next_j = cur_j + dx[i];
				//범위를 넘어선 경우 -> 그만둔다.
				if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= N)
					continue;
				//이미 왔던 곳인 경우 -> 그만둔다.
				if(check[next_i][next_j] != 0)
					continue;
				//상어의 크기보다 물고기가 큰 경우 -> 지나갈 수 없다.
				if(sea[next_i][next_j] > shark.size)
					continue;
				//다음 위치 queue 추가
				queue.add(next_i);
				queue.add(next_j);
				//몇 칸 지나왔는지 check배열에 저장
				check[next_i][next_j] = check[cur_i][cur_j] + 1;
			}
		}
		//i가 N인 경우는 초기 값이 그대로 남아있는 경우 뿐이다 -> 먹을 수 있는 물고기가 없다.
		if(target.i == N) {
			return 0;
		}
		//목표물로 지정된 물고기를 먹는다.
		shark.eat(target.size);
		shark.move(target.i, target.j);
		sea[target.i][target.j] = 0;
		//걸린 시간
		return target.d - 1;
	}
}

class Fish {
	int i;
	int j;
	int size;
	int d;
	Fish(int i, int j, int size, int d){
		this.i = i;
		this.j = j;
		this.size = size;
		this.d = d; //상어로부터의 거리
	}
}

class Shark {
	int i;
	int j;
	int size = 2;
	int cnt = 0;
	Shark(int i, int j){
		this.i = i;
		this.j = j;
	}
	public void eat(int s) {
		cnt++;
		if(cnt == size) {
			size++;
			cnt = 0;
		}
	}
	public void move(int i, int j) {
		this.i = i;
		this.j = j;
	}
}