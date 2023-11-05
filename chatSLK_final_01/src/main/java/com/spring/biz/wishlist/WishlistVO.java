package com.spring.biz.wishlist;

public class WishlistVO {
	private int wishlistNum;
	private String wishlistTime;
	private String memberId;
	private int productNum;
	
	private String path;
	private String productName;
	private String company;
	private int productPrice;
	
	private int wishCnt;
	
	private String searchCondition;
	
	
	
	public int getWishCnt() {
		return wishCnt;
	}
	public void setWishCnt(int wishCnt) {
		this.wishCnt = wishCnt;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public int getWishlistNum() {
		return wishlistNum;
	}
	public void setWishlistNum(int wishlistNum) {
		this.wishlistNum = wishlistNum;
	}
	public String getWishlistTime() {
		return wishlistTime;
	}
	public void setWishlistTime(String wishlistTime) {
		this.wishlistTime = wishlistTime;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
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
	@Override
	public String toString() {
		return this.memberId+this.productNum;
	}
	
	
}
