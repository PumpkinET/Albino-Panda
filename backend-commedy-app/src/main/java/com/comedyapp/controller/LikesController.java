package com.comedyapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comedyapp.model.jpa.Likes_Log;
import com.comedyapp.service.LikesService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LikesController {

	@Autowired
	LikesService likesService;
	
//	@RequestMapping(value = "/likes/", method = RequestMethod.GET,  headers = "Accept=application/json")
//	public List<Likes_Log> listCategories() {
//		return likesService.findAll();
//	}
	
	@RequestMapping(value = "/likes/", method = RequestMethod.GET,  headers = "Accept=application/json")
	public List<Likes_Log> likes() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return likesService.find(username);
	}

}
