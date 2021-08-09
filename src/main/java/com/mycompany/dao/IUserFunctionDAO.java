package com.mycompany.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mycompany.entity.User;


public interface IUserFunctionDAO extends CrudRepository<User, Integer>{
	
	User findByUsername(String username);
	
	@Query(value = " select username from users;  ", nativeQuery = true)
	List<String> getListOfAllUsernames();
	
	@Query(value = "select * from users where id in (select user_id from users_roles where role_id=2)", nativeQuery = true)
	List<User> getAllAdmin();

}
