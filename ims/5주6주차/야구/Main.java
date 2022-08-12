package 문제집.backjoon.야구BJ17281;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * date: 22.08.05
 */

public class Main {
    static int N;
    static int[][] arr;
    static int[] temp;
    static int max;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/야구BJ17281/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][9];
        temp = new int[9];
        StringTokenizer st;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<9;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        perm(0,0);
        System.out.println(max);
    }

    private static void perm(int cnt, int flag) {
        if(cnt == 9){
            int calculate = calculate();
            max = Math.max(max,calculate);
            return ;
        }

        for(int i=0;i<9;i++){
            if( (flag & 1 << i) != 0) continue;
            // 이 부분이 포인트
            if(cnt >= 4 && temp[3] != 0) continue;
            temp[cnt] = i;
            perm(cnt+1,flag | 1 << i);
        }
    }

    private static int calculate() {
        int outCount = 0;
        int inning = 0;
        // 1 2 3
        boolean[] base = new boolean[4];
        int cur = 0;
        int score = 0;

        while (inning < N){
            // temp[cur] 이 아니라 그냥 cur 하는 실수
            int action = arr[inning][temp[cur]];
            if(action == 0){
                outCount++;
            }else if(action == 1){
                if(base[3]){
                    score ++;
                    base[3] = false;
                }
                if(base[2]){
                    base[3] = true;
                    base[2] = false;
                }
                if(base[1]){
                    base[2] = true;
                }
                base[1] = true;
            } else if(action == 2){
                if(base[3]){
                    score++;
                    base[3] = false;
                }
                if(base[2]){
                    score ++;
                }
                if(base[1]){
                    base[3] = true;
                    base[1] = false;
                }
                base[2] = true;
            } else if(action == 3){
                if(base[3]){
                    score ++;
                }
                if(base[2]){
                    score++;
                    base[2] = false;
                }
                if(base[1]){
                    score++;
                    base[1] = false;
                }
                base[3] = true;
                // 홈런일 때 처리 안해줬었음
            }else if(action == 4){
                score ++;
                // 홈런일 때 base 남아있을 시 처리 안해줬었음
                for(int i=1;i<4;i++){
                    if(base[i]){
                        score++;
                        base[i] = false;
                    }
                }
            }
            // %9 안해줬었음
            cur = (cur + 1) % 9;
            if(outCount == 3){
                inning++;
                base = new boolean[4];
                outCount = 0;
            }
        }
        return score;
    }
}
