package prg_17678.src;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        Solution s = new Solution();
        String[] timetable = {"08:00", "08:01", "08:02", "08:03"};
        s.solution(1, 1, 5, timetable);
    }
}

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int start = 9 * 60;
        int last = start + (n - 1) * t;

        int[] data = new int[timetable.length];
        for(int i = 0; i < timetable.length; i++){
            data[i] = strToInt(timetable[i]);
        }

        Arrays.sort(data);
        
        int time = 9 * 60;
        int p = 0;
        for(int bus = 0; bus < n; bus++){
            int cnt = 0;
            while(cnt < m && p < data.length && data[p] <= time){
                cnt++;
                p++;
            }
            if(bus == n - 1){
                if(cnt < m) {
                    answer = intToStr(last);
                }
                else {
                    answer = intToStr(data[p - 1] - 1);
                }
            }
            time += t;
        }
        return answer;
    }

    public int strToInt(String t){
        StringTokenizer st = new StringTokenizer(t, ":");
        int ans = Integer.parseInt(st.nextToken()) * 60;
        ans += Integer.parseInt(st.nextToken());
        return ans;
    }

    public String intToStr(int t){
        String ans = "";
        int h = (t / 60);
        if(h < 10) ans += "0";
        ans += h;
        ans += ":";
        int m = (t % 60);
        if(m < 10) ans += "0";
        ans += m;
        return ans;
    }
}