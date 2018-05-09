package com.comedyapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comedyapp.model.jpa.Category;
import com.comedyapp.service.CategoriesService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CategoriesController {

	@Autowired
	CategoriesService categoriesService;
	
	@RequestMapping(value = "/categories/", method = RequestMethod.GET,  headers = "Accept=application/json")
	public List<Category> listCategories() {
		return categoriesService.findAll();
	}
	

}
