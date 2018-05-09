package com.comedyapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.comedyapp.model.jpa.Category;

@Repository
public interface CategoriesDAO extends CrudRepository<Category, Long> {
    List<Category> findById(int id);
    Category findByName(String category);
}
