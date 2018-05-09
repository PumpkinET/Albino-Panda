package com.comedyapp.service;

import java.util.List;

import com.comedyapp.model.jpa.Category;


public interface CategoriesService {

	List<Category> findAll();
	
	void update(String category);
	
	void sub_update(String category);

}
