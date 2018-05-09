package com.comedyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comedyapp.dao.CommentLikesDAO;
import com.comedyapp.model.jpa.Comment_Likes_Log;

import java.util.ArrayList;
import java.util.List;

@Service(value = "commentLikesService")
public class CommentLikesServiceImpl implements CommentLikesService {

	@Autowired
	private CommentLikesDAO commentLikesDAO;

	@Override
	public List<Comment_Likes_Log> findAll() {
		List<Comment_Likes_Log> list = new ArrayList<>();
		commentLikesDAO.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public List<Comment_Likes_Log> find(String username) {
		return commentLikesDAO.findByUsername(username);
	}

	@Override
	public List<Comment_Likes_Log> findByUsernameAndPostId(String username, int id) {
		return commentLikesDAO.findByUsernameAndPostId(username, id);
	}
	@Override
	public int comment_likes_log(String username, Long comment_id, int action,  int postId) {
		int operator = 0;
		boolean isNew = true;
		List<Comment_Likes_Log> temp = this.find(username);

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getCommentId() == comment_id && temp.get(i).getUsername().equals(username) && temp.get(i).getCommentAction() != action) {// update action

				Comment_Likes_Log log = temp.get(i);
				log.setCommentAction(action);
				commentLikesDAO.save(log);

				if(action == 1) operator = 0;
				else 			operator = 2;
				
				isNew = false;
				break;
			} else if (temp.get(i).getCommentId() == comment_id && temp.get(i).getUsername().equals(username) && temp.get(i).getCommentAction() == action) {// delete row

				commentLikesDAO.delete(temp.get(i));

				operator = 1;
				isNew = false;
				break;
			}
		}
		
		if (isNew == true) {
			Comment_Likes_Log log = new Comment_Likes_Log();
			log.setUsername(username);
			log.setCommentId(comment_id.intValue());
			log.setCommentAction(action);
			log.setPostId(postId);
			commentLikesDAO.save(log);
		}
		
		return operator;
	}

	// @Override
	// public LikesLogInfo comment_likes_log(String username, Long post_id, int
	// action) {
	// int operator = 0;
	// int prevAction = 10;
	// boolean isNew = true;
	// List<Likes_Log> temp = this.find(username);
	//
	// for (int i = 0; i < temp.size(); i++) {
	// if (temp.get(i).getPostId() == post_id && temp.get(i).getPostAction() !=
	// action) {// update action
	//
	// Likes_Log log = temp.get(i);
	// prevAction = log.getPostAction();
	// log.setPostAction(action);
	// likesDAO.save(log);
	//
	// operator = 2;
	// isNew = false;
	// break;
	// } else if (temp.get(i).getPostId() == post_id && temp.get(i).getPostAction()
	// == action) {// delete row
	//
	// likesDAO.delete(temp.get(i));
	//
	// operator = 1;
	// isNew = false;
	// break;
	// }
	// }
	//
	// if (isNew == true) {
	// Likes_Log log = new Likes_Log();
	// log.setUsername(username);
	// log.setPostId(post_id.intValue());
	// log.setPostAction(action);
	// likesDAO.save(log);
	// }
	// return new LikesLogInfo(operator, prevAction);
	//
	// }
}
