package 잃어버린괄호;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        String[] subs = str.split("-");
        int result = 0;
        for(int i=0;i<subs.length;i++){
            String[] add = subs[i].split("\\+"); //덧셈 분리
            int sum = 0;
            for(String item : add){
                sum += Integer.parseInt(item); // 덧셈값 더하기
            }

            if(i==0){
                result += sum; //최종변수에 맨처음값
            }else{
                result -= sum; //최종변수에 다음꺼 빼기~
            }
        }

        System.out.println(result);
    }
}
