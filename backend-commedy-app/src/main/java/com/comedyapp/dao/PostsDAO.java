package com.comedyapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.comedyapp.model.jpa.Posts;

@Repository
public interface PostsDAO extends CrudRepository<Posts, Long> {
    List<Posts> findByUsername(String username);
    List<Posts> findByCategory(String category);
}
