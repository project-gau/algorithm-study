package 문제집.backjoon.빙산BJ2573;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * date: 22.08.01
 */

public class Main {
    static int N,M;
    static int[][] map;
    static boolean zeroFlag;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/빙산BJ2573/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 실수1 -> st.nextToken() 이 아니라 br.readLine() 두번함
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;
        while(!checkIsZero() && !isSeparate()){
            time ++;
            reduce();
        }
        System.out.println(zeroFlag ? 0 : time);
    }

    private static void print() {
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void reduce() {
        int[][] cloneMap = new int[N][M];

        for(int i=0;i<N;i++){
            cloneMap[i] = map[i].clone();
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                // 실수2. >= 0으로 해버림
                if(map[i][j] > 0){
                    int discount = 0;
                    for(int d=0;d<4;d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        // 실수3. map[nx][ny] == 0 으로 해버림. 0이 아닐때 빼져야 함.
                        if(isOut(nx,ny) || map[nx][ny] != 0) continue;
                        discount++;
                    }
                    cloneMap[i][j] = map[i][j] - discount;
                    // 실수4. 0보다 작으면 0으로 만들어줘야함
                    if(cloneMap[i][j] < 0){
                        cloneMap[i][j] = 0;
                    }
                }
            }
        }
        map = cloneMap;
    }

    private static boolean isSeparate() {
        visited = new boolean[N][M];
        int cnt = 0;
        for(int i=0;i<N;i++){
            // 실수5. M이 아니라 N으로 적어줌 그래서 isOut 했는데도 indexOutOfError 걸림
            for(int j=0;j<M;j++){
                // 실수6. map[i][j] != 0 인 조건 추가 안해줌
                if(!visited[i][j] && map[i][j] != 0){
                    cnt++;
                    bfs(i,j);
                }
            }
        }
        return cnt >= 2;
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        visited[x][y] = true;

        while (!queue.isEmpty()){
            int[] poll = queue.poll();

            for(int d=0;d<4;d++){
                int nx = poll[0] + dx[d];
                int ny = poll[1] + dy[d];

                // 실수7. map[nx][ny] == 0 인 조건 추가 안해줌
                if(isOut(nx,ny) || visited[nx][ny] || map[nx][ny] == 0) continue;

                queue.offer(new int[]{nx,ny});
                visited[nx][ny] = true;
            }
        }
    }
    static boolean isOut(int nx,int ny){
        return nx<0 || ny<0 || nx>=N || ny>=M;
    }

    private static boolean checkIsZero() {
        boolean flag = true;
        out:for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] != 0){
                    flag = false;
                    break out;
                }
            }
        }
        if(flag){
            zeroFlag = true;
        }
        return flag;
    }
}
