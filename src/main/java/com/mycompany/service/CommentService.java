package com.mycompany.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mycompany.dao.ICommentFunctionDAO;
import com.mycompany.dao.IPostFunctionDAO;
import com.mycompany.dao.IUserFunctionDAO;
import com.mycompany.entity.Comment;

@Service("commentService")
public class CommentService {
	
	@Autowired
	private ICommentFunctionDAO commentDao;
	
	@Autowired
	private IUserFunctionDAO userDao;
	
	public void addComment(Comment comment) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		comment.setUser(userDao.findByUsername(auth.getName()));
		comment.setDateTime(new Timestamp(System.currentTimeMillis()));
		commentDao.save(comment);
	}
	
	public void deleteComment(int id) {
		commentDao.deleteById(id);
	}

}
