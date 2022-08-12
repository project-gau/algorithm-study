package 문제집.backjoon.히스토그램에서가장큰직사각형BJ6549;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * date: 22.08.09
 * memo:
 */

public class Main {
    static int N;
    static long[] arr;
    static long max;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/히스토그램에서가장큰직사각형BJ6549/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N==0) break;
            max = 0;
            arr = new long[N+2];
            for(int i=1; i<N+1; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            for(int i=1;i<N+2;i++){
                while(!stack.isEmpty()){
                    if(arr[stack.peek()] <= arr[i]){
                        break;
                    }
                    int standard = stack.pop();
                    max = Math.max(max, arr[standard] * (i - stack.peek() - 1));
                }
                stack.push(i);
            }
            System.out.println(max);
        }
    }
}
