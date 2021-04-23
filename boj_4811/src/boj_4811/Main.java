package boj_4811;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		long[][] d = new long[31][31];
		
		d[1][0] = 1;
		d[1][1] = 1;
		
		for(int w = 2; w < 31; w++) {
			for(int h = 0; h <= w; h++) {
				d[w][h] = d[w - 1][h];
				if(h - 1 >= 0) {
					d[w][h] += d[w][h - 1];
				}
			}
		}
		
		
		try {
			int med;
			while((med = Integer.parseInt(br.readLine())) != 0) {
				sb.append(d[med][med]).append('\n');
				
			}
			
			System.out.println(sb.toString());
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}

}
