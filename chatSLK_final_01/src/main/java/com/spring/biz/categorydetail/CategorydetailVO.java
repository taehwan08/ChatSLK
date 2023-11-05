package com.spring.biz.categorydetail;

public class CategorydetailVO {
	private int categoryDetailNum;
	private int categoryNum;
	private String categoryDetailName;
	private String categoryName;
	
	private int listcnt;
	private int categoryCnt;
	private int categoryDetailCnt;
	private String searchCondition;
	
	
	
	public int getCategoryCnt() {
		return categoryCnt;
	}
	public void setCategoryCnt(int categoryCnt) {
		this.categoryCnt = categoryCnt;
	}
	public int getCategoryDetailCnt() {
		return categoryDetailCnt;
	}
	public void setCategoryDetailCnt(int categoryDetailCnt) {
		this.categoryDetailCnt = categoryDetailCnt;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String serchCondition) {
		this.searchCondition = serchCondition;
	}
	public int getCategoryDetailNum() {
		return categoryDetailNum;
	}
	public void setCategoryDetailNum(int categoryDetailNum) {
		this.categoryDetailNum = categoryDetailNum;
	}
	public int getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}
	public String getCategoryDetailName() {
		return categoryDetailName;
	}
	public void setCategoryDetailName(String categoryDetailName) {
		this.categoryDetailName = categoryDetailName;
	}
	public int getListcnt() {
		return listcnt;
	}
	public void setListcnt(int listcnt) {
		this.listcnt = listcnt;
	}
	@Override
	public String toString() {
		return "세부카테고리 : "+this.categoryDetailNum+"번, "+this.categoryDetailName+"\n카테고리 번호 : "+categoryNum;
	}
	
	
}
