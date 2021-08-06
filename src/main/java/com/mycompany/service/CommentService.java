package com.mycompany.service;

import java.sql.Timestamp;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mycompany.dao.ICommentFunctionDAO;
import com.mycompany.dao.IPostFunctionDAO;
import com.mycompany.dao.IRoleFunctionDAO;
import com.mycompany.dao.IUserFunctionDAO;
import com.mycompany.entity.Comment;
import com.mycompany.entity.Notification;
import com.mycompany.entity.Role;
import com.mycompany.entity.User;

@Service("commentService")
public class CommentService {
	
	@Autowired
	private ICommentFunctionDAO commentDao;
	
	@Autowired
	private IUserFunctionDAO userDao;
	
	@Autowired
	private IRoleFunctionDAO roleDao;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UserService userService;
	
	public void addComment(Comment comment) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		User loggedInUser = userService.getUserFromUsername(auth.getName());

		comment.setUser(loggedInUser);
		comment.setDateTime(new Timestamp(System.currentTimeMillis()));
		commentDao.save(comment);
		
		Set<User> mentionedUsers = userService.getUsersFromString(comment.getContent());
		notificationService.saveNotification(loggedInUser, "comment", "post/" + comment.getPost().getId(), mentionedUsers);
	}
	
	public void deleteComment(int id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userDao.findByUsername(auth.getName());
		Role role = roleDao.findById(2).get();
		if(commentDao.findById(id).get().getUser().getId() == user.getId() || user.getRoles().contains(role)) {
			commentDao.deleteById(id);
		}
	}

}
