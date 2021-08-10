package com.mycompany.dao;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.entity.Role;

public interface IRoleFunctionDAO extends CrudRepository<Role, Integer> {
	
	Role findByName(String name);
	
	Role findById(int id );
	
}
