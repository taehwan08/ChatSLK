package com.spring.biz.common;

public class AlertVO {
	private String title;
	private String msg;
	private String icon;
	private String confirmtext;
	private String canceltext;
	private String path;
	
	private String searchCondition;
	
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getConfirmtext() {
		return confirmtext;
	}
	public void setConfirmtext(String confirmtext) {
		this.confirmtext = confirmtext;
	}
	public String getCanceltext() {
		return canceltext;
	}
	public void setCanceltext(String canceltext) {
		this.canceltext = canceltext;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
