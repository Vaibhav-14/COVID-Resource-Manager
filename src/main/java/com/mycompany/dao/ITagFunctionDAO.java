package com.mycompany.dao;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.entity.Tag;

public interface ITagFunctionDAO extends CrudRepository<Tag, String>{

}
