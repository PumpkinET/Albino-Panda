package com.comedyapp.model.jpa;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "sub_comments")
public class SubComments {

	@Id
	@Column(name = "id")
	long id;

	@Column(name = "img_src")
	String imgSrc;

	@Column(name = "content")
	String content;

	@Column(name = "post_id")
	long postID;

	@Column(name = "userinfo")
	String username;
	
	@Column(name = "sub_comment")
	long subComment;
	
	@ManyToOne
	@JoinColumn(name = "userinfo", insertable = false, updatable = false)
	private User user_comments;
	
	@ManyToOne
	@JoinColumn(name = "sub_comment", insertable = false, updatable = false)
	private Comments user_sub_comments;
	

	public SubComments() {
	}
	

	public Comments getUser_sub_comments() {
		return user_sub_comments;
	}


	public long getPostID() {
		return postID;
	}
	public long getId() {
		return id;
	}

	public String getImgSrc() {
		//return imgSrc;
		return "soon";
	}

	public String getContent() {
		return content;
	}
	
	public String getUsername() {
		return username;
	}
	
	public User getUser_comments() {
		return user_comments;
	}

	public long getSubComment() {
		return subComment;
	}
	
}
