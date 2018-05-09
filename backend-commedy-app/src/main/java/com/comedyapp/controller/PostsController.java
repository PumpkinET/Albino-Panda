package com.comedyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.comedyapp.model.jpa.Posts;
import com.comedyapp.model.rest.CreatePost;
import com.comedyapp.model.rest.UpdateLikeAction;
import com.comedyapp.model.rest.UploadPostImage;
import com.comedyapp.service.PostsService;
import com.comedyapp.service.UserService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PostsController {

	@Autowired
	PostsService postsService;

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/posts/", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Posts> listPosts() {
		return postsService.findAll();
	}

	@RequestMapping(value = "/posts/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Optional<Posts> get(@PathVariable Long id) {
		return postsService.findById(id);
	}
	
	@RequestMapping(value = "/postsByCategory/{category}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Posts> getByCategory(@PathVariable String category) {
		return postsService.findByCategory(category);
	}

	@RequestMapping(value = "/usernamePosts/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Posts> getByUsername(@PathVariable String username) {
		return postsService.usernamePosts(username);
	}

	@RequestMapping(value = "/myProfile/", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Posts> getByUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return postsService.usernamePosts(username);
	}

	@RequestMapping(value = "/createPost/", method = RequestMethod.POST)
	public UploadPostImage createPost(@RequestBody CreatePost post) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		File file = new File("C:\\Users\\Dell Latitude\\Desktop\\skinnycodes-2018\\comedyapp-1\\target\\classes\\static\\data\\posts\\"+username+"\\");
		if (!file.exists()) {
			new File("C:\\Users\\Dell Latitude\\Desktop\\skinnycodes-2018\\comedyapp-1\\target\\classes\\static\\data\\posts\\"+username+"\\").mkdirs();
		}
		Posts temp = postsService.save(username, post);
		temp.setUser(this.userService.findOne(temp.getUsername()));
		return new UploadPostImage(username, (int) temp.getId(), temp);
	}

	@RequestMapping(value = "/deletePost/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public void deleteComment(@PathVariable Long id) {
		postsService.delete(id);
	}
	
	@RequestMapping(value = "/postAction/{id}/{action}/{operator}", method = RequestMethod.GET)
	public UpdateLikeAction postCommands(@PathVariable Long id, @PathVariable int action, @PathVariable int operator) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int returnOperator = postsService.postCommands(auth.getName(), id, action/*, operator*/);
		return new UpdateLikeAction(returnOperator);
	}

	@RequestMapping(value = "/upload/{username}/{id}", method = RequestMethod.POST)
	public void UploadFile(@PathVariable String username,@PathVariable int id, MultipartHttpServletRequest request) throws IOException {
		Iterator<String> itr = request.getFileNames();
		MultipartFile file = request.getFile(itr.next());
		File dir = new File("C:\\Users\\Dell Latitude\\Desktop\\skinnycodes-2018\\comedyapp-1\\target\\classes\\static\\data\\posts\\"+username+"\\");
		if (dir.isDirectory()) {
			File serverFile = new File(dir, username+"_post_id_"+id+".png");
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(file.getBytes());
			stream.close();
		}
	}
}
