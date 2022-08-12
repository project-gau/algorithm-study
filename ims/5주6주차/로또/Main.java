package 문제집.backjoon.로또BJ6603;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * date: 22.07.29
 */

public class Main {
    static int[] arr;
    static int[] temp;
    static int N;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/로또BJ6603/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0){
                break;
            }
            arr = new int[N];
            temp = new int[N];
            for(int i=0;i<N;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            comb(0,0);
            System.out.println();
        }
    }

    private static void comb(int cnt, int start) {
        if (cnt == 6) {
            for (int i = 0; i < 6; i++) {
                System.out.print(temp[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < N; i++) {
            temp[cnt] = arr[i];
            comb(cnt+1, i+1);
        }
    }
}
