package com.mycompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.dao.IUserFunctionDAO;
import com.mycompany.entity.User;


@Service("userService")
public class UserService {
	
	@Autowired
	private IUserFunctionDAO userDao;
	public IUserFunctionDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserFunctionDAO userDao) {
		this.userDao = userDao;
	}
	public void addUser(User u)
	{
		userDao.save(u);
	}

}
