package com.comedyapp.model.rest;

public class CreatePost {
	private String title;
	private int postType;
	private String content;
	private String selectedCategory;
	
	public String getTitle() {
		return title;
	}
	public int getPostType() {
		return postType;
	}
	public String getContent() {
		return content;
	}
	public String getSelectedCategory() {
		return selectedCategory;
	}
	@Override
	public String toString() {
		return "CreatePost [title=" + title + ", postType=" + postType + ", content=" + content + ", selectedCategory="
				+ selectedCategory + "]";
	}
	
	
}
