package 올림픽;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int num[];
    static int gold[];
    static int silver[];
    static int bronze[];
    static int grade[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        num = new int[N];
        gold = new int[N];
        silver = new int[N];
        bronze = new int[N];
        grade = new int[N];

        for(int i=0;i<N;i++){
            num[i] = sc.nextInt();
            gold[i] = sc.nextInt();
            silver[i] = sc.nextInt();
            bronze[i] = sc.nextInt();
        }

        for(int i=0;i<N;i++){
            int temp = 0;
            for(int j=0;j<N;j++){
                if(i == j) continue;

                if(gold[i] == gold[j]){
                    if(silver[i] == silver[j]){
                        if(bronze[i] < bronze[j]){
                            temp++;
                        }
                    }else if(silver[i] < silver[j]){
                        temp++;
                    }
                }else if(gold[i] < gold[j]){
                    temp++;
                }
            }
            grade[i] = temp+1;
        }

        for(int i=0;i<N;i++){
            if(num[i] == K){
                System.out.println(grade[i]);
            }
        }
    }
}
