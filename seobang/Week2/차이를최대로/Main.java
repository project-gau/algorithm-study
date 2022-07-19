package 차이를최대로;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] result;
    static int sum;
    static boolean[] v;
    static int maxValue;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }
        result = new int[N];
        v = new boolean[N];

        dfs(0);
        System.out.println(maxValue);

    }
    static int max(int result[]){
        sum = 0;
        for(int i=0;i<N-1;i++){
            sum += Math.abs(result[i] - result[i+1]);
        }
        return sum;
    }
    static void dfs(int level){
        if(level == N){
            maxValue = Math.max(maxValue, max(result));
            return;
        }else{
            for(int i=0;i<N;i++){
                if(v[i] != true){ // 방문한 숫자는 안들어가도록.★★★
                    v[i] = true;
                    result[level] = arr[i];
                    dfs(level+1);
                    v[i] = false;
                }
            }
        }
    }
}
