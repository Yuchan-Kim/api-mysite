package com.javaex.vo;

public class User {
	private int userNum;
	private String userId;
	private String userName;
	private String userPw;
	private String userGender;

	public User() {

	}
	public User(String userId,String userName, String userPw,  String userGender) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userGender = userGender;
	}
	
	public User(String userId, String userPw) {
		this.userId = userId;
		this.userPw = userPw;
	}

	public User(String userId) {
		this.userId = userId;

	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	@Override
	public String toString() {
		return "User [userNum=" + userNum + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userGender=" + userGender + "]";
	}




}
