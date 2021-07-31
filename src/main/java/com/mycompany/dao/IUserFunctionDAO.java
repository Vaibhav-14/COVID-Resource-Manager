package com.mycompany.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mycompany.entity.User;


public interface IUserFunctionDAO extends CrudRepository<User, Integer>{

	@Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
	
	User findByUsername(String username);
}
