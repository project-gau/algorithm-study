import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * date: 22.07.25
 * 참조link: https://steady-coding.tistory.com/142
 */

public class Main {
    static int N,M;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        // dp -> n-1, m-1까지 가는 데까지 걸리는 경우의 수
        dp = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0,0));
    }

    private static int dfs(int x, int y) {
        if(x==N-1 && y== M-1){
            return 1;
        }
        if(dp[x][y] != -1){
            return dp[x][y];
        }
        dp[x][y] = 0;
        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isOut(nx,ny)) continue;

            if(map[x][y] > map[nx][ny]){
                dp[x][y] += dfs(nx,ny);
            }
        }
        return dp[x][y];
    }

    static boolean isOut(int nx,int ny){
        return nx < 0 || ny < 0 || nx >= N || ny>=M;
    }
}
