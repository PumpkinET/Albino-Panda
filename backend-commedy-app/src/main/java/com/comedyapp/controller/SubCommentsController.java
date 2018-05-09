package com.comedyapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comedyapp.model.jpa.SubComments;
import com.comedyapp.service.SubCommentsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SubCommentsController {

	@Autowired
	SubCommentsService subCommentsService;
	
	@RequestMapping(value = "/subComments/", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<SubComments> getAll() {
		return subCommentsService.findAll();
	}
	
	@RequestMapping(value = "/subComments/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<SubComments> get(@PathVariable Long id) {
		return subCommentsService.get(id);
	}
}
