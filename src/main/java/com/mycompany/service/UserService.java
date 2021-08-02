package com.mycompany.service;

import java.util.List;

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

@Transactional
@Service("userService")
public class UserService {
	
	@Autowired
	private IUserFunctionDAO userDao;
	
	@Autowired
	private IRoleFunctionDAO roleDao;
	
	@Autowired
	private IPostFunctionDAO postDao;
	
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
	
	public List<Post> displayProfile(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return postDao.findAllByUserIdOrderByDateTimeDesc(getUserFromUsername(auth.getName()).getId());
	}
	
	public User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return userDao.findByUsername(auth.getName());
	}

}
