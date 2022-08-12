package 문제집.backjoon.ABCDE;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * date: 22.08.02
 * memo: depth가 4인 것을 확인. 비트마스킹으로 방문체크
 * system.exit으로 종료 안시켜주면 시간터짐
 */

public class Main {
    static int N,M;
    static ArrayList<Integer>[] list;
    static boolean res;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/ABCDE/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N];

        for(int i=0;i<N;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        for(int i=0;i<N;i++){
            dfs(i,0,1 << i);
        }
        System.out.println(0);
    }

    private static void dfs(int index,int depth, int flag) {
        if(depth == 4){
            res = true;
            System.out.println(1);
            System.exit(0);
            return ;
        }
        for(int connect : list[index]) {
            if ((flag & 1 << connect) != 0) continue;
            dfs(connect, depth+1, flag | 1 << connect);
        }
    }
}
