package com.mycompany.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mycompany.dao.ICommentFunctionDAO;
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
		
		String activityType = "@" + loggedInUser.getUsername() + " commented on your post: " + 
									comment.getContent();
		
		notificationService.saveNotification(loggedInUser, activityType,
				"post", "post/" + comment.getPost().getId(), comment.getPost().getUser());
		
		Set<User> mentionedUsers = userService.getUsersFromString(comment.getContent());
		activityType = "@" + loggedInUser.getUsername() + " mentioned you in a comment" ;
		notificationService.saveNotification(loggedInUser, activityType,
				"comment", "post/" + comment.getPost().getId(), mentionedUsers);
		
	}
	
	public void deleteComment(int id) throws IncorrectUserException{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userDao.findByUsername(auth.getName());
		Role role = roleDao.findByName("ADMIN");
		boolean isAdmin = false;
		Comment comment = commentDao.findById(id).get();
		User commentUser = comment.getUser();
		if(commentUser.getId() == user.getId() || (isAdmin = user.getRoles().contains(role))) {
			commentDao.deleteById(id);
			if (isAdmin) {
				commentUser.setWarnings(commentUser.getWarnings()+1);
				String activityType = "Your comment violets the Covid Resource Manager Policies. "
						+ "So It has been removed. "+"You got "+commentUser.getWarnings() +
						" out of 5. After 5 warnings your account will get suspended.";

				notificationService.saveNotification(null, activityType, "post", 
									"post/" + id, commentUser);
				if(commentUser.getWarnings()>5) {
					commentUser.setEnabled(0);
					userService.updateUser(commentUser);
				}
			}
		}
		else {
			throw new IncorrectUserException("This comment doesn't belong to User " + user.getUsername());
		}
	}
	
	List<Comment> findAllCommentsByUserID(int userID)
	{
		return commentDao.findAllByUserId(userID);
	}

}
