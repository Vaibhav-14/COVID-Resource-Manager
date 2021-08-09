package com.mycompany.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.ICommentFunctionDAO;
import com.mycompany.dao.IPostFunctionDAO;
import com.mycompany.dao.IRoleFunctionDAO;
import com.mycompany.dao.IUserFunctionDAO;
import com.mycompany.entity.Comment;
import com.mycompany.entity.Post;
import com.mycompany.entity.Role;
import com.mycompany.entity.User;

@Transactional
@Service("userService")
public class UserService {
	
	@Autowired
	private IUserFunctionDAO userDao;
	
	@Autowired
	private IRoleFunctionDAO roleDao;
	
	@Autowired
	private IPostFunctionDAO postDao;

	@Autowired
	private ICommentFunctionDAO commentDao;
	
	public IUserFunctionDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserFunctionDAO userDao) {
		this.userDao = userDao;
	}
	public void addUser(User user)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		int roleId = 1; // role id for role 'USER'
		Role role = roleDao.findById(roleId).get();
		user.getRoles().add(role);
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.save(user);
	}
	public User getUserFromUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public List<String> getListOfAllUsernames()
	{
		return userDao.getListOfAllUsernames();
	}
	
	public List<Post> displayProfile(String username){
		if(username == null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			return postDao.findAllByUserIdOrderByDateTimeDesc(getUserFromUsername(auth.getName()).getId());
		}
		else {
			return postDao.findAllByUserIdOrderByDateTimeDesc(getUserFromUsername(username).getId());
		}
	}
	
	public User getUser(String username) {
		if(username == null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			return userDao.findByUsername(auth.getName());
		}
		else {
			return userDao.findByUsername(username);
		}
	}
	
	public void updateUser(User user) {
		System.out.println(user);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User admin_user = userDao.findByUsername(auth.getName());
		Role role = roleDao.findByName("ADMIN"); // for admin use only
		if(admin_user.getRoles().contains(role)) {
			userDao.save(user);
		}
	}
	
	public List<String> searchUsersByKeyWord(String keyword) {
		return userDao.searchUserByKeyWord(keyword);	
	}
	public Set<User> getUsersFromString(String message) {
		Set<User> mentionedUsers = new HashSet<>();
		String[] strs = message.split(" ");
		for (String string : strs) {
			if (string.startsWith("@")) {
				User user = getUser(string.substring(1));
				if (user != null)
					mentionedUsers.add(user);
			}
		}
		return mentionedUsers;
	}
	
	public void deleteUserAccount(String username)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = getUserFromUsername(auth.getName());

		if(user.getId() != getUserFromUsername(username).getId())
			return;
		
		//deleting comments by the user
		List<Comment> commentsToDelete = commentDao.findAllByUserId(user.getId());
		for(Comment currentComment: commentsToDelete)
			commentDao.deleteById(currentComment.getId());
		
		//deleting posts by the user
		List<Post> postsToDelete = postDao.findPostByUser(user);
		for(Post currentPost: postsToDelete)
			postDao.deleteById(currentPost.getId());
		
		/*
		 * Any more entities added in the db need to be listed and deleted here
		 * */
		
		//finally the user is deleted
		userDao.deleteById(user.getId());
	
	}

}
