package com.stackroute.favouriteservice.model;

import org.springframework.data.annotation.Id;

public class Ingredients {
	@Id
	private String id;
	private String name;         
	private String amount;
	private String unit;
	
	
	
	
	public Ingredients(String name, String amount, String unit) {
		super();
		this.name = name;
		this.amount = amount;
		this.unit = unit;
	}
	
	
	public Ingredients() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
     
       

}
