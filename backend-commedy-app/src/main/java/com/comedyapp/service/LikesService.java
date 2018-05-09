package com.comedyapp.service;

import java.util.List;

import com.comedyapp.model.jpa.Likes_Log;
import com.comedyapp.model.rest.LikesLogInfo;


public interface LikesService {

	List<Likes_Log> findAll();
	List<Likes_Log> find(String username);
	
	LikesLogInfo likes_log(String username, Long post_id, int action);
}
