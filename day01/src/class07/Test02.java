package class07;

public class Test02 {

	public static void main(String[] args) {
		
		
		// 연습문제 1
		int a = 100;
		int b = 200;
		// ★ 풀이 1
		int res = a > b ? a : b; // 정답을 저장할 변수
		
		// 풀이 2
		int res2 = a < b ? b : a;

		System.out.println(res);
		System.out.println(res2);
		
		
		
		// 연습문제 2
		int c = 9;
		// 풀이 1
		System.out.println(c%2 == 0 ? 'E' : 'O');
		
		// 풀이 2
		System.out.println(c%2 != 0 ? 'O' : 'E');
		
		// ★ 풀이 3
		char res3 = c%2 == 0 ? 'E' : 'O';
		System.out.println(res3);

	}

}
