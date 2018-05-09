package com.comedyapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.comedyapp.model.jpa.SubComments;

@Repository
public interface SubCommentsDAO extends CrudRepository<SubComments, Long> {
    List<SubComments> findById(long id);
    List<SubComments> findBySubComment(long id);
}
