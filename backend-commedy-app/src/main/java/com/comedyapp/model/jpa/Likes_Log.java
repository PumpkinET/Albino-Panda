package com.comedyapp.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "likes_log")
public class Likes_Log {
	@Id
	@Column(name = "id")
	long id;
	
	@Column(name = "user_like_info")
	String username;
	
	@Column(name = "post_id_like")
	int postId;
	
	@Column(name = "post_action")
	int postAction;
	
	public Likes_Log() {
		
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public int getPostId() {
		return postId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getPostAction() {
		return postAction;
	}

	public void setPostAction(int postAction) {
		this.postAction = postAction;
	}
	
	

	
	
}
