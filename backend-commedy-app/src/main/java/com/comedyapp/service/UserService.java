package com.comedyapp.service;

import java.util.List;
import java.util.Optional;

import com.comedyapp.model.jpa.User;
import com.comedyapp.model.rest.Register;
import com.comedyapp.model.rest.UpdateProfile;

public interface UserService {

	boolean save(Register user);
	
	void updateProfile(String username, UpdateProfile user);
	
	List<User> findAll();

	void delete(long id);

	User findOne(String username);

	Optional<User> findById(Long id);
}
