package com.comedyapp.model.jpa;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class User {

    @Id
    @Column
    private String username;
    
    @Column
    @JsonIgnore
    private String password;

	@Column(name="description")
	String description;
	
	@Column(name="imgSrc")
	String imgSrc;
	
	@Column
    private String email;
	
	@Column(name="gender")
    private int gender;
	
    @OneToMany(mappedBy="user")
	private Set<Posts> posts;
    
    @OneToMany(mappedBy="user_comments")
	private Set<Comments> comments;
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
	public String getDescription() {
		return description;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
	
}
