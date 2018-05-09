package com.comedyapp.service;

import java.util.List;
import java.util.Optional;

import com.comedyapp.model.jpa.Posts;
import com.comedyapp.model.rest.CreatePost;


public interface PostsService {

	List<Posts> findAll();

	List<Posts> usernamePosts(String username);
	
	List<Posts> findByCategory(String category);
	
	void delete(long id);

	Optional<Posts> findById(Long id);
	
	int postCommands(String username, Long id, int action/*, int operator*/);
	
	Posts save(String username, CreatePost post);
	
}
