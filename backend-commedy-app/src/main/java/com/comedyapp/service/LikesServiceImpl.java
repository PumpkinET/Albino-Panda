package com.comedyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comedyapp.dao.LikesDAO;
import com.comedyapp.model.jpa.Likes_Log;
import com.comedyapp.model.rest.LikesLogInfo;

import java.util.ArrayList;
import java.util.List;

@Service(value = "likesService")
public class LikesServiceImpl implements LikesService {

	@Autowired
	private LikesDAO likesDAO;

	@Override
	public List<Likes_Log> findAll() {
		List<Likes_Log> list = new ArrayList<>();
		likesDAO.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public List<Likes_Log> find(String username) {
		return likesDAO.findByUsername(username);
	}

	@Override
	public LikesLogInfo likes_log(String username, Long post_id, int action) {
		int operator = 0;
		int prevAction = 10;
		boolean isNew = true;
		List<Likes_Log> temp = this.find(username);

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getPostId() == post_id && temp.get(i).getPostAction() != action) {// update action
				
				Likes_Log log = temp.get(i);
				prevAction = log.getPostAction();
				log.setPostAction(action);
				likesDAO.save(log);

				operator = 2;
				isNew = false;
				break;
			} else if (temp.get(i).getPostId() == post_id && temp.get(i).getPostAction() == action) {// delete row
				
				likesDAO.delete(temp.get(i));
				
				operator = 1;
				isNew = false;
				break;
			}
		}
		
		if (isNew == true) {
			Likes_Log log = new Likes_Log();
			log.setUsername(username);
			log.setPostId(post_id.intValue());
			log.setPostAction(action);
			likesDAO.save(log);
		}
		return new LikesLogInfo(operator, prevAction);

	}
}
