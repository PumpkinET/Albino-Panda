package com.comedyapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.comedyapp.model.jpa.User;

@Repository
public interface UserDAO extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
