package com.stackroute.userservice.model;


import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class User {
	
	@Id	
	private String userId;
	
	private String password;	
	private String firstname;
	private String secondname;
	//private String gender;
	
	
	public User(String userId,String password,String firstname, String secondname)
	{
		this.userId=userId;
		this.password=password;
		this.firstname=firstname;
		this.secondname=firstname;
		//this.gender=gender;
		
	}
	public User() {
		
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getSecondname() {
		return secondname;
	}
	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}
	

	
}
