package com.comedyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.comedyapp.model.jpa.User;
import com.comedyapp.model.rest.Register;
import com.comedyapp.model.rest.UpdateProfile;
import com.comedyapp.model.rest.UploadPostImage;
import com.comedyapp.service.UserService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> listUser() {
		return userService.findAll();
	}

	@RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
	public User getOne(@PathVariable(value = "username") String username) {
		return userService.findOne(username);
	}

	@RequestMapping(value = "/profileUser", method = RequestMethod.GET)
	public User getProfileUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return userService.findOne(username);
	}

	@RequestMapping(value = "/updateProfile/", method = RequestMethod.POST, headers = "Accept=application/json")
	public UploadPostImage updateProfile(@RequestBody UpdateProfile user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		user.setImgSrc("http://albinopanda.net:8080/data/profile/" + username + "/" + username + ".png");
		userService.updateProfile(username, user);
		return new UploadPostImage(username, 0, null);
	}

	@RequestMapping(value = "/updateProfileImage/{username}", method = RequestMethod.POST)
	public void updateProfileImage(@PathVariable String username, MultipartHttpServletRequest request) {
		Iterator<String> itr = request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		
		File dir = new File("C:\\Users\\Dell Latitude\\Desktop\\skinnycodes-2018\\comedyapp-1\\target\\classes\\static\\data\\profile\\" + username + "\\");

		if (!dir.exists()) {
			new File("C:\\Users\\Dell Latitude\\Desktop\\skinnycodes-2018\\comedyapp-1\\target\\classes\\static\\data\\profile\\" + username + "\\").mkdirs();
		}
		
		File serverFile = new File(dir, username+".png");
		BufferedOutputStream stream = null;
		try {
			stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stream.write(file.getBytes());
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@RequestMapping(value = "/register/", method = RequestMethod.POST, headers = "Accept=application/json")
	public UploadPostImage register(@RequestBody Register user) {
		boolean test = false;
		if (user.getDescription() != null && user.getEmail() != null && user.getEmail() != null
				&& user.getGender() != null  && user.getPassword() != null
				&& user.getUsername() != null)
		{
			user.setImgSrc("http://albinopanda.net:8080/data/profile/" + user.getUsername() + "/" + user.getUsername() + ".png");
			test = userService.save(user);
		}
		return new UploadPostImage(user.getUsername(), test==true? 1 : 0, null);		
	}

}
	