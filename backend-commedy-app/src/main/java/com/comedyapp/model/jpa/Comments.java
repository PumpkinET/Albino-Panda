package com.comedyapp.model.jpa;



import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "comments")
public class Comments {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Column(name = "sub_comment")
	long sub_comment;

	@Column(name = "imgSrc")
	String imgSrc;

	@Column(name = "content")
	String content;

	@Column(name = "post_id")
	long postID;

	@Column(name = "userinfo")
	String username;
	
	@Column(name="amount")
	int amount;
	
	@ManyToOne
	@JoinColumn(name = "userinfo", insertable = false, updatable = false)
	private User user_comments;
	
	@OneToMany(mappedBy="user_sub_comments")
	private Set<SubComments> user_sub_comments;
	
	public Comments() {
	}
	
	public long getSub_comment() {
		return sub_comment;
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


	public void setContent(String content) {
		this.content = content;
	}


	public void setPostID(long postID) {
		this.postID = postID;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

	
}
