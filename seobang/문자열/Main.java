
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String A = sc.next();
		String B = sc.next();
		char aA[] = new char[A.length()];
		char bB[] = new char[B.length()];
		
		int count = 0;
		
		if(A.length() == B.length()) {
			for(int i=0;i<A.length();i++) {
				aA[i] = A.charAt(i);
				bB[i] = B.charAt(i);
				
				if(aA[i] != bB[i]) {
					count++;
				}
			}
		}else {
			//A B ���� �����ؼ� A�� �����ϰ��ִ��� �˻�?
		}
		
		System.out.println(count);
		
		sc.close();
	}
}
