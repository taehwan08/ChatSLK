package com.spring.biz.cart;

public class CartVO {
	private int cartNum;
	private int productNum;
	private String memberId;
	private int cartCount;
	
	private String path;
	private String productName;
	private String company;
	private int productPrice;
	private String searchCondition;
	
	private int tmpcnt;
	
	
	
	public int getTmpcnt() {
		return tmpcnt;
	}
	public void setTmpcnt(int tmpcnt) {
		this.tmpcnt = tmpcnt;
	}
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getCartCount() {
		return cartCount;
	}
	public void setCartCount(int cartCount) {
		this.cartCount = cartCount;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	@Override
	public String toString() {
		return "[cartNum=" + cartNum + ", productNum=" + productNum + ", memberId=" + memberId + ", cartCount="
				+ cartCount + "]";
	}
	
	
	
}
