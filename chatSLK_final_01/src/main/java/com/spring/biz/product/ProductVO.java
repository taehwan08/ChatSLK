package com.spring.biz.product;

public class ProductVO {
	private int productNum;
	private int categoryNum;
	private String categoryName;
	private int categoryDetailNum;
	private String categoryDetailName;
	private String productName;
	private String company;
	private String path;
	private int productPrice;
	private int productCnt;
	private String productExplain;
	
	//임시변수
	private int star;
	private String searchCondition; 
	private int listcnt;
	private boolean check;
	private String searchKeyword;
	


	
	
	
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public int getListcnt() {
		return listcnt;
	}
	public void setListcnt(int listcnt) {
		this.listcnt = listcnt;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDetailName() {
		return categoryDetailName;
	}
	public void setCategoryDetailName(String categoryDetailName) {
		this.categoryDetailName = categoryDetailName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getProductExplain() {
		return productExplain;
	}
	public void setProductExplain(String productExplain) {
		this.productExplain = productExplain;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public int getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}
	public int getCategoryDetailNum() {
		return categoryDetailNum;
	}
	public void setCategoryDetailNum(int categoryDetailNum) {
		this.categoryDetailNum = categoryDetailNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductCnt() {
		return productCnt;
	}
	public void setProductCnt(int productCnt) {
		this.productCnt = productCnt;
	}
	@Override
	public String toString() {
		return "상품번호 : " + this.productNum + "상품이름 : " + this.productName + 
				"상품가격 : " + this.productPrice + "상품설명 : " + this.productExplain;
	}



}