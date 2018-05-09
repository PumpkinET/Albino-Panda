package com.comedyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comedyapp.dao.CategoriesDAO;
import com.comedyapp.model.jpa.Category;

import java.util.ArrayList;
import java.util.List;

@Service(value = "categoriesService")
public class CategoriesServiceImpl implements CategoriesService {

	@Autowired
	private CategoriesDAO categoryDAO;
	
	@Override
	public List<Category> findAll() {
		List<Category> list = new ArrayList<>();
		categoryDAO.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void update(String category) {
		Category temp = categoryDAO.findByName(category);
		temp.setAmount(temp.getAmount() + 1);
		categoryDAO.save(temp);
	}

	@Override
	public void sub_update(String category) {
		Category temp = categoryDAO.findByName(category);
		temp.setAmount(temp.getAmount() - 1);
		categoryDAO.save(temp);
	}
}
