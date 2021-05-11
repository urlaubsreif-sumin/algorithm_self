package prg_67257;

import java.util.ArrayList;

import java.util.ArrayList;

class Solution {
	ArrayList<Long> n = new ArrayList<>();
    ArrayList<Character> op = new ArrayList<>();
    
    ArrayList<Long> expnum = new ArrayList<>();
    ArrayList<Character> expop = new ArrayList<>();
    
    public long solution(String expression) {
        long answer = 0;
        long num = 0;
        for(int i = 0; i < expression.length(); i++) {
        	char ch = expression.charAt(i);
        	if(ch == '+' || ch == '-' || ch == '*') {
        		expnum.add(num);
        		num = 0;
        		expop.add(ch);
        	}
        	else {
        		num *= 10;
        		num += (ch - '0');
        	}
        }
        expnum.add(num);
        
        for(int a = 0; a < 3; a++) {
        	for(int b = 0; b < 3; b++) {
        		if(b == a) continue;
        		for(int c = 0; c < 3; c++) {
        			if(c == a || c == b) continue;
        			long tmp = Math.abs(go(a, b, c));
        			answer = answer > tmp ? answer : tmp;
        		}
        	}
        }
        return answer;
    }
    
    public long go(int a , int b, int c) {
    	copy();
    	for(int i = 0; i < 3; i++) {
    		if(a == i) {
    			gogo('+');
    		}
    		else if(b == i) {
    			gogo('-');
    		}
    		else if(c == i) {
    			gogo('*');
    		}
    	}
    	return n.get(0);
    }
    
    public void gogo(char target) {
    	for(int i = 0; i < op.size(); i++) {
    		if(op.get(i) == target) {
    			n.set(i + 1, calc(n.get(i), n.get(i + 1), target));
    			n.set(i, (long) -1);
    		}
    	}
    	int idx = 0;
    	while(idx < n.size()) {
    		if(n.get(idx) == -1) {
    			n.remove(idx);
    		}
    		else {
    			idx++;
    		}
    	}
    	idx = 0;
    	while(idx < op.size()) {
    		if(op.get(idx) == target) {
    			op.remove(idx);
    		}
    		else {
    			idx++;
    		}
    	}
    }
    
    public long calc(long n1, long n2, char op) {
    	switch(op) {
    	case '+':
    		return n1 + n2;
    	case '-':
    		return n1 - n2;
    	case '*':
    		return n1 * n2;
    	}
    	return -1;
    }
    
    public void copy() {
    	n.clear();
    	op.clear();
    	for(long t : expnum) {
    		n.add(t);
    	}
    	for(char t : expop) {
    		op.add(t);
    	}
    }
}