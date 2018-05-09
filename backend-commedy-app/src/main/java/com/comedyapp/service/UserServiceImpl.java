package com.comedyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.comedyapp.dao.UserDAO;
import com.comedyapp.model.jpa.User;
import com.comedyapp.model.rest.Register;
import com.comedyapp.model.rest.UpdateProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;// register purpose

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		userDAO.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		userDAO.deleteById(id);
		// userDAO.delete(id);
	}

	@Override
	public User findOne(String username) {
		return userDAO.findByUsername(username);
	}

	@Override
	public Optional<User> findById(Long id) {
		return userDAO.findById(id);
		// return userDAO.findOne(id);
	}

	@Override
	public boolean save(Register user) {
		if (this.findOne(user.getUsername()) != null) {
			System.out.println("Username already exists");
			return false;
		}
		User signup = new User();
		signup.setUsername(user.getUsername());
		signup.setPassword(this.bcryptEncoder.encode(user.getPassword()));
		signup.setEmail(user.getEmail());
		signup.setGender(user.getGender());
		signup.setImgSrc(user.getImgSrc());
		signup.setDescription(user.getDescription());
		userDAO.save(signup);
		System.out.println("Username not exists");
		return true;
	}

	@Override
	public void updateProfile(String username, UpdateProfile user) {
		User current = this.findOne(username);
		boolean bUpdate = false;

		if (current.getDescription() == null || current.getDescription().equals(user.getDescription()) == false) {
			current.setDescription(user.getDescription());
			bUpdate = true;
		}

		if (current.getImgSrc() == null || current.getImgSrc().equals(user.getImgSrc()) == false) {
			current.setImgSrc(user.getImgSrc());
			bUpdate = true;
		}

		if (bUpdate == true)
			userDAO.save(current);
	}
}
