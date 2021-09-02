package com.stackroute.favouriteservice.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
	@Id
	String userid;
	List<Recipe> recipe;
	
	@Override
	public String toString() {
		return "User [userId=" + userid + ",  recipe=" + recipe + "]";
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userId, List<Recipe> recipe) {
		super();
		this.userid = userId;
		this.recipe = recipe;
	}

	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<Recipe> getRecipe() {
		return recipe;
	}
	public void setRecipe(List<Recipe> recipe) {
		this.recipe = recipe;
	}
	
	
	

}
