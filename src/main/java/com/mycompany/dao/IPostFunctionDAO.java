package com.mycompany.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.entity.Post;

public interface IPostFunctionDAO extends CrudRepository<Post, Integer>{
	
	List<Post> findAllByUserIdOrderByDateTimeDesc(int userId);
	
	List<Post> findAllByOrderByDateTimeDesc();
	
}
