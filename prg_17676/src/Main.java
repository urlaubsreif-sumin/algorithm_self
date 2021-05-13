package prg_17676.src;

import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args){
        Log log = new Log("2016-09-15 01:00:04.002 2.0s");
        System.out.println(log.start);
        System.out.println(log.end);
        System.out.println(log.dur);
    }
}

class Solution {
    public int solution(String[] lines) {
        int max = 1;
        for(int i = 0; i < lines.length; i++){
            Log s = new Log(lines[i]);
            double section_start = s.end;
            double section_end = section_start + 0.999;
            int cnt = 1;
            for(int j = i + 1; j < lines.length; j++){
                Log l = new Log(lines[j]);
                if(l.start > section_end) continue;
                if(l.end < section_start) continue;
                cnt++;
            }
            max = max > cnt ? max : cnt;
        }
        return max;
    }

}

class Log {
    int start;
    int end;
    int dur;
    Log(String s){
        StringTokenizer st = new StringTokenizer(s);
        st.nextToken();
        
        String time = st.nextToken();
        String dur_str = st.nextToken();
        st = new StringTokenizer(dur_str.substring(0, dur_str.length() - 1), ".");
        dur = Integer.parseInt(st.nextToken()) * 1000;
        dur += Integer.parseInt(st.nextToken());

        st = new StringTokenizer(time, ":.");
        this.end = (Integer.parseInt(st.nextToken()) * 3600000);
        end += (Integer.parseInt(st.nextToken()) * 60000);
        end += (Integer.parseInt(st.nextToken())) * 1000;
        end += (Integer.parseInt(st.nextToken()));
        
        this.start = end - dur + 1;
    }
}