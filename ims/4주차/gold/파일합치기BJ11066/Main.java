import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * date: 22.07.25
 * memo: 처음엔 greedy로 생각했으나, 역시 틀렸고 해답을 찾아봤다. 해답을 찾아봐도 한참 이해가 안됐었다.
 *
 * 주 참조 : https://cocoon1787.tistory.com/317
 * 보조 참조 : https://data-make.tistory.com/402
 * 자바 코드 참조 : https://guy-who-writes-sourcecode.tistory.com/43
 */

public class Main {
    static int T;
    static int N;
    static int[] arr;
    static int[][] dp;
    static int[] prefixSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[N+1];
            prefixSum = new int[N+1];

            for(int i=1;i<=N;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1;i<=N;i++){
                prefixSum[i] = prefixSum[i-1] + arr[i];
            }

            dp = new int[N+1][N+1];

            // 0부터 시작하면 안되는 이유는 0부터 시작하면 dp[1][1], dp[2][2] 와 같이 dp[j][j]가 다 INF로 채워지기 때문
            for(int i=1;i<=N;i++){
                for(int j=1;j<=N-i;j++){
                    dp[j][j+i] = Integer.MAX_VALUE;
                    for(int k=j;k<j+i;k++){
                        dp[j][j+i] = Math.min(dp[j][j+i], dp[j][k] + dp[k+1][j+i] + prefixSum[j+i] - prefixSum[j-1]);
                    }
                }
            }
            System.out.println(dp[1][N]);
        }
    }

    private static void printdp() {
        for(int i=1;i<N+1;i++){
            for(int j=1;j<N+1;j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
