package com.spring.biz.pay;

import java.util.Date;

public class PayVO {
	private int payNum;
	private String payMethod;
	private String memberId;
	private int productNum;
	private String status;
	private String zipcode;
	private String address;
	private String addressDetail;
	private int addressNum;
	private Date payTime;
	private String time;

	// 칼럼에는 없는 친구들 
	private String path;
	private String productName;
	private String company;
	private String productPrice;
	private int payCount;
	private boolean check;

	private String searchCondition;

	
	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public int getPayNum() {
		return payNum;
	}
	public void setPayNum(int payNum) {
		this.payNum = payNum;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public int getAddressNum() {
		return addressNum;
	}
	public void setAddressNum(int addressNum) {
		this.addressNum = addressNum;
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
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public int getPayCount() {
		return payCount;
	}
	public void setPayCount(int payCount) {
		this.payCount = payCount;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return payNum + " | "+memberId;
	}



}
