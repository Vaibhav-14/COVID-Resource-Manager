package com.mycompany.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mycompany.entity.Post;
import com.mycompany.entity.User;


public interface IUserFunctionDAO extends CrudRepository<User, Integer>{
	
	User findByUsername(String username);
	
	@Query(value = " select username from users;  ", nativeQuery = true)
	List<String> getListOfAllUsernames();

}
