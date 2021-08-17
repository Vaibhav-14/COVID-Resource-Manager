package com.mycompany.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.ICommentFunctionDAO;
import com.mycompany.dao.IRoleFunctionDAO;
import com.mycompany.entity.Comment;
import com.mycompany.entity.Role;
import com.mycompany.entity.User;
import com.mycompany.exception.IncorrectUserException;

@Transactional
@Service("commentService")
public class CommentService {
	
	@Autowired
	private ICommentFunctionDAO commentDao;
	
	@Autowired
	private IRoleFunctionDAO roleDao;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentService.class);
	
	public void addComment(Comment comment) {
		User loggedInUser = userService.getLoggedInUser();

		comment.setUser(loggedInUser);
		comment.setDateTime(new Timestamp(System.currentTimeMillis()));
		commentDao.save(comment);
		logger.info("User : " + loggedInUser.getUsername() + " created a comment");
		
		User userOfThePost = comment.getPost().getUser();

		if (userOfThePost.getId() != loggedInUser.getId()) {
			String activityType = "@" + loggedInUser.getUsername() + " commented on your post: " + 
					comment.getContent();

			notificationService.saveNotification(loggedInUser, activityType,
			"post", "post/" + comment.getPost().getId(), userOfThePost);
			
			Set<User> mentionedUsers = userService.getUsersFromString(comment.getContent());
			activityType = "@" + loggedInUser.getUsername() + " mentioned you in a comment" ;
			notificationService.saveNotification(loggedInUser, activityType,
			"comment", "post/" + comment.getPost().getId(), mentionedUsers);
		}
				
	}
	
	public void deleteComment(int id) throws IncorrectUserException{
		User user = userService.getLoggedInUser();

		Role role = roleDao.findByName("ADMIN");
		boolean isAdmin = false;
		Comment comment = commentDao.findById(id).get();
		User commentUser = comment.getUser();
		if(commentUser.getId() == user.getId() || comment.getPost().getUser().getId() == user.getId() || (isAdmin = user.getRoles().contains(role))) {
			commentDao.deleteById(id);
			if (isAdmin) {
				logger.warn("Admin has deleted comment of user : " + commentUser.getUsername());
				commentUser.setWarnings(commentUser.getWarnings()+1);
				String activityType = "Your comment violets the Covid Resource Manager Policies. "
						+ "So It has been removed. "+"You got "+(commentUser.getWarnings()+1) +
						" out of 5. After 5 warnings your account will get suspended.";

				notificationService.saveNotification(null, activityType, "post", 
									"/user/profile", commentUser);
				if(commentUser.getWarnings()>5) {
					logger.warn("The account of user : " + commentUser.getUsername() + " is suspended autometically due to 5 warnings");
					commentUser.setEnabled(0);
					userService.updateUser(commentUser);
				}
			}
			else {
				logger.info("User : " + commentUser.getUsername() + " has deleted post with id = " + id);
			}
		}
		else {
			logger.error("User : " + user.getUsername() + " doesn't have permission to delete post with id = " + id);
			throw new IncorrectUserException("This comment doesn't belong to User " + user.getUsername());
		}
	}
	
	List<Comment> findAllCommentsByUserID(int userID)
	{
		return commentDao.findAllByUserId(userID);
	}

}
