package boj_3954;
import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int sm, sc, si;
	static int[] arr, pair;
	static char[] program, input;
	static int pos;
	static int sp;
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		try {
			int tc = Integer.parseInt(br.readLine());
			
			while(tc --> 0) {
				st = new StringTokenizer(br.readLine());
				sm = Integer.parseInt(st.nextToken());
				sc = Integer.parseInt(st.nextToken());
				si = Integer.parseInt(st.nextToken());
				
				arr = new int[sm];
				program = br.readLine().toCharArray();
				input = br.readLine().toCharArray();
				pos = 0;
				sp = 0; //input position
				
				pair = find_pair();
				
				int s = 0;
				int i = 0;
				int max = 0;
				boolean fin = false;
				while(s < 50000001) {
					i = calc(i);
					if(i >= sc) {
						fin = true;
						break;
					}
					s++;
				}
				
				if(fin) {
					sb.append("Terminates").append('\n');
				}
				else {
					s = 0;
					while(s < 50000001) {
						i = calc(i);
						max =  max > i ? max : i;
						s++;
					}
					sb.append("Loops ").append(pair[max]).append(" ").append(max).append('\n'); //구간은 어케 구하냐?
				}
				
			}
			
			System.out.print(sb.toString());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int calc(int i) {
		int n = i;
		switch(program[i]) {
		case '+':
			arr[pos] = (int) ((arr[pos] + 1) % 256);
			break;
		case '-':
			arr[pos] = (int) ((arr[pos] - 1) % 256);
			break;
		case '<':
			if(pos == 0) pos = sm - 1;
			else pos--;
			break;
		case '>':
			if(pos == sm - 1) pos = 0;
			else pos++;
			break;
		case '[':
			if(arr[pos] == 0) {
				n = pair[i];
			}
			break;
		case ']':
			if(arr[pos] != 0) {
				n = pair[i];
			}
			break;
		case '.':
			break;
		case ',':
			if(sp >= si) {
				arr[pos] = 255;
			}
			else {
				arr[pos] = input[sp++];
			}
			break;
		}
		return n + 1;
	}
	
	public static int[] find_pair() {
		Stack<Integer> stack = new Stack<>();
		int[] res = new int[sc];
		for(int i = 0; i < sc; i++) {
			if(program[i] == '[') {
				stack.add(i);
			}
			else if(program[i] == ']') {
				int a = stack.pop();
				res[i] = a;
				res[a] = i;
			}
		}
		
		return res;
	}

}
