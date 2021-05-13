package prg_1836.src;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args){
        Solution s = new Solution();
        String[] sa = {"AB", "BA"};
        s.solution(2, 2, sa);
    }
}

class Solution {
    int n, m;
    String[] board;
    Pair[][] p;
    public String solution(int m, int n, String[] board) {
        String answer = "";
        this.n = n;
        this.m = m;
        this.board = board;
        p = new Pair[26][2];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int ch = board[i].charAt(j);
                if(ch == '*' || ch == '.') continue;
                if(p[ch - 'A'][0] == null) {
                    p[ch - 'A'][0] = new Pair(i, j);
                }
                else{
                    p[ch - 'A'][1] = new Pair(i, j);
                }
            }
        }
        answer = go();

        return answer;
    }

    public String go(){
        boolean ok = true;
        String res = "";
        while(ok){
            ok = false;
            int cnt = 0;
            for(int i = 0; i < 26; i++){
                if(p[i][0] == null) continue;
                if(p[i][0].x != -1){
                    if(find_route(i)) {
                        char ch = board[p[i][0].y].charAt(p[i][0].x);
                        res += ch;
                        p[i][0].init();
                        p[i][1].init();
                        System.out.println("res: " + res);
                        ok = true;
                        break;
                    }
                    else{
                        cnt++;
                    }
                }
            }
            if(!ok && cnt != 0) {
                return "IMPOSSIBLE";
            }
        }
        return res;
    }

    public boolean find_route(int idx){
        boolean ok = true;

        int from = p[idx][0].x;;
        int to = p[idx][1].x;
        if(from > to) {
            int tmp = from;
            from = to;
            to = tmp;
        }
        for(int i = from; i <= to; i++) {
            char ch = board[p[idx][0].y].charAt(i);
            if(isInvalid(idx, ch)){
                ok = false;
            }
            if(!ok) break;
        }
        if(ok){
            from = p[idx][0].y;
            to = p[idx][1].y;
            if(from > to) {
                int tmp = from;
                from = to;
                to = tmp;
            }
            for(int j = from; j <= to; j++){
                char ch = board[j].charAt(p[idx][1].x);
                if(isInvalid(idx, ch)){
                    ok = false;
                }
                if(!ok) break;
            }
            if(ok){
                System.out.println(idx);
                return true;
            }
        }

        ok = true;
        from = p[idx][0].x;
        to = p[idx][1].x;
        if(from > to) {
            int tmp = from;
            from = to;
            to = tmp;
        }
        for(int i = from; i <= to; i++){
            char ch = board[p[idx][1].y].charAt(i);
            if(isInvalid(idx, ch)){
                ok = false;
            }
            if(!ok) break;
        }
        if(ok) {
            from = p[idx][0].y;
            to = p[idx][1].y;
            if(from > to) {
                int tmp = from;
                from = to;
                to = tmp;
            }
            for(int j = from; j <= to; j++) {
                char ch = board[j].charAt(p[idx][0].x);
                if(isInvalid(idx, ch)){
                    ok = false;
                }
                if(!ok) break;
            }
            if(ok){
                return true;
            }
        }
        return false;
    }

    public boolean isInvalid(int idx, char ch){
        if(ch != '.') {
            if(ch - 'A' == idx) return false;
            if(ch == '*') return true;
            else if(p[ch - 'A'][0].x != -1) return true;
        }
        return false;
    }

    
}

class Pair {
    int x = 1, y = 1;
    Pair(int y, int x){
        this.x = x;
        this.y = y;
    }
    public void init() {
        this.x = -1;
        this.y = -1;
    }
}

