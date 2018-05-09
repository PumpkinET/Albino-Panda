package com.comedyapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comedyapp.model.jpa.Comments;
import com.comedyapp.model.rest.PostComment;
import com.comedyapp.model.rest.PostCommentBlock;
import com.comedyapp.model.rest.UpdateLikeAction;
import com.comedyapp.service.CommentsService;
import com.comedyapp.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CommentsController {

	@Autowired
	CommentsService commentsService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/comments/", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Comments> getAll() {
		return commentsService.findAll();
	}
	
	@RequestMapping(value = "/comments/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Comments get(@PathVariable Long id) {
		return commentsService.findById(id);
	}
	
	@RequestMapping(value = "/postComments/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Comments> getComments(@PathVariable Long id) {
		return commentsService.findByPost(id);
	}
	
	@RequestMapping(value = "/deleteComment/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public void deleteComment(@PathVariable Long id) {
		commentsService.deleteComment(id);
	}
	
	@RequestMapping(value = "/postComment/", method = RequestMethod.POST, headers = "Accept=application/json")
	public PostCommentBlock postComment(@RequestBody PostComment comment) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		if(comment.getContent().isEmpty() == false) {
			Comments tempComment = commentsService.postComment(comment.getId(), username, comment.getContent()); 
			return new PostCommentBlock(tempComment, userService.findOne(auth.getName()));
		}
		return null;
	}
	
	@RequestMapping(value = "/postLike/{id}/{action}/{postId}", method = RequestMethod.GET)
	public UpdateLikeAction postCommands(@PathVariable Long id, @PathVariable int action, @PathVariable int postId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		int returnOperator = commentsService.postLike(auth.getName(), id, action, postId);
		return new UpdateLikeAction(returnOperator);
	}
}
