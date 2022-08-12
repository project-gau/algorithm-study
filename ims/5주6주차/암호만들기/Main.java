package 문제집.backjoon.암호만들기BJ1759;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * date: 22.08.03
 */

public class Main {
    static int N,L;
    static char[] alphas;
    static int[] temp;
    static Set<Character> set;
    static char[] vowel;
    static TreeSet<String> res;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/암호만들기BJ1759/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        alphas = new char[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            alphas[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alphas);
        temp = new int[L];
        set = new HashSet<>();
        vowel = new char[]{'a','e','i','o','u'};
        res = new TreeSet<>();
        for (char c : vowel) {
            set.add(c);
        }
        perm(0,0);
        for (String re : res) {
            System.out.println(re);
        }
    }

    private static void perm(int cnt, int check) {
        if(cnt == L){
            StringBuilder sb = new StringBuilder();
            boolean flag = false;
            int cn = 0;
            for(int i=0;i<L;i++){
                if(set.contains(alphas[temp[i]])){
                    flag = true;
                }else{
                    cn++;
                }
                sb.append(alphas[temp[i]]);
            }
            // 모음만 포함돼있으면 되겠지 하고 자음 체크는 안했는데, 생각해보니 모음으로만 이루어 질수도 있음
            if(flag && cn >= 2){
                res.add(sb.toString());
            }
            return ;
        }

        for(int i=0;i<N;i++){
            if( (check & 1 << i) != 0) continue;
            // 증가하는 부분 체크 이 부분 없으면 메모리초과
            if( cnt != 0 && temp[cnt-1] > i ) continue;
            temp[cnt] = i;
            perm(cnt+1,check | 1 << i);
        }
    }
}
