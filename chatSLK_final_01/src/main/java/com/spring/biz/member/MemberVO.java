package com.spring.biz.member;

public class MemberVO {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String phonenumber;
	private String domain;
	private String email;
	private String role;
	
	
	private String searchCondition;
	private String tmpPw;
	
	
	public String getTmpPw() {
		return tmpPw;
	}
	public void setTmpPw(String tmpPw) {
		this.tmpPw = tmpPw;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return memberId+" | "+memberPw+" | "+memberName +" | "+phonenumber;
	}
	
	
}
