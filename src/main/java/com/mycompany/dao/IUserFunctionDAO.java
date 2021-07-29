package com.mycompany.dao;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.beans.User;


public interface IUserFunctionDAO extends CrudRepository<User, Integer>{

}
