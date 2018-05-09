package com.comedyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comedyapp.dao.CommentLikesDAO;
import com.comedyapp.dao.CommentsDAO;
import com.comedyapp.dao.PostsDAO;
import com.comedyapp.model.jpa.Comment_Likes_Log;
import com.comedyapp.model.jpa.Comments;
import com.comedyapp.model.jpa.Posts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "commentsService")
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsDAO commentsDAO;
	
	@Autowired
	private CommentLikesDAO commentLikesDAO;
	
	@Autowired
	private CommentLikesService commentLikesService;

	@Autowired
	private PostsDAO postsDAO;
	
	@Autowired
	private PostsService postsService;

	
	@Override
	public List<Comments> findAll() {
		List<Comments> list = new ArrayList<>();
		commentsDAO.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public Comments findById(Long id) {
		return commentsDAO.findById(id).get();
	}

	@Override
	public List<Comments> findByPost(Long id) {
		return commentsDAO.findByPostID(id);
	}

	@Override
	public Comments postComment(Long id, String username, String content) {
		Optional<Posts> post = this.postsService.findById(id);
		post.get().setAmount(post.get().getAmount() + 1);
		this.postsDAO.save(post.get());
		
		Comments comment = new Comments();
		comment.setContent(content);
		comment.setPostID(id.intValue());
		comment.setUsername(username);
		return commentsDAO.save(comment);
	}

	@Override
	public int postLike(String username, Long id, int action,  int postId) {
		Comments temp = this.findById(id);
		int res = this.commentLikesService.comment_likes_log(username, id, action, postId);
		if(res == 0) {
			temp.setAmount(temp.getAmount() + 1 );
		} else if(res == 1) {
			temp.setAmount(temp.getAmount() - 1 );
		}
		this.commentsDAO.save(temp);
		return res;
	}

	@Override
	public void deleteComment(Long id) {
		Comments postId = this.findById(id);
		Optional<Posts> post = this.postsService.findById(postId.getPostID());
		post.get().setAmount(post.get().getAmount() - 1);
		this.postsDAO.save(post.get());
		
		commentsDAO.deleteById(id);
		
		List<Comment_Likes_Log> temp = this.commentLikesDAO.findByCommentId(id.intValue());
		for(int i = 0; i < temp.size(); i++) {
			commentLikesDAO.delete(temp.get(i));
		}
	}
}
