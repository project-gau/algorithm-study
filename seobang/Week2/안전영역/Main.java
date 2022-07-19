package 안전영역;
import java.util.*;
public class Main {
    static int N;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0}; //우, 하, 좌, 상
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] v;
    static int h;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = sc.nextInt();
            }
        }
        int count;
        h = 0;
        int maxValue = 0;

        while(h <= 100){
            count = 0;
            v = new boolean[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(map[i][j] > h && !v[i][j]){
                        count++;
                        dfs(i, j, h);
                    }
                }
            }
            h++;
            maxValue = Math.max(maxValue, count);
        }
        System.out.println(maxValue);
    }

    static void dfs(int x, int y, int h){
        if(isOut(x,y) || v[x][y] || map[x][y] <= h){
            return;
        }
        v[x][y] = true;

        for(int i=0;i<4;i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            dfs(nx,ny,h);
        }

    }
    static boolean isOut(int x, int y){
        return x < 0 || x >= N || y < 0 || y >= N;
    }
}
