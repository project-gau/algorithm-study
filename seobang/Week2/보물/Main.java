package 보물;
import java.lang.reflect.Array;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Integer[] A = new Integer[N];
        Integer[] B = new Integer[N];
        int sum = 0;

        for(int i=0;i<N;i++){
            A[i] = sc.nextInt();
        }
        for(int i=0;i<N;i++){
            B[i] = sc.nextInt();
        }

        Arrays.sort(A); // A 오름차순
        Arrays.sort(B,Collections.reverseOrder()); // B 내림차순

        for(int i=0;i<N;i++){
            sum += A[i]*B[i];
        }
        System.out.println(sum);
    }
}
