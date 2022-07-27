import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * date: 22.07.25
 * 주 참조1: https://yabmoons.tistory.com/536
 * 주 참조2: https://pangtrue.tistory.com/310
 *
 * memo: f(2) = 3 ( 구해보니 3이나옴. 이 3에 대해서 A,B,C라고 지칭 )
 * f(4) = 9 + 2 ( ABC 이므로 3*3 , 구해보니 예외케이스 2개 나옴. 예외케이스가 나오는 이유는 홀수일 때 남은 블록에 대해서 가능해지기 때문)
 * f(6) = f(4)*f(2) + f(2) * 2 + f(0) * 2 ( f(2) * f(4) -> f(4) * f(2)와 중복된다. 해당 경우와 중복되지 않는 경우는 f(2) * 예외케이스 개수 일 때이다.
 * f(8) = f(6)*f(2) + f(4) * 2 + f(2) * 2 + f(0) * 2
 * ...
 */

public class Main {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        dp = new int[31];
        dp[0] = 1;
        dp[2] = 3;

        for(int i=4;i<=30;i+=2){
            dp[i] = dp[i-2] * dp[2];
            for(int j=0;j<=i-4;j+=2){
                dp[i] += dp[j] * 2;
            }
        }
        System.out.println(N % 2 == 0 ? dp[N] : 0);
    }
}
