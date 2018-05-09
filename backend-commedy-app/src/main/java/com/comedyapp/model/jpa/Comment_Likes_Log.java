package com.comedyapp.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment_likes_log")
public class Comment_Likes_Log {
	@Id
	@Column(name = "id")
	long id;
	
	@Column(name = "user_like_info")
	String username;
	
	@Column(name = "comment_id_like")
	int commentId;
	
	@Column(name = "comment_action")
	int commentAction;
	
	@Column(name="post_id")
	int postId;
	
	public Comment_Likes_Log() {
		
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public int getCommentAction() {
		return commentAction;
	}

	public void setCommentAction(int commentAction) {
		this.commentAction = commentAction;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	

	
	
}
