package com.mycompany.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.dao.IPostFunctionDAO;
import com.mycompany.dao.IRoleFunctionDAO;
import com.mycompany.dao.IUserFunctionDAO;
import com.mycompany.entity.Post;
import com.mycompany.entity.Role;
import com.mycompany.entity.User;
import com.mycompany.exception.IncorrectUserException;

@Transactional
@Service("userService")
public class UserService {
	
	@Autowired
	private IUserFunctionDAO userDao;
	
	@Autowired
	private IRoleFunctionDAO roleDao;
	
	@Autowired
	private IPostFunctionDAO postDao;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
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
		Role role = roleDao.findById(roleId);
		user.getRoles().add(role);
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.save(user);
		logger.info("User : " + user.getUsername() + " has registered successfully.");
		
	}
	
	public User getUserFromUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	public List<String> getListOfAllUsernames() {
		return userDao.getListOfAllUsernames();
	}
	
	public List<Post> displayProfile(String username){
		if(username == null) {
			return postDao.findAllByUserIdOrderByDateTimeDesc(getLoggedInUser().getId());
		}
		else {
			return postDao.findAllByUserIdOrderByDateTimeDesc(getUserFromUsername(username).getId());
		}
	}
		
	public void updateUser(User user) {
		User admin_user = getLoggedInUser();

		Role role = roleDao.findByName("ADMIN"); // for admin use only
		if(admin_user.getRoles().contains(role)) {
			userDao.save(user);
		}
	}
	
	public void updateUserProfile(User user) {
		user.setEnabled(1);
		User userOld = getUserFromUsername(user.getUsername());
		user.setRoles(userOld.getRoles());
		userDao.save(user);
		logger.info("User : " + user.getUsername() + " has updated the profile information.");
	}
	
	public void changePassword(User user) {
		user.setEnabled(1);
		User userOld = getUserFromUsername(user.getUsername());
		user.setRoles(userOld.getRoles());
		userDao.save(user);
		logger.info("User : " + user.getUsername() + " has changed the password.");
	}
	
	public List<String> searchUsersByKeyWord(String keyword) {
		return userDao.searchUserByKeyWord(keyword);	
	}
	public Set<User> getUsersFromString(String message) {
		Set<User> mentionedUsers = new HashSet<>();
		String[] strs = message.split(" ");
		for (String string : strs) {
			if (string.startsWith("@")) {
				User user = getUserFromUsername(string.substring(1));
				if (user != null)
					mentionedUsers.add(user);
			}
		}
		return mentionedUsers;
	}
	
	public void deleteUserAccount(String username) throws IncorrectUserException {
		User user = getLoggedInUser();

		if(user.getId() != getUserFromUsername(username).getId()) {
			logger.error("User : " + user.getUsername() + " doesn't have the permission to delete account with username = " + username);
			throw new IncorrectUserException("Forbidden");
		}
		
		userDao.deleteById(user.getId());
		logger.info("User : " + username + " has deleted the acoount");
	
	}
	
	public List<User> getAllAdmin() {
		return userDao.getAllAdmin();
	}
	
	public User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = getUserFromUsername(auth.getName());
		return user;
	}

}
