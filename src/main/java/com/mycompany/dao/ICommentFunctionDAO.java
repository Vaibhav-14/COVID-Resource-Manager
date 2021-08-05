package com.mycompany.dao;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.entity.Comment;

public interface ICommentFunctionDAO extends CrudRepository<Comment, Integer>{

}
