package com.comedyapp.model.rest;


import com.comedyapp.model.jpa.Comments;
import com.comedyapp.model.jpa.User;

public class PostCommentBlock {
	private Comments comment;
	private User user;
	public Comments getComment() {
		return comment;
	}
	public void setComment(Comments comment) {
		this.comment = comment;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public PostCommentBlock(Comments comment, User user) {
		super();
		this.comment = comment;
		this.user = user;
	}
	@Override
	public String toString() {
		return "PostCommentBlock [comment=" + comment.toString() + ", user=" + user.toString() + "]";
	}
	
	
}
