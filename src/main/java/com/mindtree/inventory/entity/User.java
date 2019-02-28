package com.mindtree.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	private String userName;
	
	@Column(unique = true)
	private String email;
	
	private String userPassword;
	
	@Column(nullable = true)
	private String role;
	
	public User() {
		super();
	}
	
	public User(int userId, String userName, String userPassword, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.email = email;
	}
	
	public User(String userName, String userPassword, String email) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.email = email;
	}

	public User(String userName, String userPassword, String email, String role) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.email = email;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", email="
				+ email + ", role=" + role + "]";
	}
	
	
}
