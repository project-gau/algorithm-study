package 단어뒤집기2;
import java.util.*;
public class Main {
    static boolean check;
    static char c;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        Stack<Character> stack = new Stack<>();
        check = false;

        for(int i=0;i<S.length();i++){
            c = S.charAt(i);

            if(c == '<'){
                check = true;
                while (!stack.empty()){ // < 만나기 전 스택에 저장된 내용 출력
                    System.out.print(stack.pop());
                }
                System.out.print(c);
            }
            else if(c == '>'){
                check = false;
                System.out.print(c);
            }
            else if(check){ // < 만난 후 문자 그대로 출력
                System.out.print(c);
            }
            else if(!check){
                if(c == ' '){
                    while(!stack.empty()){
                        System.out.print(stack.pop());
                    }
                    System.out.print(c);
                }else{
                    stack.push(c);
                }
            }

        }
        while (!stack.empty()){ // 문자열 끝에 공백문자 없어서 나머지 다 출력
            System.out.print(stack.pop());
        }
    }
}
