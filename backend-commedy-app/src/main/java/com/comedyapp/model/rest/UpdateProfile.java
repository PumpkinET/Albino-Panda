package com.comedyapp.model.rest;

public class UpdateProfile {
	private String description;
	private String imgSrc;
	
	public String getDescription() {
		return description;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	@Override
	public String toString() {
		return "UpdateProfile [description=" + description + ", imgSrc=" + imgSrc + "]";
	}
	
	
}
