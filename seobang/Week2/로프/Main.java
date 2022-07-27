package 로프;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] rope = new int[N];
        int[] max = new int[N];
        int result = 0;
        for(int i=0;i<N;i++){
            rope[i] = sc.nextInt();
        }
        Arrays.sort(rope);

        for(int i=0;i<N;i++) {
            max[i] = rope[i] * (N - i);
            result = Math.max(result, max[i]);
        }
        System.out.println(result);
    }
}
