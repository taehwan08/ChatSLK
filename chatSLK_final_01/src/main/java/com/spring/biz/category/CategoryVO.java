package com.spring.biz.category;

public class CategoryVO {
	private int categoryNum;
	private String categoryName;
	private int listcnt;
	private String searchCondition;
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public int getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getListcnt() {
		return listcnt;
	}
	public void setListcnt(int listcnt) {
		this.listcnt = listcnt;
	}
	
	
}
