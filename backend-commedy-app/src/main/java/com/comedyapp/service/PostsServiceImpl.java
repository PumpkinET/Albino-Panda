package com.comedyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comedyapp.dao.CommentLikesDAO;
import com.comedyapp.dao.CommentsDAO;
import com.comedyapp.dao.LikesDAO;
import com.comedyapp.dao.PostsDAO;
import com.comedyapp.model.jpa.Comment_Likes_Log;
import com.comedyapp.model.jpa.Comments;
import com.comedyapp.model.jpa.Likes_Log;
import com.comedyapp.model.jpa.Posts;
import com.comedyapp.model.rest.CreatePost;
import com.comedyapp.model.rest.LikesLogInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "postsService")
public class PostsServiceImpl implements PostsService {

	@Autowired
	private PostsDAO postsDAO;

	@Autowired
	private CommentsDAO commentsDAO;

	@Autowired
	private CommentLikesDAO commentLikesDAO;

	@Autowired
	LikesService likesService;
	
	@Autowired
	private LikesDAO likesDAO;

	
	@Autowired
	CategoriesService categoriesService;

	@Override
	public List<Posts> findAll() {
		List<Posts> list = new ArrayList<>();
		postsDAO.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		
		/* POST */
		Posts hmm = postsDAO.findById(id).get();
		this.categoriesService.sub_update(hmm.getCategory());//update category
		postsDAO.delete(hmm);

		/* COMMENT */
		List<Comments> comment = commentsDAO.findByPostID(id);
		for (int i = 0; i < comment.size(); i++) {
			/* COMMENT LIKES LOG */
			List<Comment_Likes_Log> temp = commentLikesDAO.findByCommentId((int) comment.get(i).getId());
			for (int j = 0; j < temp.size(); j++) {
				commentLikesDAO.delete(temp.get(j));
			}
			commentsDAO.delete(comment.get(i));
		}
		
		List<Likes_Log> likes = this.likesDAO.findByPostId((int) id);
		for (int i = 0; i < likes.size(); i++) {
			likesDAO.delete(likes.get(i));
		}

	}

	@Override
	public Optional<Posts> findById(Long id) {
		return postsDAO.findById(id);
		// return postsDAO.findOne(id);
	}

	@Override
	public int postCommands(String username, Long id, int action/* , int operator */) {

		Optional<Posts> temp = this.findById(id);
		LikesLogInfo info = this.likesService.likes_log(username, id, action);
		int operator = info.getOperator();

		if (operator != 2) {
			switch (action) {
			case 1:
				temp.get()
						.setOkayAmount(operator == 0 ? temp.get().getOkayAmount() + 1 : temp.get().getOkayAmount() - 1);
				break;
			case 2:
				temp.get().setForever_aloneAmount(operator == 0 ? temp.get().getForever_aloneAmount() + 1
						: temp.get().getForever_aloneAmount() - 1);
				break;
			case 3:
				temp.get().setMe_gustaAmount(
						operator == 0 ? temp.get().getMe_gustaAmount() + 1 : temp.get().getMe_gustaAmount() - 1);
				break;
			case 4:
				temp.get().setOmgAmount(operator == 0 ? temp.get().getOmgAmount() + 1 : temp.get().getOmgAmount() - 1);
				break;
			}
		} else {
			switch (info.getLastAction()) {
			case 1:
				temp.get().setOkayAmount(temp.get().getOkayAmount() - 1);
				break;
			case 2:
				temp.get().setForever_aloneAmount(temp.get().getForever_aloneAmount() - 1);
				break;
			case 3:
				temp.get().setMe_gustaAmount(temp.get().getMe_gustaAmount() - 1);
				break;
			case 4:
				temp.get().setOmgAmount(temp.get().getOmgAmount() - 1);
				break;
			}
			switch (action) {
			case 1:
				temp.get().setOkayAmount(temp.get().getOkayAmount() + 1);
				break;
			case 2:
				temp.get().setForever_aloneAmount(temp.get().getForever_aloneAmount() + 1);
				break;
			case 3:
				temp.get().setMe_gustaAmount(temp.get().getMe_gustaAmount() + 1);
				break;
			case 4:
				temp.get().setOmgAmount(temp.get().getOmgAmount() + 1);
				break;
			}
		}
		postsDAO.save(temp.get());

		return operator;

	}

	@Override
	public List<Posts> usernamePosts(String username) {
		return postsDAO.findByUsername(username);
	}

	@Override
	public Posts save(String username, CreatePost post) {
		Posts createPost = new Posts();
		createPost.setUsername(username);
		createPost.setTitle(post.getTitle());

		createPost.setOkayAmount(0);
		createPost.setForever_aloneAmount(0);
		createPost.setMe_gustaAmount(0);
		createPost.setOmgAmount(0);

		createPost.setIsURL(post.getPostType());
		createPost.setCategory(post.getSelectedCategory());

		Posts updateContent = postsDAO.save(createPost);

		if (post.getPostType() == 1)
			updateContent.setContent(
					"http://albinopanda.net:8080/data/posts/" + username + "/" + username + "_post_id_" + updateContent.getId()+".png");
		else
			updateContent.setContent(post.getContent());

		categoriesService.update(post.getSelectedCategory());
		return postsDAO.save(updateContent);// (int) updateContent.getId();
	}

	@Override
	public List<Posts> findByCategory(String category) {
		return postsDAO.findByCategory(category);
	}

}
