package com.comedyapp.service;

import java.util.List;

import com.comedyapp.model.jpa.Comments;


public interface CommentsService {

	List<Comments> findAll();

	Comments findById(Long id);
	
	List<Comments> findByPost(Long id);
	
	Comments postComment(Long id, String username, String content);
	
	int postLike(String username, Long id, int action, int postId);
	
	void deleteComment(Long id);
	
}
