package com.mycompany.dao;

import org.springframework.data.repository.CrudRepository;
import com.mycompany.entity.User;


public interface IUserFunctionDAO extends CrudRepository<User, Integer>{
	
	User findByUsername(String username);
}
