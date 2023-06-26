package class08;

// 양자택일
public class Test02 {
	
	public static void main(String[] args) {
		/*
		
		int a = 9;
		
		if(a%2 == 0) { // a가 짝수라면
			System.out.println("짝수입니다.");
		}
		if(a%2 != 0) {
			System.out.println("홀수입니다.");
		}
		
		*/
		// ↑ a값과 무관하게 연산자 5번 사용
		
		// ↓ a값과 무관하게 연산자 3번 사용
		int a = 9;
		
		if(a%2 == 0) { // a가 짝수라면
			System.out.println("짝수입니다.");
		}
		else { // if문이 거짓이되면 전부 else -> 조건식 XXX
			System.out.println("홀수입니다.");
		}
		
		// 결론
		// 양자택일의 상황이라면
		// if-else를 사용한다 !
		
	}

}
