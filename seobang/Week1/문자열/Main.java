package 문자열;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String A = sc.next();
        String B = sc.next();
        char[] aA = new char[A.length()];
        char[] bB = new char[B.length()];

        for(int i = 0; i < A.length(); i++){
            aA[i] = A.charAt(i);
        }
        for(int i = 0; i< B.length(); i++){
            bB[i] = B.charAt(i);
        }
        int result = A.length();


        for(int i = 0;i <= B.length()-A.length();i++){
            int count = 0;

            for(int j=0;j<A.length();j++){
                if(aA[j] != bB[j+i]){
                    count++;
                }
            }
            if(count < result){
                result = count;
            }
        }

        System.out.println(result);
    }
}
