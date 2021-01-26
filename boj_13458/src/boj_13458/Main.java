package boj_13458;
import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		try {
			int N = Integer.parseInt(br.readLine());
			int[] room = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				room[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			int main = N;
			long sub = 0; //자료형 조심ㅠㅠ
			//시험장의 수가 최대 1000000개, 각 시험장 응시자 수의 최대 1000000명
			//감독관이 감독할 수 있는 학생 최소 수 1명
			//--> int형 초과
			
			for(int i = 0; i < N; i++) {
				int k = room[i] - B;
				if(k > 0) {
					sub += (long)((k + C - 1) / C);
				}
			}
			
			System.out.println(main + sub);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
