package com.mycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.dao.IRoleFunctionDAO;
import com.mycompany.dao.IUserFunctionDAO;
import com.mycompany.entity.Role;
import com.mycompany.entity.User;


@Service("userService")
public class UserService {
	
	@Autowired
	private IUserFunctionDAO userDao;
	
	@Autowired
	private IRoleFunctionDAO roleDao;
	
	public IUserFunctionDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserFunctionDAO userDao) {
		this.userDao = userDao;
	}
	public void addUser(User user)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Role role = roleDao.findById(1).get();
		user.getRoles().add(role);
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.save(user);
	}
	public User getUserFromUsername(String username) {
		return userDao.findByUsername(username);
	}

}
