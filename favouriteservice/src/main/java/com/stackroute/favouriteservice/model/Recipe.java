package com.stackroute.favouriteservice.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document
public class Recipe {
	
	@Id
	String id;
	String image;
	String url;
	String title;
	String userid;
	
	//ArrayList<String> incredientArray;
	ArrayList<Ingredients> ingredientArray;
	
	public Recipe(String id,String title,String image,	String url, ArrayList<Ingredients> ingredients, String userid) {
	this.id=id;
		this.title=title;
		this.image=image;
		this.url=url;
		this.ingredientArray=ingredients;
		this.userid = userid;
	}
	
	public Recipe() {
		
	}

	public ArrayList<Ingredients> getIngredientArray() {
		return ingredientArray;
	}

	public void setIngredientArray(ArrayList<Ingredients> ingredientArray) {
		this.ingredientArray = ingredientArray;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", image=" + image + ", url=" + url + ", title=" + title + ", userid=" + userid
				+ ", ingredientArray=" + ingredientArray + "]";
	}
	

}
