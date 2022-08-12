package 문제집.backjoon.괄호제거BJ2800;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * date: 22.07.29
 * https://hsin.hr/coci/archive/2011_2012/
 * 정렬에서 문제가 있었음. 18%에서 틀렸는데, list : ArrayList -> TreeSet으로 변경하니까 맞음
 */

public class Main {
    static int N;
    static int aN;
    static int target;
    static int[] arr;
    static int[] temp;
    static String iv;
    static Map<Integer,Integer> map;
    static TreeSet<String> list;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/괄호제거BJ2800/input.txt"));

        Scanner sc = new Scanner(System.in);

        Stack<Integer> stack = new Stack<>();
        map = new HashMap<>();

        iv = sc.next();
        N = iv.length();

        for(int i=0;i<iv.length();i++){
            if(iv.charAt(i) == '('){
                stack.push(i);
            }else if(iv.charAt(i) == ')'){
                map.put(stack.pop(),i);
            }
        }
        Set<Integer> keys = map.keySet();

        arr = new int[keys.size()];
        aN = keys.size();
        int index = 0;
        for(int key : keys){
            arr[index++] = key;
        }
        list = new TreeSet<>();
        for(int i=1;i<=aN;i++){
            target = i;
            temp = new int[i];
            comb(0,0);
        }
        list.forEach(System.out::println);
    }

    private static void comb(int cnt, int start) {
        if(cnt == target){
            StringBuilder sb = new StringBuilder();
            out: for(int i=0;i<N;i++){
                for(int j=0;j<target;j++){
                    if(i==temp[j] || (map.containsKey(temp[j]) && i == map.get(temp[j]))) continue out;
                }
                sb.append(iv.charAt(i));
            }
            list.add(sb.toString());
            return ;
        }

        for(int i=start;i<aN;i++){
            temp[cnt] = arr[i];
            comb(cnt+1,i+1);
        }
    }
}
