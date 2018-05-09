package com.comedyapp.model.rest;

import com.comedyapp.model.jpa.Posts;

public class UploadPostImage {
	private String username;
	private int id;
	private Posts post;
	public UploadPostImage(String username, int id, Posts post) {
		super();
		this.username = username;
		this.id = id;
		this.post = post;
	}
	public String getUsername() {
		return username;
	}
	public int getId() {
		return id;
	}
	public Posts getPost() {
		return post;
	}
	
}
