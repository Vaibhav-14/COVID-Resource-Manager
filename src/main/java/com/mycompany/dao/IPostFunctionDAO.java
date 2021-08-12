package com.mycompany.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mycompany.entity.Post;
import com.mycompany.entity.User;

public interface IPostFunctionDAO extends PagingAndSortingRepository<Post, Integer>{
	
	List<Post> findAllByUserIdOrderByDateTimeDesc(int userId);
	
	List<Post> findAllByOrderByDateTimeDesc();
	
	List<Post> findPostByUser(User user);

	Post findPostById(int id);
	
}
