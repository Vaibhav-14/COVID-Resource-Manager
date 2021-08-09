package com.mycompany.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mycompany.entity.User;


public interface IUserFunctionDAO extends CrudRepository<User, Integer>{
	
	User findByUsername(String username);
	
	@Query(value = " select username from users;  ", nativeQuery = true)
	List<String> getListOfAllUsernames();
	

	@Query(value = "select * from users where id in (select user_id from users_roles where role_id=2)", nativeQuery = true)
	List<User> getAllAdmin();

	@Query("SELECT username FROM User WHERE username LIKE %:keyword%")
	public List<String> searchUserByKeyWord(@Param("keyword") String keyword);


}
