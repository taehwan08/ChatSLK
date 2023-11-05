package com.spring.biz.images;

public class ImagesVO {
	private int imageNum;
	private String path;
	private int productNum;

	//각자 상품이미지파일 경로 지정
//	static String path2="D:\\lee\\workspace\\final\\src\\main\\webapp\\assets\\productimg\\"; // 이광원
	static String path2="/Users/taehwan/Desktop/TAEHWAN/Springws/chatSLK_final_01/src/main/webapp/assets/productimg/"; // 김태환
//	static String path2="C:\\Users\\suha0\\OneDrive\\바탕 화면\\SUHA\\Spring_workspace\\final_test5\\src\\main\\webapp\\assets\\productimg\\"; // 신수하
//	static String path2="D:\\lee\\workspace\\final\\src\\main\\webapp\\assets\\productimg\\";
//	static String path2="D:\\lee\\workspace\\final\\src\\main\\webapp\\assets\\productimg\\";

	private String searchCondition;



	public String getSearchCondition() {
		return searchCondition;
	}


	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public int getImageNum() {
		return imageNum;
	}


	public void setImageNum(int imageNum) {
		this.imageNum = imageNum;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public int getProductNum() {
		return productNum;
	}


	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}


	public static String getPath2() {
		return path2;
	}


	public static void setPath2(String path2) {
		ImagesVO.path2 = path2;
	}


	@Override
	public String toString() {
		return "imgNum : "+imageNum+" productNum : "+productNum+" path : "+path;
	}

}
