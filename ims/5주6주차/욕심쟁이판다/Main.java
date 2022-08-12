package 문제집.backjoon.욕심쟁이판다BJ1937;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * date: 22.07.29
 * 참조링크: https://steady-coding.tistory.com/39
 * 부참조: https://velog.io/@hyeon930/BOJ-1937-%EC%9A%95%EC%8B%AC%EC%9F%81%EC%9D%B4-%ED%8C%90%EB%8B%A4-Java
 */

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dp;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/욕심쟁이판다BJ1937/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N][N];

        int max = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int temp = dfs(i, j);
                max = Math.max(max,temp);
            }
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(max);
    }

    private static int dfs(int x, int y) {
        if(dp[x][y] != 0){
            return dp[x][y];
        }
//        if(isStop(x,y)){
//            return dp[x][y];
//        }
        dp[x][y] = 1;
        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isOut(nx,ny) ||  map[nx][ny] <= map[x][y]){
                continue;
            }
            dp[x][y] = Math.max(dp[x][y], dfs(nx,ny)+1);
        }
        return dp[x][y];
    }

    private static boolean isStop(int x, int y) {
        int cnt = 0;

        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isOut(nx,ny) || map[nx][ny] >= map[x][y]){
                cnt++;
            }
        }
        return cnt == 4;
    }

    static boolean isOut(int nx, int ny){
        return nx<0 || ny <0 || nx>= N || ny>=N;
    }
}
