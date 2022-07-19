package 사탕게임;

import java.util.*;

public class Main {
    static char[][] board;
    static int N;
    static int result = 0;

    public static void candy(){
        for(int i=0; i<N;i++){ // 가로검사
            int count = 1;
            for(int j=0;j<N-1;j++){
                if(board[i][j] == board[i][j+1]){
                    count++;
                }else{
                    count = 1;
                }
                result = Math.max(result, count);
            }
        }

        for(int i=0;i<N;i++){ //세로검사
            int count = 1;
            for(int j=0;j<N-1;j++){
                if(board[j][i] == board[j+1][i]){
                    count++;
                }else{
                    count = 1;
                }
                result = Math.max(result, count);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        board = new char[N][N];
        String str = "";

        for(int i=0;i<N;i++){
            str = sc.next();
            for(int j=0;j<N;j++){
                board[i][j] = str.charAt(j);
            }
        }

        for(int i=0;i<N;i++){ // 가로
            for(int j=0;j<N-1;j++){
                char temp = board[i][j]; // 위치교환
                board[i][j] = board[i][j+1];
                board[i][j+1] = temp;

                candy();

                temp = board[i][j]; // 원상복구
                board[i][j] = board[i][j+1];
                board[i][j+1] = temp;
            }
        }

        for(int i=0;i<N;i++){ // 세로
            for(int j=0;j<N-1;j++){
                char temp = board[j][i];
                board[j][i] = board[j+1][i];
                board[j+1][i] = temp;

                candy();

                temp = board[j][i];
                board[j][i] = board[j+1][i];
                board[j+1][i] = temp;
            }
        }
        System.out.println(result);

    }

}
