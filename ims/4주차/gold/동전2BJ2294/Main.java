
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * date: 22.07.25
 * 최적화: https://loosie.tistory.com/374
 */

public class Main {
    static int N,K;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/동전2BJ2294/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[K+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1;i<=K;i++){
            for(int j=0;j<N;j++){
                if(i - arr[j] >= 0 && dp[i-arr[j]] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], dp[i-arr[j]]+1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[K] == Integer.MAX_VALUE ? -1 : dp[K]);
    }
}
