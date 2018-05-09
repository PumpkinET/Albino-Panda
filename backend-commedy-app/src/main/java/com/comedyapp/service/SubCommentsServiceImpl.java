package com.comedyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comedyapp.dao.SubCommentsDAO;
import com.comedyapp.model.jpa.SubComments;

import java.util.ArrayList;
import java.util.List;

@Service(value = "subCommentsService")
public class SubCommentsServiceImpl implements SubCommentsService {

	@Autowired
	private SubCommentsDAO subCommentsDAO;
	
	@Override
	public List<SubComments> findAll() {
		List<SubComments> list = new ArrayList<>();
		subCommentsDAO.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public List<SubComments> get(Long id) {
		return subCommentsDAO.findBySubComment(id);
	}
}
