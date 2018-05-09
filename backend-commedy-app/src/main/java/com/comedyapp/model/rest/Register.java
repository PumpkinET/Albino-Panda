package com.comedyapp.model.rest;

public class Register {
    private String username;
    private String password;
	private String description;
	private String imgSrc;
    private String email;
    private Integer gender;

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getDescription() {
		return description;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public String getEmail() {
		return email;
	}
	public Integer getGender() {
		return gender;
	}
	@Override
	public String toString() {
		return "RegisterUser [username=" + username + ", password=" + password + ", description=" + description
				+ ", imgSrc=" + imgSrc + ", email=" + email + ", gender=" + gender + "]";
	}
    
	
}
