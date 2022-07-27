
import java.io.*;
import java.util.Arrays;

/**
 * date: 22.07.27
 */

public class Main {
    static long[] minDp;
    static int[] cost = {0,0,1,7,4,2,0,8};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        minDp = new long[101];
        Arrays.fill(minDp,Long.MAX_VALUE);
        minDp[2] = 1;
        minDp[3] = 7;
        minDp[4] = 4;
        minDp[5] = 2;
        minDp[6] = 6;
        minDp[7] = 8;
        minDp[8] = 10;

        for(int i=9;i<=100;i++){
            for(int j=2;j<=7;j++){
                String temp = "" + minDp[i-j] + cost[j];
                minDp[i] = Math.min(minDp[i], Long.parseLong(temp));
            }
        }

        for(int t=0;t<T;t++){
            int N = Integer.parseInt(br.readLine());
            int degree = N/2;
            boolean isEven = N%2 == 0;

            StringBuilder max = new StringBuilder();

            if(isEven){
                max.append("1");
            }else{
                max.append("7");
            }

            for(int i=0;i<degree-1;i++){
                max.append("1");
            }
            System.out.println(minDp[N] + " " + max);
        }
    }
}
