package 문제집.backjoon.영만들기BJ7490;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;

/**
 * date: 22.07.30
 */

public class Main {
    static int N;
    static int T;
    static char[] ops = {'+','-',' '};
    static TreeSet<String> treeSet;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();
        for(int t=0;t<T;t++){
            N = sc.nextInt();
            treeSet = new TreeSet<>();
            dfs(2,new StringBuilder().append(1));
            treeSet.forEach(System.out::println);
            System.out.println();
        }
    }

    private static void dfs(int index,StringBuilder sb) {
        if(index > N){
            int cal = cal(sb.toString());
            if(cal == 0){
                treeSet.add(sb.toString());
            }
            return ;
        }
        for(int b=0;b<3;b++){
            dfs(index+1,new StringBuilder().append(sb).append(ops[b]).append(index));
        }
    }

    static int cal(String s){
        LinkedList<Character> op = new LinkedList<>();
        LinkedList<Integer> num = new LinkedList<>();

        s = s.replace(" ","");
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == '+' || c == '-' ){
                op.add(c);
            }else{
                int p = i;
                while(p < s.length() && s.charAt(p) != '+' && s.charAt(p) != '-'){
                    p++;
                }
                String iv = s.substring(i,p);
                i = p-1;
                num.add(Integer.valueOf(iv));
            }
        }
        int res = 0;
        while(!num.isEmpty()){
            int p1 = num.poll();
            if(num.isEmpty()){
                res = p1;
                break;
            }
            int p2 = num.poll();
            char opPop = op.poll();
            int temp;
            if(opPop == '+'){
                temp = p1 + p2;
            }else{
                temp = p1 - p2;
            }
            num.offerFirst(temp);
        }
        return res;
    }
}
