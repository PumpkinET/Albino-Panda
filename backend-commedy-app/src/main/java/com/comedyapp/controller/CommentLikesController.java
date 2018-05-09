package com.comedyapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comedyapp.model.jpa.Comment_Likes_Log;
import com.comedyapp.service.CommentLikesService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CommentLikesController {

	@Autowired
	CommentLikesService commentLikesService;
	
//	@RequestMapping(value = "/commentLikes/", method = RequestMethod.GET,  headers = "Accept=application/json")
//	public List<Comment_Likes_Log> listCategories() {
//		return commentLikesService.findAll();
//	}
	
	@RequestMapping(value = "/commentLikes/{id}", method = RequestMethod.GET,  headers = "Accept=application/json")
	public List<Comment_Likes_Log> likes(@PathVariable int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return commentLikesService.findByUsernameAndPostId(username, id);
	}
}
