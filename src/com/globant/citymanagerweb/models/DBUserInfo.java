package com.globant.citymanagerweb.models;

public abstract class DBUserInfo {

	private String uid;
	private String pwd;
	private String cat;
	
	public DBUserInfo() {
		
	}
	
	public DBUserInfo(String userId, String password, String catalog) {
		uid = userId;
		pwd = password;
		cat = catalog;
	}

	public String getUserId() {
		return uid;
	}

	public void setUserId(String uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return pwd;
	}

	public void setPassword(String pwd) {
		this.pwd = pwd;
	}

	public String getCatalog() {
		return cat;
	}

	public void setCatalog(String cat) {
		this.cat = cat;
	}
}
