package com.comedyapp.service;

import java.util.List;

import com.comedyapp.model.jpa.Comment_Likes_Log;


public interface CommentLikesService {

	List<Comment_Likes_Log> findAll();
	List<Comment_Likes_Log> find(String username);
	List<Comment_Likes_Log> findByUsernameAndPostId(String username, int id);
	
	int comment_likes_log(String username, Long comment_id, int action,  int postId);
}
