package com.comedyapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.comedyapp.model.jpa.Comments;

@Repository
public interface CommentsDAO extends CrudRepository<Comments, Long> {
    List<Comments> findById(long id);
    List<Comments> findByPostID(long id);
}
