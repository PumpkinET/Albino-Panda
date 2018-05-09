package com.comedyapp.model.rest;

public class PostComment {
	private Long id;
	private String content;
	public Long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	@Override
	public String toString() {
		return "PostComment [id=" + id + ", content=" + content + "]";
	}
	
	
}
