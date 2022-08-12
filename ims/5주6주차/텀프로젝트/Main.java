package 문제집.backjoon.텀프로젝트BJ9466;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * date: 22.08.05
 * link: https://velog.io/@jh5253/%EB%B0%B1%EC%A4%80-9466-%ED%85%80-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8Java%EC%9E%90%EB%B0%94
 * idea: 사이클이 존재하는 것들의 수를 세자!
 *
 * 재방문했는데, 끝나지 않았으면 사이클이 생긴 것
 */

public class Main {
    static int T;
    static int N;
    static int[] arr;

    static boolean[] visited;
    static boolean[] isEnd;
    static int cycleCount;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/텀프로젝트BJ9466/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            cycleCount = 0;
            arr = new int[N + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[N+1];
            isEnd = new boolean[N+1];

            for (int i = 1; i <= N; i++) {
                dfs(i);
            }
            System.out.println(N - cycleCount);
        }
    }

    private static void dfs(int cur) {
        int next = arr[cur];
        if (visited[cur]) {
            // 사이클 체크
            if (!isEnd[cur]) {
                int p = next;
                isEnd[cur] = true;
                cycleCount++;
                while (p != cur) {
                    cycleCount++;
                    isEnd[p] = true;
                    p = arr[p];
                }
            }
        } else {
            // 방문 안했으면 다음거 보내주고, 다음 dfs에서 사이클 체크를 해줬기 때문에 isEnd = true해줘야한다.
            visited[cur] = true;
            dfs(next);
            // 다음거 보냈으면 isEnd 체크 해줘야한다.
            isEnd[cur] = true;
        }
    }
}
