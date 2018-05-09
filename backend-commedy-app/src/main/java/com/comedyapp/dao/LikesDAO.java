package com.comedyapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.comedyapp.model.jpa.Likes_Log;


@Repository
public interface LikesDAO extends CrudRepository<Likes_Log, Long> {
    List<Likes_Log> findById(int id);
    List<Likes_Log> findByUsername(String username);
    List<Likes_Log> findByPostId(int id);
}
