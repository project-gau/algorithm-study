package 문제집.backjoon.불끄기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * date: 22.07.14
 */

public class Main {
    static int[][] map;
    static int[][] tempMap;
    static int N=10;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/불끄기/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[N][N];
        for(int i=0;i<N;i++){
            String s = br.readLine();
            for(int j=0;j<N;j++){
                char c = s.charAt(j);
                if(c=='O'){
                    map[i][j] = 1;
                }else{
                    map[i][j] = 0;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        tempMap = new int[N][N];

        for(int c=0; c< (1<<10);c++){
            for(int i=0;i<N;i++){
                tempMap[i] = map[i].clone();
            }

            int cnt = getCnt(c);

            if(check()){
                min = Math.min(min,cnt);
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void printMap() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(tempMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }

    private static int getCnt(int c) {
        int cnt = 0;
        for(int j=0;j<N;j++){
            // 첫번째는 map[0][j]와 상관없이 그냥 센다.
            // cnt 를 세기 위한 로직이라고 생각하면 될 것 같다.
            // 어차피 1024개, 모든 경우의 수를 다 해보기 때문에 기존에 map[0][j]가 어떤 모양으로 생겼든 상관 없다.
            if(( c & 1 <<j) != 0){
                cnt++;
                turnOnOffSwitch(tempMap,0,j);
            }
        }

        for(int i=1;i<N;i++){
            for(int j=0;j<N;j++){
                if(tempMap[i-1][j] == 1){
                    turnOnOffSwitch(tempMap,i,j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean check() {
        for(int j=0;j<N;j++){
            if(tempMap[9][j] == 1){
                return false;
            }
        }
        return true;
    }

    // 첫번째 c값을 기준으로 두번째, 두번째 값을 기준으로 3번째 이렇게 하려고 했으나
    // 2진수 -> 10진수 , 10진수 -> 2진수 바꾸는 게 맞나 싶었고
    // 첫번째 row 의 cnt 를 세지 못한다는 점이 틀렸다.
    private static int getCntFail(int rowValue) {
        int cnt = 0;
        StringBuilder sb;
        for(int i=1;i<N;i++){
            for(int j=0;j<N;j++){
                int compareValue = tempMap[i][j] << j;
                if((rowValue | compareValue) != 0){
                    turnOnOffSwitch(tempMap,i,j);
                    cnt++;
                }
            }
            sb = new StringBuilder();
            for(int j=0;j<N;j++){
                sb.append(tempMap[i][j]);
            }
            rowValue = Integer.parseInt(sb.toString());
        }
        return cnt;
    }

    private static void turnOnOffSwitch(int[][] map, int i, int j) {
        if(map[i][j] == 0){
            map[i][j] = 1;
        }else{
            map[i][j] = 0;
        }

        for(int d=0;d<4;d++){
            int nx = i + dx[d];
            int ny = j + dy[d];
            if(isOut(nx,ny)) continue;
            if(map[nx][ny] == 0){
                map[nx][ny] = 1;
            }else{
                map[nx][ny] = 0;
            }
        }
    }

    private static boolean isOut(int nx, int ny) {
        return nx< 0 || ny<0 || nx>=N || ny>=N;
    }
}
