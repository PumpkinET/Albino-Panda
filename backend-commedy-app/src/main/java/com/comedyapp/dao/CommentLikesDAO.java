package com.comedyapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.comedyapp.model.jpa.Comment_Likes_Log;


@Repository
public interface CommentLikesDAO extends CrudRepository<Comment_Likes_Log, Long> {
    List<Comment_Likes_Log> findById(int id);
    List<Comment_Likes_Log> findByUsername(String username);
    List<Comment_Likes_Log> findByCommentId(int id);
    List<Comment_Likes_Log> findByUsernameAndPostId(String username, int id);
}
