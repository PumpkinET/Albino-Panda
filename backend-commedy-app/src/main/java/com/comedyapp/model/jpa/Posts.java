package com.comedyapp.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "posts")
public class Posts {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@Column(name = "title")
	String title;

	@Column(name = "content")
	String content;

	@Column(name = "okay")
	int okayAmount;

	@Column(name = "forever_alone")
	int forever_aloneAmount;

	@Column(name = "me_gusta")
	int me_gustaAmount;

	@Column(name = "omg")
	int omgAmount;

	@Column(name = "isURL")
	int isURL;

	@Column(name = "username")
	String username;

	@Column(name= "category")
	String category;
	
	@Column(name="amount")
	int amount;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@JsonInclude()
	@Transient
	boolean isSelected;
	
	@JsonInclude()
	@Transient
	int action = 10;
	
	@ManyToOne
	@JoinColumn(name = "username", insertable = false, updatable = false)
	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public Posts() {
	}

	public int getIsURL() {
		return isURL;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public int getOkayAmount() {
		return okayAmount;
	}

	public int getForever_aloneAmount() {
		return forever_aloneAmount;
	}

	public int getMe_gustaAmount() {
		return me_gustaAmount;
	}

	public int getOmgAmount() {
		return omgAmount;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setOkayAmount(int okayAmount) {
		this.okayAmount = okayAmount;
	}

	public void setForever_aloneAmount(int forever_aloneAmount) {
		this.forever_aloneAmount = forever_aloneAmount;
	}

	public void setMe_gustaAmount(int me_gustaAmount) {
		this.me_gustaAmount = me_gustaAmount;
	}

	public void setOmgAmount(int omgAmount) {
		this.omgAmount = omgAmount;
	}

	public void setIsURL(int isURL) {
		this.isURL = isURL;
	}

	public User getUser() {
		return user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public int getAction() {
		return action;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
