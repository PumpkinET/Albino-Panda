package com.comedyapp.service;

import java.util.List;

import com.comedyapp.model.jpa.SubComments;


public interface SubCommentsService {

	List<SubComments> findAll();
	List<SubComments> get(Long id);
}
