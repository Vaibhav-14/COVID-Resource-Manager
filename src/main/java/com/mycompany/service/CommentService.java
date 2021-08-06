package com.mycompany.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mycompany.dao.ICommentFunctionDAO;
import com.mycompany.dao.IPostFunctionDAO;
import com.mycompany.dao.IRoleFunctionDAO;
import com.mycompany.dao.IUserFunctionDAO;
import com.mycompany.entity.Comment;
import com.mycompany.entity.Role;
import com.mycompany.entity.User;
import com.mycompany.exception.IncorrectUserException;

@Service("commentService")
public class CommentService {
	
	@Autowired
	private ICommentFunctionDAO commentDao;
	
	@Autowired
	private IUserFunctionDAO userDao;
	
	@Autowired
	private IRoleFunctionDAO roleDao;
	
	public void addComment(Comment comment) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		comment.setUser(userDao.findByUsername(auth.getName()));
		comment.setDateTime(new Timestamp(System.currentTimeMillis()));
		commentDao.save(comment);
	}
	
	public void deleteComment(int id) throws IncorrectUserException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userDao.findByUsername(auth.getName());
		Role role = roleDao.findById(2).get();
		if(commentDao.findById(id).get().getUser().getId() == user.getId() || user.getRoles().contains(role)) {
			commentDao.deleteById(id);
		}
		else {
			throw new IncorrectUserException("This comment doesn't belong to User " + user.getUsername());
		}
	}

}
