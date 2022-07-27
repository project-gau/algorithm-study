import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * date: 22.07.25
 * 주참조: https://hu-coding.tistory.com/28 , https://yeoeun-ji.tistory.com/49
 * 보조참조: https://nahwasa.com/entry/%EB%B0%B1%EC%A4%80-2225-%EC%9E%90%EB%B0%94-%ED%95%A9%EB%B6%84%ED%95%B4-BOJ-2225-JAVA
 */

public class Main {
    static int N,K;
    static int[][] dp;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        dp = new int[K+1][N+1];

        for(int k=0;k<=K;k++){
            dp[k][0] = 1;
        }

        for(int i=1;i<=K;i++){
            for(int j=1;j<=N;j++){
                dp[i][j] = (dp[i-1][j] + dp[i][j-1])%1000000000;
            }
        }
        System.out.println(dp[K][N]);
    }
}
