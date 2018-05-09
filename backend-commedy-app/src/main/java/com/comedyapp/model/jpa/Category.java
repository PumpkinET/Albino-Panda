package com.comedyapp.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {
	@Id
	@Column(name="id")
	int id;
	
	@Column(name="name")
	String name;
	
	@Column(name="imgSrc")
	String imgSrc;
	
	@Column(name="amount")
	int amount;
	
	@Column(name="description")
	String description;
	
	public Category() {
		
	}

	public Category(int id, String name, String imgSrc, int amount, String description) {
		this.id = id;
		this.name = name;
		this.imgSrc = imgSrc;
		this.amount = amount;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public int getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
