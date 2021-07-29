package com.mycompany.dao;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.entity.Post;

public interface IPostFunctionDAO extends CrudRepository<Post, Integer>{

}
