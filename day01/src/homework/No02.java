package homework;

public class No02 {

	public static void main(String[] args) {
		
		int age = 2;
		
		if ( 8 <= age && age <= 19 ) {
			System.out.println("1000원");
		}
		
		else if ( 20 <= age && age <= 65) {
			System.out.println("2000원");
		}
		
		else {
			System.out.println("무료!");
		}

	}

}
