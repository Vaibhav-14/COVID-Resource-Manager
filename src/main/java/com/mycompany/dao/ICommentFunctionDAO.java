package com.mycompany.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.entity.Comment;
import com.mycompany.entity.Post;

public interface ICommentFunctionDAO extends CrudRepository<Comment, Integer>{
	
	List<Comment> findAllByUserId(int userId);

}
