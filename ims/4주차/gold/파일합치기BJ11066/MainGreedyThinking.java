package 문제집.backjoon.파일합치기BJ11066;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * date: 22.07.25
 * memo: 그리디로 하면 안될까? 했는데 역시 안된다.
 * 작은 거를 여러 번 더해주는 게 포인트가 아닐까 했다.
 */

public class MainGreedyThinking {
    static int T;
    static int N;
    static int[] arr;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/파일합치기BJ11066/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t=0;t<T;t++){
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[N];

            for(int i=0;i<N;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            pq = new PriorityQueue<>();

            for(int i=0;i<N;i++){
                pq.offer(arr[i]);
            }

            int sum = 0;

            while(!pq.isEmpty()){
                int p1 = pq.poll();
                if(pq.isEmpty()){
                    break;
                }
                int p2 = pq.poll();
                int temp = p1 + p2;
                sum += temp;
                pq.offer(temp);
            }
            System.out.println(sum);
        }
    }
}
