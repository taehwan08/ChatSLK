package class06;

public class Test04 {
	
	public static void main(String[] args) {
		
		 // 나이가 19살 이상부터 45살 미만까지만 가능하다. 
		 // int age;	// 나이라는 value를 저장할 변수 공간
		 // age >= 19 그리고,AND age < 45
		//		-> &&
				
		int age=30;
				System.out.println(age >= 19 && age < 45);
				
		// 가격이 1000원 이하 이거나 혹은 20000원 초과된 상품인가요?
		// int price;	// 가격이라는 value를 저장할 변수 공간
		// price <= 1000 이거나, 혹은,또는, OR price > 20000
							// -> ||
									
		int price=1500;
				System.out.println(price <= 1000 || price > 20000);
				
				
		boolean data = true;
		System.out.println(data);
		System.out.println(!data);
	}

}
